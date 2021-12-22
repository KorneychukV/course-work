import {Component, OnInit, Output} from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
import {MatDialog} from '@angular/material/dialog';
import {RestService} from '../../../../services/rest.service';
import {DialogRequestComponent} from '../../../../common/dialog-request/dialog-request.component';
import {ActivatedRoute, Router} from '@angular/router';
import {AddNewSectionComponent} from '../dialogs/add-new-section/add-new-section.component';
import {EditSectionComponent} from '../dialogs/edit-section/edit-section.component';
import {AddProgramComponent} from '../dialogs/add-program/add-program.component';
import {EditProgramComponent} from '../dialogs/edit-program/edit-program.component';
import {InfoDialogComponent} from '../../../../common/info-dialog/info-dialog.component';
import {OkInformComponent} from '../../../../common/ok-inform/ok-inform.component';
import {AuthService} from '../../../../services/auth.service';

@Component({
  selector: 'app-admin-test',
  templateUrl: './admin-test.component.html',
  styleUrls: ['./admin-test.component.css']
})
export class AdminTestComponent implements OnInit {


  firstFormGroup: FormGroup;
  @Output() program: any = [];
  choice: string;
  section: any = [];
  nameProgram: string;
  programs: any = [];
  currentSec: any;
  currentCourse: any;
  currentQuestion: any;
  currentLit: any;
  permission = false;
  constructor(private formBuilder: FormBuilder,
              public dialog: MatDialog,
              private router: Router,
              private authService: AuthService,
              public restService: RestService,
              private activateRoute: ActivatedRoute) { }

  ngOnInit(): void {
   const course = this.activateRoute.snapshot.params.prog;
   console.log(course);
   if (course !== course) {
   }
   this.authService.getRoles().map(role => {
      if (role === 'admin') {
        this.permission = true;
      }
    });
   this.loadSection();
  }

  loadProgram(item: any): void{
    this.restService.post('prof/edu/getPrograms', {
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

  clickProgram(): void {
    this.program = this.programs;
    this.choice = 'program';
  }

  // загрузка всех направлений определенного раздела
  loadCourses(item: any): void {
    this.restService.post('prof/edu/getCourses', {
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

  // загрузка всех направлений
  loadSection(): void {
    this.restService.get('prof/edu/getSection').subscribe(
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

  question(item: any): void {
    this.currentQuestion =
      {
        name: item.name,
        id: item.studyProgramId
      };
    this.choice = 'question';
    //this.router.navigate(['question/' + this.currentCourse.courseId + '/' + id]);
  }

  literature(item: any): void {
    this.currentLit =
      {
        name: item.name,
        id: item.studyProgramId
      };
    this.choice = 'liter';
    // this.router.navigate(['add-liter/' + id]);
  }

  // добавление нового раздела/курса
  addNewSec(razdel: string): void{
    let data;
    if (razdel === 'section') {
      data = {
        sec: razdel
      };
    } else {
      data = {
        sec: razdel,
        id: this.currentSec.studySectionId
      };
    }
    const dialogRef = this.dialog.open(AddNewSectionComponent, {
      width: '460px',
      autoFocus: false,
      data
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
      console.log(razdel);
      if ((razdel === 'section') || result) {
        this.loadSection();
      } else  if ((razdel === 'courses') || result) {
        this.loadCourses(this.currentSec);
      }
    });
  }

  deleteSec(item: any): void{
    const dialogRef = this.dialog.open(InfoDialogComponent, {
      width: '380px',
      data: 'Вы действиельно хотите удалить раздел?',
      autoFocus: false,
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
      if (result) {
        this.restService.post('prof/edu/deleteStudySection', {
          studySectionId: item.studySectionId
        }).subscribe(res => {
            if (result.type === 'ok') {
              this.loadSection();
            }
          }, err => {
          }
        );
      }
    });
  }

  // редактирование направления/курса
  editSec(item: any, razdel: string): void{
    let data;
    if (razdel === 'section') {
      data = {
        sec: razdel,
        item
      };
    } else {
      data = {
        sec: razdel,
        id: this.currentSec.studySectionId,
        item
      };
    }
    const dialogRef = this.dialog.open(EditSectionComponent, {
      width: '460px',
      autoFocus: false,
      data
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
      console.log(razdel);
      if ((razdel === 'section') && result) {
        this.loadSection();
      } else  if ((razdel === 'courses') && result) {
        this.loadCourses(this.currentSec);
      }
    });
  }

  deleteCourses(item: any): void{
    const dialogRef = this.dialog.open(InfoDialogComponent, {
      width: '380px',
      data: 'Вы действиельно хотите удалить направление?',
      autoFocus: false,
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
      if (result) {
        this.restService.post('prof/edu/deleteCourses', {
          courseId: item.courseId
        }).subscribe(res => {
            if (result.type === 'ok') {
              this.loadSection();
            }
          }, err => {
          }
        );
      }
    });
  }

  deleteProgram(item: any): void{
    const dialogRef = this.dialog.open(InfoDialogComponent, {
      width: '380px',
      data: 'Вы действиельно хотите удалить программу?',
      autoFocus: false,
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
      if (result) {
        this.restService.post('prof/edu/deleteProgram', {
          studyProgramId: item.studyProgramId
        }).subscribe(res => {
            if (result.type === 'ok') {
              this.loadSection();
            }
          }, err => {
          }
        );
      }
    });
  }

  addProgram(): void{
    const dialogRef = this.dialog.open(AddProgramComponent, {
      width: '460px',
      autoFocus: false,
      data: this.currentCourse.courseId
    });

    dialogRef.afterClosed().subscribe(result => {
      this.loadProgram(this.currentCourse);
    });
  }

  editProgram(item: any): void {
    const dialogRef = this.dialog.open(EditProgramComponent, {
      width: '460px',
      autoFocus: false,
      data: item
    });

    dialogRef.afterClosed().subscribe(result => {
      this.loadProgram(this.currentCourse);
    });
  }


}
