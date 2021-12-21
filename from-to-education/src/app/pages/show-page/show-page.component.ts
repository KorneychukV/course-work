import {AfterViewInit, Component, OnInit, Output} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {RestService} from '../../services/rest.service';
import {InfoDialogComponent} from '../../common/info-dialog/info-dialog.component';
import {MatDialog} from '@angular/material/dialog';
import {DialogRequestComponent} from '../../common/dialog-request/dialog-request.component';
import {OkInformComponent} from '../../common/ok-inform/ok-inform.component';
import {MatSnackBar} from '@angular/material/snack-bar';
import {Router} from '@angular/router';
import {AddNewSectionComponent} from '../administration/admin-page/dialogs/add-new-section/add-new-section.component';
import {EditSectionComponent} from '../administration/admin-page/dialogs/edit-section/edit-section.component';
import {AddProgramComponent} from '../administration/admin-page/dialogs/add-program/add-program.component';
import {EditProgramComponent} from '../administration/admin-page/dialogs/edit-program/edit-program.component';
import {AuthService} from '../../services/auth.service';

@Component({
  selector: 'app-show-page',
  templateUrl: './show-page.component.html',
  styleUrls: ['./show-page.component.css']
})
export class ShowPageComponent implements OnInit {


  firstFormGroup: FormGroup;
  @Output() program: any = [];
  choice: string;
  section: any = [];
  nameProgram: string;
  programs: any = [];
  currentSec: any;
  currentCourse: any;
  constructor(private formBuilder: FormBuilder,
              public dialog: MatDialog,
              public authService: AuthService,
              private router: Router,
              public restService: RestService) { }

  ngOnInit(): void {
    this.loadSection();
  }

  loadProgram(item: any): void{
    if (this.authService.getLoggedUser() === undefined) {
      this.restService.post('prof/getPrograms', {
        courseId: item.courseId
      }).subscribe(
        result => {
          console.log(result);
          this.program = result.list;
          this.choice = 'program';
          this.currentCourse =
            {
              name: item.name,
              courseId: item.courseId
            };
        }, err => {
        }
      );
    } else {
      this.restService.post('prof/user/getPrograms', {
        courseId: item.courseId
      }).subscribe(
        result => {
          console.log(result);
          this.program = result.list;
          this.choice = 'program';
          this.currentCourse =
            {
              name: item.name,
              courseId: item.courseId
            };
        }, err => {
        }
      );
    }
  }

  clickProgram(): void {
    this.program = this.programs;
    this.choice = 'program';
  }

  // загрузка всех направлений определенного раздела
  loadCourses(item: any): void {
    this.restService.post('prof/getCourses', {
      studySectionId: item.studySectionId
    }).subscribe(result => {
        this.program = result.list;
        this.choice = 'courses';
        this.currentSec =
          {
            name: item.name,
            studySectionId: item.studySectionId
          };
      }, err => {
      }
    );
  }

  buy(item): void{
    if (this.authService.getLoggedUser() === undefined) {
      this.authService.login();
    } else {
      this.restService.post('prof/user/buyProgram', {
        studyProgramId: item.studyProgramId
      }).subscribe(result => {

        }, err => {
        }
      );

      const dialogRef1 = this.dialog.open(OkInformComponent, {
        width: '380px',
        data: 'Поздравляю с покупкой'
      });
      dialogRef1.afterClosed().subscribe(res2 => {
        console.log('The dialog was closed');
      });
    }
  }

  // загрузка всех направлений
  loadSection(): void {
    this.restService.get('prof/getSection').subscribe(
      result => {
        this.program = result.list;
        this.choice = 'section';
      }, err => {
      }
    );
  }

  test(program: any): void {
    this.router.navigate(['admintest/' + program.id]);
  }

  question(id: number): void {
    this.router.navigate(['question/' + id]);
  }

}
