export class ScheduledVaccination {
    vaccinationDay: Date;
    branchVaccine: BranchVaccine;
    timeSlot: VaccinationTimeSlot;
    clintEmail: string;
    payingType: string
}

export class BranchVaccine {
    branch: Branch;
    vaccine: Vaccine
}

export class Branch {
    name: String;
    location: String
}

export class Vaccine {
    name: String

}

export class VaccinationTimeSlot {
    timeFrom: String;
    timeTo: String;

}

