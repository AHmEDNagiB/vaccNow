package com.naguib.technicalTasks.vaccNow.entity;

import com.naguib.technicalTasks.vaccNow.utils.PayingType;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Date;
import java.util.StringJoiner;

@Entity
@Table(name = "scheduled_vaccination")
public class ScheduledVaccination {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "vaccination_day", nullable = false)
    @Type(type = "date")
    private Date vaccinationDay;

    @ManyToOne
    @JoinColumn(name = "branch_vaccine_id", referencedColumnName = "id")
    private BranchVaccine branchVaccine;

    @ManyToOne
    @JoinColumn(name = "vaccination_time_slot_id", referencedColumnName = "id")
    private VaccinationTimeSlot timeSlot;

    @Column(columnDefinition = "enum( 'Cash', 'Credit', 'Fawry')", nullable = false)
    @Enumerated(EnumType.STRING)
    private PayingType payingType;

    @Column(name = "is_applied", columnDefinition = "enum('0', '1') default '0'", nullable = false, length = 1)
    private int isApplied;
    @Column(name = "clint_email")
    @Email
    private String clintEmail;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getVaccinationDay() {
        return vaccinationDay;
    }

    public void setVaccinationDay(Date vaccinationDay) {
        this.vaccinationDay = vaccinationDay;
    }

    public BranchVaccine getBranchVaccine() {
        return branchVaccine;
    }

    public void setBranchVaccine(BranchVaccine branchVaccine) {
        this.branchVaccine = branchVaccine;
    }

    public PayingType getPayingType() {
        return payingType;
    }

    public void setPayingType(PayingType payingType) {
        this.payingType = payingType;
    }

    public VaccinationTimeSlot getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(VaccinationTimeSlot timeSlot) {
        this.timeSlot = timeSlot;
    }

    public int getIsApplied() {
        return isApplied;
    }

    public void setIsApplied(int isApplied) {
        this.isApplied = isApplied;
    }

    public String getClintEmail() {
        return clintEmail;
    }

    public void setClintEmail(String clintEmail) {
        this.clintEmail = clintEmail;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ScheduledVaccination.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("vaccinationDay=" + vaccinationDay)
                .add("Branch=" + branchVaccine.getBranch().getName())
                .add("Vaccine=" + branchVaccine.getVaccine().getName())
                .add("timeSlotFrom=" + timeSlot.getTimeFrom())
                .add("timeSlotTo=" + timeSlot.getTimeTo())
                .add("payingType=" + payingType)
                .add("clintEmail=" + clintEmail)
                .toString();
    }
}
