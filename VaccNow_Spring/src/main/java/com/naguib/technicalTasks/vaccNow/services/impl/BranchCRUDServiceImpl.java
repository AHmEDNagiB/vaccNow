package com.naguib.technicalTasks.vaccNow.services.impl;

import com.naguib.technicalTasks.vaccNow.adapter.BranchDTOAdapter;
import com.naguib.technicalTasks.vaccNow.controller.dto.BranchDTO;
import com.naguib.technicalTasks.vaccNow.controller.dto.BranchMockDTO;
import com.naguib.technicalTasks.vaccNow.controller.dto.ResponseDTO;
import com.naguib.technicalTasks.vaccNow.entity.Branch;
import com.naguib.technicalTasks.vaccNow.repositories.BranchRepository;
import com.naguib.technicalTasks.vaccNow.services.BranchCRUDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.naguib.technicalTasks.vaccNow.utils.Constants.NO_DATA_FOUND;


@Service
public class BranchCRUDServiceImpl implements BranchCRUDService {


    private BranchRepository branchRepository;
    private BranchDTOAdapter branchDTOAdapter;

    @Autowired
    public BranchCRUDServiceImpl(BranchRepository branchRepository, BranchDTOAdapter branchDTOAdapter) {
        this.branchRepository = branchRepository;
        this.branchDTOAdapter = branchDTOAdapter;
    }

    @Override
    public ResponseDTO gatBranchesMockUp() {
        List<Branch> allBranches = (List<Branch>) branchRepository.findAll();
        List<BranchMockDTO> branchMockDTOList = allBranches.stream()
                .map(entity -> new BranchMockDTO(entity.getId(), entity.getName())).collect(Collectors.toList());
        return new ResponseDTO(branchMockDTOList);
    }

    @Override
    public ResponseDTO gatBranches(long branchId, long vaccineId) {
        List<Branch> allBranches = branchRepository.findAllBranches(branchId, vaccineId);
        ResponseDTO<List<BranchDTO>> responseDTO;
        if ((allBranches == null || allBranches.isEmpty()) && (branchId > 0 || vaccineId > 0)) {
            responseDTO = new ResponseDTO("Wrong sent params", 400);
        } else if ((allBranches == null || allBranches.isEmpty()) && (branchId == 0 && vaccineId == 0)) {
            responseDTO = new ResponseDTO(NO_DATA_FOUND, 204);
        } else {
            responseDTO = new ResponseDTO(allBranches.stream().map(branch -> branchDTOAdapter.getBranchDTO(branch))
                    .collect(Collectors.toList()));
        }
        return responseDTO;
    }

}
