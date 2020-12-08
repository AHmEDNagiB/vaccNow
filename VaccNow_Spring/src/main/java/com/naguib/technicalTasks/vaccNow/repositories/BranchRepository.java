package com.naguib.technicalTasks.vaccNow.repositories;

import com.naguib.technicalTasks.vaccNow.entity.Branch;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/*
  PagingAndSortingRepository is used when we want paging and sorting function
*/

public interface BranchRepository extends PagingAndSortingRepository<Branch, Long> {
    @Query(value = "select distinct b.* from branch b, branch_vaccine bv, vaccine v " +
            "where (bv.vaccine_count > 0) and  (:branchId = 0 or b.id = :branchId)" +
            "and (:vaccineId = 0 or v.id = :vaccineId)" +
            "and b.id = bv.branch_id " +
            "and v.id = bv.vaccine_id", nativeQuery = true)
    List<Branch> findAllBranches( @Param("branchId") long branchId , @Param("vaccineId") long vaccineId);


}

