package com.naguib.technicalTasks.vaccNow.services.impl;

import com.naguib.technicalTasks.vaccNow.controller.dto.ResponseDTO;
import com.naguib.technicalTasks.vaccNow.controller.dto.ScheduleVaccinationRequestDTO;
import com.naguib.technicalTasks.vaccNow.entity.BranchVaccine;
import com.naguib.technicalTasks.vaccNow.entity.ScheduledVaccination;
import com.naguib.technicalTasks.vaccNow.entity.VaccinationTimeSlot;
import com.naguib.technicalTasks.vaccNow.repositories.BranchVaccineRepository;
import com.naguib.technicalTasks.vaccNow.repositories.ScheduledVaccinationRepository;
import com.naguib.technicalTasks.vaccNow.repositories.VaccinationTimeSlotsRepository;
import com.naguib.technicalTasks.vaccNow.services.MailService;
import com.naguib.technicalTasks.vaccNow.services.VaccinationService;
import com.naguib.technicalTasks.vaccNow.utils.UtilService;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static com.naguib.technicalTasks.vaccNow.utils.Constants.*;

@Service
public class VaccinationServiceImpl implements VaccinationService {
    private ScheduledVaccinationRepository scheduledVaccinationRepository;
    private VaccinationTimeSlotsRepository vaccinationTimeSlotsRepository;
    private BranchVaccineRepository branchVaccineRepository;
    private MailService mailService;
    private UtilService utilService;

    public VaccinationServiceImpl(ScheduledVaccinationRepository scheduledVaccinationRepository,
                                  VaccinationTimeSlotsRepository vaccinationTimeSlotsRepository,
                                  BranchVaccineRepository branchVaccineRepository,
                                  MailService mailService, UtilService utilService) {
        this.scheduledVaccinationRepository = scheduledVaccinationRepository;
        this.vaccinationTimeSlotsRepository = vaccinationTimeSlotsRepository;
        this.branchVaccineRepository = branchVaccineRepository;
        this.mailService = mailService;
        this.utilService = utilService;
    }

    @Override
    public ResponseDTO getAppliedVaccination(long branch, String dateFrom, String dateTo) {
        Date inputDateFrom = utilService.getDateFromString(dateFrom, DATE_FORMAT);
        Date inputDateTo = utilService.getDateFromString(dateTo, DATE_FORMAT);

        List<ScheduledVaccination> appliedVaccination = scheduledVaccinationRepository

                .findAllByBranchOrDate(branch, (int) branch, 1, inputDateFrom, inputDateTo);
        if (appliedVaccination == null) {
            return new ResponseDTO(NO_DATA_FOUND, 204);
        }
        return new ResponseDTO(appliedVaccination);
    }

    @Override
    public ResponseDTO getConfirmedVaccination(long branch, String dateFrom, String dateTo) {
        Date inputDateFrom = utilService.getDateFromString(dateFrom, DATE_FORMAT);
        Date inputDateTo = utilService.getDateFromString(dateTo, DATE_FORMAT);

        List<ScheduledVaccination> appliedVaccination = scheduledVaccinationRepository
                .findAllByBranchOrDate(branch, (int) branch, 0, inputDateFrom, inputDateTo);
        if (appliedVaccination == null) {
            return new ResponseDTO(NO_DATA_FOUND, 204);
        }
        return new ResponseDTO(appliedVaccination);
    }

    @Override
    public ResponseDTO scheduleVaccination(ScheduleVaccinationRequestDTO requestDTO) {
        VaccinationTimeSlot timeSlot = vaccinationTimeSlotsRepository.findById(requestDTO.getTimeSlot())
                .orElseGet(null);
        BranchVaccine branchVaccine = branchVaccineRepository.findById(requestDTO.getTimeSlot())
                .orElseGet(null);
        if (timeSlot == null || branchVaccine == null) {
            return new ResponseDTO("Wrong request body", 400);
        }
        Date vaccinationDay = null;
        try {
            vaccinationDay = new SimpleDateFormat(DATE_FORMAT).parse(requestDTO.getVaccinationDay());
        } catch (ParseException e) {
            e.printStackTrace();
            return new ResponseDTO(WRONG_INPUT_DATE_FORMAT, 400);
        }

        ScheduledVaccination scheduledVaccination = new ScheduledVaccination();
        scheduledVaccination.setBranchVaccine(branchVaccine);
        scheduledVaccination.setTimeSlot(timeSlot);
        scheduledVaccination.setIsApplied(0);
        scheduledVaccination.setPayingType(requestDTO.getPayingType());
        scheduledVaccination.setVaccinationDay(vaccinationDay);
        scheduledVaccination.setClintEmail(requestDTO.getClintEmail());
        scheduledVaccinationRepository.save(scheduledVaccination);
        this.sendMail(scheduledVaccination);
        return new ResponseDTO("Schedule is done ");

    }

    void sendMail(ScheduledVaccination scheduledVaccination) {
        String subject = "You have successfully scheduled a vaccination";

        String body = String.format("You have successfully scheduled a vaccination at branch: %1$s " +
                        "at time from :  %2$s to: %3$s  ," +
                        "location is: %4$s ", scheduledVaccination.getBranchVaccine().getBranch().getName(),
                scheduledVaccination.getTimeSlot().getTimeFrom(), scheduledVaccination.getTimeSlot().getTimeTo(),
                scheduledVaccination.getBranchVaccine().getBranch().getLocation()
        );
        this.mailService.sentMail(scheduledVaccination.getClintEmail(), subject, body);

    }
}
