package com.naguib.technicalTasks.vaccNow.adapter;

import com.naguib.technicalTasks.vaccNow.controller.dto.BranchDTO;
import com.naguib.technicalTasks.vaccNow.entity.Branch;
import com.naguib.technicalTasks.vaccNow.entity.BranchVaccine;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class BranchDTOAdapter {
    public BranchDTO getBranchDTO(Branch entity) {
        BranchDTO dto = new BranchDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setLocation(entity.getLocation());
        dto.setAvailableVaccines(entity.getAvailableVaccines());
        dto.setVaccines(entity.getBranchVaccines().stream().map(BranchVaccine::getVaccine).collect(Collectors.toList()));
        return dto;
    }
}
