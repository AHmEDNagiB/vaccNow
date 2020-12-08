package com.naguib.technicalTasks.vaccNow.controller.dto;

import com.naguib.technicalTasks.vaccNow.entity.Vaccine;

import java.util.List;

public class BranchDTO {
    private long id;
    private String name;
    private String location;
    private List<Vaccine> Vaccines;
    private String availableVaccines;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<Vaccine> getVaccines() {
        return Vaccines;
    }

    public void setVaccines(List<Vaccine> vaccines) {
        Vaccines = vaccines;
    }

    public String getAvailableVaccines() {
        return availableVaccines;
    }

    public void setAvailableVaccines(String availableVaccines) {
        this.availableVaccines = availableVaccines;
    }
}
