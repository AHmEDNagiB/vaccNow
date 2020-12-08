package com.naguib.technicalTasks.vaccNow.services.impl;

import com.naguib.technicalTasks.vaccNow.controller.dto.ResponseDTO;
import com.naguib.technicalTasks.vaccNow.entity.ScheduledVaccination;
import com.naguib.technicalTasks.vaccNow.entity.VaccinationTimeSlot;
import com.naguib.technicalTasks.vaccNow.repositories.ScheduledVaccinationRepository;
import com.naguib.technicalTasks.vaccNow.repositories.VaccinationTimeSlotsRepository;
import com.naguib.technicalTasks.vaccNow.services.AvailableTimeSlotsService;
import com.naguib.technicalTasks.vaccNow.utils.UtilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static com.naguib.technicalTasks.vaccNow.utils.Constants.DATE_FORMAT;
import static com.naguib.technicalTasks.vaccNow.utils.Constants.WRONG_INPUT_DATE_FORMAT;

@Service
public class AvailableTimeSlotsServiceImpl implements AvailableTimeSlotsService {

    private ScheduledVaccinationRepository scheduledVaccinationRepository;
    private VaccinationTimeSlotsRepository vaccinationTimeSlotsRepository;
    private UtilService utilService;

    @Autowired
    public AvailableTimeSlotsServiceImpl(ScheduledVaccinationRepository scheduledVaccinationRepository,
                                         VaccinationTimeSlotsRepository vaccinationTimeSlotsRepository,
                                         UtilService utilService) {
        this.scheduledVaccinationRepository = scheduledVaccinationRepository;
        this.vaccinationTimeSlotsRepository = vaccinationTimeSlotsRepository;
        this.utilService = utilService;
    }

    @Override
    public ResponseDTO gatAvailableTimeSlots(long branchId, String date) {
        Date inputDate = utilService.getDateFromString(date, DATE_FORMAT);
        if (inputDate == null) {
            return new ResponseDTO(WRONG_INPUT_DATE_FORMAT, 400);
        }

        List<ScheduledVaccination> scheduledVaccinations = null;
        scheduledVaccinations = scheduledVaccinationRepository
                .findAllByBranchVaccine_Branch_IdAndVaccinationDay(branchId, inputDate);
        List<Long> usedTimeSlots = scheduledVaccinations.stream().map(sv -> sv.getTimeSlot().getId())
                .collect(Collectors.toList());
        List<VaccinationTimeSlot> timeSlots = (List<VaccinationTimeSlot>) vaccinationTimeSlotsRepository.findAll();
        timeSlots = timeSlots.stream().filter(ts -> !usedTimeSlots.contains(ts.getId())).collect(Collectors.toList());
        if (timeSlots.isEmpty()) {
            return new ResponseDTO("No Available timeSlots for this day", 204);
        }
        return new ResponseDTO(timeSlots);
    }
}
