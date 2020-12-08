package com.naguib.technicalTasks.vaccNow.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

@Entity
@Table(name = "Branch")
public class Branch {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "branch_name", nullable = false)
    private String name;
    @Column(name = "branch_location", nullable = false)
    private String location;
    @JsonIgnore
    @OneToMany(mappedBy = "branch", cascade = CascadeType.ALL)
    @Where(clause = "vaccine_count > 0")
    private List<BranchVaccine> branchVaccines;


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

    public List<BranchVaccine> getBranchVaccines() {
        return branchVaccines;
    }

    public void setBranchVaccines(List<BranchVaccine> branchVaccines) {
        this.branchVaccines = branchVaccines;
    }

    public String getAvailableVaccines() {
        return this.branchVaccines.stream().map(bv -> bv.getVaccine().getName()).collect(Collectors.joining(" , "));
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Branch.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .toString();
    }

}
