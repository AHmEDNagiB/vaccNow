package com.naguib.technicalTasks.vaccNow.controller;

import com.naguib.technicalTasks.vaccNow.controller.dto.ResponseDTO;
import com.naguib.technicalTasks.vaccNow.services.AvailableTimeSlotsService;
import com.naguib.technicalTasks.vaccNow.services.BranchCRUDService;
import com.naguib.technicalTasks.vaccNow.utils.UtilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping(value = "/branch")
public class BranchController {

    private BranchCRUDService branchCRUDService;
    private AvailableTimeSlotsService availableTimeSlotsService;
    private UtilService utilService;

    @Autowired
    public BranchController(BranchCRUDService branchCRUDService, AvailableTimeSlotsService availableTimeSlotsService, UtilService utilService) {
        this.branchCRUDService = branchCRUDService;
        this.availableTimeSlotsService = availableTimeSlotsService;
        this.utilService = utilService;
    }

    @GetMapping
    public ResponseEntity gatBranches(@RequestParam(required = false, defaultValue = "0") long branch,
                                      @RequestParam(required = false, defaultValue = "0") long vaccine) {
        ResponseDTO responseDTO = branchCRUDService.gatBranches(branch, vaccine);
        return utilService.getResponse(responseDTO);

    }

    @GetMapping("/mock-up")
    public ResponseEntity gatBranchesMockUp() {
        ResponseDTO responseDTO = branchCRUDService.gatBranchesMockUp();
        return utilService.getResponse(responseDTO);

    }

    @GetMapping("/available-time-slots")
    public ResponseEntity gatAvailableTimeSlots(@RequestParam @NotNull @NotBlank @Valid String date,
                                                @RequestParam @Min(1) @Valid long branch) {
        ResponseDTO responseDTO = availableTimeSlotsService.gatAvailableTimeSlots(branch, date);
        return utilService.getResponse(responseDTO);
    }


}
