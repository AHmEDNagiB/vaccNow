package com.naguib.technicalTasks.vaccNow.controller.dto;

import com.naguib.technicalTasks.vaccNow.utils.PayingType;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.StringJoiner;

public class ScheduleVaccinationRequestDTO {

    @NotNull
    @NotBlank
    private String vaccinationDay;

    @Min(1)
    private long branchVaccine;

    @Min(1)
    private long timeSlot;

    @NotNull
    private PayingType payingType;

    @NotNull
    @NotBlank
    @Email
    private String clintEmail;

    public String getVaccinationDay() {
        return vaccinationDay;
    }

    public void setVaccinationDay(String vaccinationDay) {
        this.vaccinationDay = vaccinationDay;
    }

    public long getBranchVaccine() {
        return branchVaccine;
    }

    public void setBranchVaccine(long branchVaccine) {
        this.branchVaccine = branchVaccine;
    }

    public long getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(long timeSlot) {
        this.timeSlot = timeSlot;
    }

    public PayingType getPayingType() {
        return payingType;
    }

    public void setPayingType(PayingType payingType) {
        this.payingType = payingType;
    }

    public String getClintEmail() {
        return clintEmail;
    }

    public void setClintEmail(String clintEmail) {
        this.clintEmail = clintEmail;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ScheduleVaccinationRequestDTO.class.getSimpleName() + "[", "]")
                .add("vaccinationDay=" + vaccinationDay)
                .add("branchVaccine=" + branchVaccine)
                .add("timeSlot=" + timeSlot)
                .add("payingType=" + payingType)
                .add("clintEmail=" + clintEmail)
                .toString();
    }
}
