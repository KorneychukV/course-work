import {Component, OnInit, Output} from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
import {MatDialog} from '@angular/material/dialog';
import {RestService} from '../../../../services/rest.service';
import {DialogRequestComponent} from '../../../../common/dialog-request/dialog-request.component';
import {Router} from '@angular/router';
import {AddNewSectionComponent} from '../dialogs/add-new-section/add-new-section.component';
import {EditSectionComponent} from '../dialogs/edit-section/edit-section.component';

@Component({
  selector: 'app-admin-test',
  templateUrl: './admin-test.component.html',
  styleUrls: ['./admin-test.component.css']
})
export class AdminTestComponent implements OnInit {


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
              private router: Router,
              public restService: RestService) { }

  ngOnInit(): void {
    this.loadSection();
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

  loadSection(): void {
    this.restService.get('prof/edu/getSection').subscribe(
      result => {
        console.log(result);
        this.program = result.list;
        this.choice = 'section';
      }, err => {
      }
    );
  }

  test(program: any): void {
    this.router.navigate(['admintest/' + program.id]);
  }

  addNewSec(): void{
    const dialogRef = this.dialog.open(AddNewSectionComponent, {
      width: '460px',
      autoFocus: false
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
      if (result) {
        this.loadSection();
      }
    });
  }

  editSec(item: any): void{
    const dialogRef = this.dialog.open(EditSectionComponent, {
      width: '460px',
      autoFocus: false,
      data: item
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
      if (result) {
        this.loadSection();
      }
    });
  }

}
