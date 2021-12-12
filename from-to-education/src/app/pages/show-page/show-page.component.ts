import {AfterViewInit, Component, OnInit, Output} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {RestService} from '../../services/rest.service';
import {InfoDialogComponent} from '../../common/info-dialog/info-dialog.component';
import {MatDialog} from '@angular/material/dialog';
import {DialogRequestComponent} from '../../common/dialog-request/dialog-request.component';
import {OkInformComponent} from '../../common/ok-inform/ok-inform.component';
import {MatSnackBar} from '@angular/material/snack-bar';

@Component({
  selector: 'app-show-page',
  templateUrl: './show-page.component.html',
  styleUrls: ['./show-page.component.css']
})
export class ShowPageComponent implements OnInit {

  firstFormGroup: FormGroup;
  @Output() program: any = [];
  choice: string;
  route: any = [];
  section: any = [];
  nameProgram: string;
  programs: any = [];
  currentSec: string;
  constructor(private formBuilder: FormBuilder,
              public dialog: MatDialog,
              private snackBar: MatSnackBar,
              public restService: RestService) { }

  ngOnInit(): void {
    this.clickSection();


  }



  choiceRoute(section: any): void{
    console.log('Выбрано направление' + section);
    // this.program = section.route;
    // this.choice = 'route';
    this.currentSec = section.name;

    this.restService.post('get_courses', {
      "section_id": section.id
    }).subscribe(
      result => {
        console.log(result);
        this.program = result.programs;
        this.route = result.programs;
        this.choice = 'route';
      }, err => {
      }
    );
  }

  choiceProgram(route: any): void{
    console.log(route);
    // this.program = route.program;
    // this.programs = route.program;
    // this.choice = 'program';

    this.restService.post('get_public_programs', {
      "course_id": route.id
    }).subscribe(
      result => {
        console.log(result);
        this.program = result.programs;
        this.choice = 'program';
      }, err => {
      }
    );
  }

  clickRoute(): void {
    this.program = this.route;
    this.choice = 'route';
  }

  clickProgram(): void {
    this.program = this.programs;
    this.choice = 'program';
  }

  clickSection(): void {
    // this.program = this.section;
    // this.choice = 'section';
    this.restService.post('get_sections', {
    }).subscribe(
      result => {
        console.log(result);
        this.program = result.programs;
        this.choice = 'section';
      }, err => {
      }
    );
  }
  goProgram(program): void{

    // this.nameProgram = program.name;

  }

  join(): void{
   // window.open();
  }

  createRequest(): void{
    const dialogRef = this.dialog.open(DialogRequestComponent, {
      width: '430px',
      autoFocus: false,
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
      if (result) {
        this.snackBar.open('Заявка отправлена', 'Ok', {  duration: 1000});
        // const timeout = 3000;
        // const dialogRef1 = this.dialog.open(OkInformComponent, {
        //   width: '380px',
        //   data: 'Заявка отправлена1',
        //   autoFocus: false,
        // });
        //
        // dialogRef1.afterOpened().subscribe(_ => {
        //   setTimeout(() => {
        //     dialogRef.close();
        //   }, timeout);
        // });
      }
    });
  }

}
