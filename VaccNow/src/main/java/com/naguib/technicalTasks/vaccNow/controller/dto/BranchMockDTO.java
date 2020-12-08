package com.naguib.technicalTasks.vaccNow.controller.dto;

public class BranchMockDTO {
    private String name;
    private Long id;

    public BranchMockDTO() {
    }

    public BranchMockDTO(Long id, String name) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
