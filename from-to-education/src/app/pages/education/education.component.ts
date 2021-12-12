import {AfterViewInit, Component, OnInit} from '@angular/core';
import {RestService} from '../../services/rest.service';
import {Program} from '../../classes/Program';
import {ActivatedRoute, Router} from '@angular/router';
import {InfoDialogComponent} from '../../common/info-dialog/info-dialog.component';
import {MatDialog} from '@angular/material/dialog';

@Component({
  selector: 'app-education',
  templateUrl: './education.component.html',
  styleUrls: ['./education.component.css']
})
export class EducationComponent implements OnInit, AfterViewInit {

  eduPrograms = [];
  stat =  'false';
  buttons = [
    {
      name: 'Пробное тестирование',
      action: (id: number) => {
        this.test(id);
      }
    },
    {
      name: 'Итоговое тестирование',
      action: (id: number) => {
        this.finish(id);
      }
    },
    {
      name: 'Литература',
      action: (id: number) => {
        this.getLiterature(id);
      }
    }
  ];

  constructor(private restService: RestService,
              public dialog: MatDialog,
              private router: Router) { }

  ngAfterViewInit(): void {
    // this.stat = this.auth.getSuper();
  }

  ngOnInit(): void {
    this.restService.get('get_programs', {
    }).subscribe(
      result => {
        console.log(result);
        this.eduPrograms = result.programs;
        this.eduPrograms.map(v => {
          v.panelOpenState = false;
          if (v.is_complete === true) {
            v.color = '#2ca632';
          } else {
            v.color = '#5ecdb4';
          }
        });
      }, error => {
      }
    );
  }

  goProgram(id) {
    this.router.navigate(['program/:' + id]);
  }

  test(id: number) {
    console.log('test');
    this.router.navigate(['test/' + id + '/False']);
  }

  finish(id: number) {
    const dialogRef = this.dialog.open(InfoDialogComponent, {
      width: '380px',
      data: 'Вы действиельно хотите пройти итоговый тест?',
      autoFocus: false,
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
      if (result) {
        this.router.navigate(['test/' + id + '/True']);
      }
    });
  }

  getLiterature(id: number) {
    this.router.navigate(['literature/' + id]);
  }

  getResult(id: number) {
    this.router.navigate(['final']);
  }
}
