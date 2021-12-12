import {Component, OnInit, Output} from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
import {MatDialog} from '@angular/material/dialog';
import {RestService} from '../../services/rest.service';
import {DialogRequestComponent} from '../../common/dialog-request/dialog-request.component';
import {Router} from '@angular/router';

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

  test(program: any): void {
    this.router.navigate(['admintest/' + program.id]);
  }

}
