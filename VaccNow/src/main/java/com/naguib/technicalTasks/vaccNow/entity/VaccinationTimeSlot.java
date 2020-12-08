package com.naguib.technicalTasks.vaccNow.entity;

import javax.persistence.*;

@Entity
@Table(name = "vaccination_time_slots")
public class VaccinationTimeSlot {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "time_from", nullable = false)
    private String timeFrom;
    @Column(name = "time_to", nullable = false)
    private String timeTo;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTimeFrom() {
        return timeFrom;
    }

    public void setTimeFrom(String timeFrom) {
        this.timeFrom = timeFrom;
    }

    public String getTimeTo() {
        return timeTo;
    }

    public void setTimeTo(String timeTo) {
        this.timeTo = timeTo;
    }
}
