import {Component, OnInit, Output} from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
import {MatDialog} from '@angular/material/dialog';
import {ActivatedRoute, Router} from '@angular/router';
import {AuthService} from '../../services/auth.service';
import {RestService} from '../../services/rest.service';
import {InfoDialogComponent} from '../info-dialog/info-dialog.component';
import {EditSectionComponent} from '../../pages/administration/admin-page/dialogs/edit-section/edit-section.component';

@Component({
  selector: 'app-razdel',
  templateUrl: './razdel.component.html',
  styleUrls: ['./razdel.component.css']
})
export class RazdelComponent implements OnInit {

  choice: string;
  program: any = [];
  permission = false;

  constructor(private formBuilder: FormBuilder,
              public dialog: MatDialog,
              private router: Router,
              private authService: AuthService,
              public restService: RestService,
              private activateRoute: ActivatedRoute) { }

  ngOnInit(): void {
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


}
