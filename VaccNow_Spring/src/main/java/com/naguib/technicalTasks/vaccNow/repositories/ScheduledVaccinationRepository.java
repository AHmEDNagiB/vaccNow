package com.naguib.technicalTasks.vaccNow.repositories;

import com.naguib.technicalTasks.vaccNow.entity.ScheduledVaccination;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

/*
  PagingAndSortingRepository is used when we want paging and sorting function
*/

public interface ScheduledVaccinationRepository extends PagingAndSortingRepository<ScheduledVaccination, Long> {

    List<ScheduledVaccination> findAllByBranchVaccine_Branch_IdAndVaccinationDay(Long branchId, Date date);


    @Query("from ScheduledVaccination sv where (:id = 0 or sv.branchVaccine.branch.id = :branchId  ) "
            + "and (sv.isApplied = :isApplied ) "
            + "and (:dateFrom is null or sv.vaccinationDay >= :dateFrom)"
            + "and (:dateTo is null or sv.vaccinationDay <= :dateTo)"
    )
    List<ScheduledVaccination> findAllByBranchOrDate(@Param("branchId") Long branchId, @Param("id") Integer id,
                                                     @Param("isApplied") int isApplied,
                                                     @Param("dateFrom") Date dateFrom,
                                                     @Param("dateTo") Date dateTo);


}
