import {Routes} from '@angular/router';

import {ReportsComponent} from '../../reports/reports.component';
import {BranchesComponent} from '../../branches/branches.component';


export const AdminLayoutRoutes: Routes = [
    {path: 'branches', component: BranchesComponent},
    {path: 'reports', component: ReportsComponent}
];
