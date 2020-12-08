import {Component, OnInit} from '@angular/core';
import {HttpCallsService} from '../shared/services/http-calls.service';
import {Observable} from 'rxjs';
import {ScheduledVaccination} from '../shared/models/ScheduledVaccination';
import {ResponseDTO} from '../shared/models/ResponseDTO';
import {BranchMockDTO} from '../shared/models/BranchMockDTO';

@Component({
    selector: 'app-reports',
    templateUrl: './reports.component.html',
    styleUrls: ['./reports.component.css']
})
export class ReportsComponent implements OnInit {
    isNoDataFound = false;
    typeSelected = 0;
    headerRow: string[];
    dataRows: ScheduledVaccination[];
    branchesMockUp: BranchMockDTO[];
    selectedBranch = -1;
    selectedDateTo = '';
    selectedDateFrom = '';


    constructor(public httpCallsService: HttpCallsService) {
    }

    ngOnInit() {
        this.headerRow = ['Branch Name', 'Location', 'Date', 'Clint Email', 'Paying'];
        this.getMockUps();
        this.getVaccinations();

    }

    getMockUps() {
        this.httpCallsService.getBranchesMockUp().subscribe(res => {
            this.branchesMockUp = res.data;
        })
    }

    selectType(value: any) {
        console.log(value)
        this.typeSelected = value;
    }

    filterByType() {
        this.getVaccinations();
    }

    selectBranch(branchId) {
        this.selectedBranch = branchId;
    }

    getVaccinations() {

        let fun: Observable<ResponseDTO<ScheduledVaccination[]>>;
        console.log('typeSelected : ' + this.typeSelected)
        if (this.typeSelected == 0) {
            fun = this.httpCallsService.getConfirmedVaccination(this.getParams());
        } else {
            fun = this.httpCallsService.getAppliedVaccination(this.getParams());
        }
        fun.subscribe(res => {
            this.dataRows = res.data;
            if (this.dataRows.length > 0) {
                this.isNoDataFound = false;
            } else {
                this.isNoDataFound = true;
            }
        }, error => {
            console.log(error);
            this.isNoDataFound = true;
        })
    }

    private getParams() {
        let params = '';
        params = (this.selectedBranch > 0) ? 'branch=' + this.selectedBranch : params;
        params = (this.selectedDateTo !== '') ? params + '&&dateTo=' + this.selectedDateTo : params;
        params = (this.selectedDateFrom !== '') ? params + '&&dateFrom=' + this.selectedDateFrom : params;
        params = (params === '') ? '' : '?' + params;
        return params;
    }

    selectDateFrom(value: any) {
        this.selectedDateFrom = value;
    }

    selectDateTo(value: any) {
        this.selectedDateTo = value;
    }
}
