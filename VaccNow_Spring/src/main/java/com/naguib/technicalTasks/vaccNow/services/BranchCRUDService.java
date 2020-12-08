package com.naguib.technicalTasks.vaccNow.services;

import com.naguib.technicalTasks.vaccNow.controller.dto.ResponseDTO;

public interface BranchCRUDService {
    ResponseDTO gatBranches(long branchId, long vaccineId);
    ResponseDTO gatBranchesMockUp();
}
