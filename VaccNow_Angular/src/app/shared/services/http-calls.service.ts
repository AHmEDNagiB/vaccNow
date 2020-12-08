import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable, of} from 'rxjs';
import {environment} from '../../../environments/environment';
import {ResponseDTO} from '../models/ResponseDTO';
import {BranchMockDTO} from '../models/BranchMockDTO';
import {BranchDTO} from '../models/BranchDTO';
import {ScheduledVaccination} from '../models/ScheduledVaccination';

@Injectable({
    providedIn: 'root'
})
export class HttpCallsService {

    constructor(public http: HttpClient) {
    }


    getBranchesMockUp(): Observable<ResponseDTO<BranchMockDTO[]>> {
        return this.http.get<ResponseDTO<BranchMockDTO[]>>(environment.hostUrl + '/branch/mock-up');
    }

    getBranches(selectedBranch: number): Observable<ResponseDTO<BranchDTO[]>> {
        return this.http.get<ResponseDTO<BranchDTO[]>>(environment.hostUrl + '/branch'
            + ((selectedBranch > 0) ? '?branch=' + selectedBranch : ''));
    }

    getAppliedVaccination(params: string): Observable<ResponseDTO<ScheduledVaccination[]>> {
        return this.http.get<ResponseDTO<ScheduledVaccination[]>>(environment.hostUrl + '/vaccination/applied' + params);
    }

    getConfirmedVaccination(params: string): Observable<ResponseDTO<ScheduledVaccination[]>> {
        return this.http.get<ResponseDTO<ScheduledVaccination[]>>(environment.hostUrl + '/vaccination/confirmed' + params);

    }
}
