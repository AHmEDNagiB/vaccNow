import {Component, OnInit} from '@angular/core';
import {BranchDTO} from '../shared/models/BranchDTO';
import {BranchMockDTO} from '../shared/models/BranchMockDTO';
import {HttpCallsService} from '../shared/services/http-calls.service';

@Component({
    selector: 'app-branches',
    templateUrl: './branches.component.html',
    styleUrls: ['./branches.component.css']
})
export class BranchesComponent implements OnInit {
    headerRow: string[];
    dataRows: BranchDTO[];
    branchesMockUp: BranchMockDTO[];
    selectedBranch = -1;
    isNoBranches = false;


    constructor(public httpCallService: HttpCallsService) {
    }

    ngOnInit() {
        this.headerRow = ['Name', 'Location', 'Available Vaccines', ''];
        this.getMockUps();
        this.getBranches();
    }

    getMockUps() {
        this.httpCallService.getBranchesMockUp().subscribe(res => {
            this.branchesMockUp = res.data;
        })
    }

    getBranches() {
        this.httpCallService.getBranches(this.selectedBranch).subscribe(res => {
            this.dataRows = res.data;
            if (this.dataRows.length > 0) {
                this.isNoBranches = false;
            } else {
                this.isNoBranches = true;
            }
        }, error => {
            console.log(error);
            this.isNoBranches = true;
        })
    }


    selectBranch(branchId) {
        this.selectedBranch = branchId;
    }

    filterByBranch() {
        if (this.selectedBranch === -1) {
            return;
        } else {
            this.getBranches();
        }
    }

}
