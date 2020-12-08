package com.naguib.technicalTasks.vaccNow.services;

import com.naguib.technicalTasks.vaccNow.controller.dto.ResponseDTO;

public interface AvailableTimeSlotsService {

    ResponseDTO gatAvailableTimeSlots(long branchId, String date);

}
