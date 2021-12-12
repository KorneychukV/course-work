import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {LoginComponent} from './pages/login/login.component';
import {ShowPageComponent} from './pages/show-page/show-page.component';
import {EducationComponent} from './pages/education/education.component';
import {ProgramComponent} from './pages/program/program.component';
import {TestComponent} from './pages/test/test.component';
import {ResultComponent} from './pages/result/result.component';
import {LiteratureComponent} from './pages/literature/literature.component';
import {RequestComponent} from './common/request/request.component';
import {PageRequestComponent} from './pages/page-request/page-request.component';
import {StatisticsComponent} from './pages/administration/admin-page/statistics/statistics.component';
import {FinalProgramComponent} from './pages/final-program/final-program.component';
import {FinalAdminTestComponent} from './pages/final-admin-test/final-admin-test.component';
import {WebinarComponent} from './pages/webinar/webinar.component';
import {AuthGuard} from './services/guard/auth.guard';
import {AdminPageComponent} from './pages/administration/admin-page/admin-page.component';

const routes: Routes = [
  { path: '',
    redirectTo: '/show',
    pathMatch: 'full',
  },
  {
    path: 'show',
    component: ShowPageComponent
  },
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: 'final',
    component: FinalProgramComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'education',
    component: EducationComponent,
    // canActivate: [AuthGuard]
  },
  {
    path: 'administration',
    component: AdminPageComponent,
    canActivate: [AuthGuard],
    data: { roles: ['admin'] }
  },
  {
    path: 'request',
    component: PageRequestComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'webinar',
    component: WebinarComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'program',
    component: ProgramComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'test/:id/:type',
    component: TestComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'result/:id',
    component: ResultComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'literature/:id',
    component: LiteratureComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'admintest/:id',
    component: FinalAdminTestComponent
  }
];
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
