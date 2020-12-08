package com.naguib.technicalTasks.vaccNow.repositories;

import com.naguib.technicalTasks.vaccNow.entity.Vaccine;
import org.springframework.data.repository.CrudRepository;



public interface VaccineRepository extends CrudRepository<Vaccine, Long> {

}

