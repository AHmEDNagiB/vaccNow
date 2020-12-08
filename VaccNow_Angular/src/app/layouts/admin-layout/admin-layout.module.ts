import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { NguiMapModule} from '@ngui/map';

import { AdminLayoutRoutes } from './admin-layout.routing';

import { ReportsComponent } from '../../reports/reports.component';
import {BranchesComponent} from '../../branches/branches.component';


@NgModule({
  imports: [
    CommonModule,
    RouterModule.forChild(AdminLayoutRoutes),
    FormsModule
  ],
  declarations: [
    ReportsComponent,
    BranchesComponent
  ]
})

export class AdminLayoutModule {}
