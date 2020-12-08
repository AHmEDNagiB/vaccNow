package com.naguib.technicalTasks.vaccNow.services;

import com.naguib.technicalTasks.vaccNow.controller.dto.ResponseDTO;
import com.naguib.technicalTasks.vaccNow.controller.dto.ScheduleVaccinationRequestDTO;

public interface VaccinationService {
    ResponseDTO getAppliedVaccination(long branch, String dateFrom, String dateTo);
    ResponseDTO getConfirmedVaccination(long branch, String dateFrom, String dateTo);
    ResponseDTO scheduleVaccination(ScheduleVaccinationRequestDTO requestDTO) ;
}
