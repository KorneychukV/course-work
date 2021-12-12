import { BrowserModule } from '@angular/platform-browser';
import {APP_INITIALIZER, NgModule} from '@angular/core';

import { AppComponent } from './app.component';
import { LoginComponent } from './pages/login/login.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {RouterModule} from '@angular/router';
import {AppRoutingModule} from './app-routing.module';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import {MatButtonModule} from '@angular/material/button';
import { ShowPageComponent } from './pages/show-page/show-page.component';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatIconModule} from '@angular/material/icon';
import {MatSidenavModule} from '@angular/material/sidenav';
import {MenuComponent} from './common/menu/menu.component';
import {MatDialogModule} from '@angular/material/dialog';
import {CookieModule, CookieService} from 'ngx-cookie';
import { EducationComponent } from './pages/education/education.component';
import { ProgramComponent } from './pages/program/program.component';
import {FormatTimePipe, TestComponent} from './pages/test/test.component';
import {MatExpansionModule} from '@angular/material/expansion';
import { DialogComponent } from './pages/test/dialog/dialog.component';
import { ResultComponent } from './pages/result/result.component';
import {MatCardModule} from '@angular/material/card';
import { LkComponent } from './pages/lk/lk.component';
import {MatProgressBarModule} from '@angular/material/progress-bar';
import { InfoDialogComponent } from './common/info-dialog/info-dialog.component';
import { LiteratureComponent } from './pages/literature/literature.component';
import {MatTabsModule} from '@angular/material/tabs';
import { OkInformComponent } from './common/ok-inform/ok-inform.component';
import { RequestComponent } from './common/request/request.component';
import { DialogRequestComponent } from './common/dialog-request/dialog-request.component';
import { PhoneDirective } from './common/directive/phone.directive';
import { PageRequestComponent } from './pages/page-request/page-request.component';
import { StatisticsComponent } from './pages/administration/admin-page/statistics/statistics.component';
import {AgGridModule} from 'ag-grid-angular';
import { FinalProgramComponent } from './pages/final-program/final-program.component';
import { AdminTestComponent } from './pages/administration/admin-page/admin-test/admin-test.component';
import { FinalAdminTestComponent } from './pages/final-admin-test/final-admin-test.component';
import { MatSnackBarModule} from '@angular/material/snack-bar';
import { WebinarComponent } from './pages/webinar/webinar.component';
import {initializeKeycloak} from './services/keycloak-init.factory';
import {KeycloakAngularModule, KeycloakBearerInterceptor, KeycloakService} from 'keycloak-angular';
import {AuthGuard} from './services/guard/auth.guard';
import {AuthService} from './services/auth.service';
import { AdminPageComponent } from './pages/administration/admin-page/admin-page.component';
import { AddNewSectionComponent } from './pages/administration/admin-page/dialogs/add-new-section/add-new-section.component';
import { EditSectionComponent } from './pages/administration/admin-page/dialogs/edit-section/edit-section.component';

@NgModule({
    declarations: [
        AppComponent,
        LoginComponent,
        ShowPageComponent,
        MenuComponent,
        EducationComponent,
        ProgramComponent,
        TestComponent,
        FormatTimePipe,
        DialogComponent,
        ResultComponent,
        LkComponent,
        InfoDialogComponent,
        LiteratureComponent,
        OkInformComponent,
        RequestComponent,
        DialogRequestComponent,
        PhoneDirective,
        PageRequestComponent,
        StatisticsComponent,
        FinalProgramComponent,
        AdminTestComponent,
        FinalAdminTestComponent,
        WebinarComponent,
        AdminPageComponent,
        AddNewSectionComponent,
        EditSectionComponent,
    ],
    imports: [
        BrowserModule,
        BrowserModule,
        BrowserAnimationsModule,
        RouterModule,
        AppRoutingModule,
        HttpClientModule,
        ReactiveFormsModule,
        MatFormFieldModule,
        MatInputModule,
        MatButtonModule,
        FormsModule,
        MatToolbarModule,
        MatIconModule,
        MatSidenavModule,
        MatDialogModule,
        CookieModule.forRoot(),
        MatExpansionModule,
        MatCardModule,
        MatProgressBarModule,
        MatTabsModule,
        MatSnackBarModule,
        AgGridModule.withComponents([]),
      KeycloakAngularModule
    ],
  providers: [
    AuthGuard,
    AuthService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: KeycloakBearerInterceptor,
      multi: true
    },
    {
      provide: APP_INITIALIZER,
      useFactory: initializeKeycloak,
      multi: true,
      deps: [KeycloakService],
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
