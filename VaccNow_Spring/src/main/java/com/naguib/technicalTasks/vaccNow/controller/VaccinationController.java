package com.naguib.technicalTasks.vaccNow.controller;

import com.naguib.technicalTasks.vaccNow.controller.dto.ResponseDTO;
import com.naguib.technicalTasks.vaccNow.controller.dto.ScheduleVaccinationRequestDTO;
import com.naguib.technicalTasks.vaccNow.services.VaccinationService;
import com.naguib.technicalTasks.vaccNow.utils.UtilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/vaccination")
public class VaccinationController {
    private VaccinationService vaccinationService;
    private UtilService utilService;


    @Autowired
    public VaccinationController(VaccinationService vaccinationService, UtilService utilService) {
        this.vaccinationService = vaccinationService;
        this.utilService = utilService;

    }

    @GetMapping("/applied")
    public ResponseEntity<ResponseDTO> gatAppliedVaccinations(@RequestParam(required = false, defaultValue = "0") long branch,
                                                              @RequestParam(required = false) String dateFrom,
                                                              @RequestParam(required = false) String dateTo) {

        ResponseDTO responseDTO = vaccinationService.getAppliedVaccination(branch, dateFrom, dateTo);
        return utilService.getResponse(responseDTO);

    }

    @GetMapping("/confirmed")
    public ResponseEntity<ResponseDTO> gatConfirmedVaccinations(@RequestParam(required = false, defaultValue = "0") long branch,
                                                                @RequestParam(required = false) String dateFrom,
                                                                @RequestParam(required = false) String dateTo) {

        ResponseDTO responseDTO = vaccinationService.getConfirmedVaccination(branch, dateFrom, dateTo);
        return utilService.getResponse(responseDTO);

    }

    @PostMapping("/schedule")
    public ResponseEntity<ResponseDTO> scheduleVaccination(@RequestBody @Valid ScheduleVaccinationRequestDTO requestDTO) {
        ResponseDTO responseDTO = vaccinationService.scheduleVaccination(requestDTO);
        return utilService.getResponse(responseDTO);

    }


}
