import {Component, Input, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {RestService} from '../../../../services/rest.service';
import {MatDialog} from '@angular/material/dialog';
import {environment} from '../../../../../environments/environment';
import {AddQuestionComponent} from '../question/dialogs/add-question/add-question.component';
import {DialogLiterComponent} from './dialog-liter/dialog-liter.component';

@Component({
  selector: 'app-add-literature',
  templateUrl: './add-literature.component.html',
  styleUrls: ['./add-literature.component.css']
})
export class AddLiteratureComponent implements OnInit {

  literatures: any;
  textSearch: string | undefined;
  @Input() idProgram: number;
  constructor(private router: Router,
              public restService: RestService,
              public dialog: MatDialog) { }

  ngOnInit(): void {
    this.loadLiterature();
  }

  search(): void{
    this.loadLiterature();
  }

  loadLiterature(): void{
    this.restService.post('prof/getLiterature', {
      studyProgramId: this.idProgram,
      filter: this.textSearch
    }).subscribe(
      result => {
        this.literatures = result.list;
        this.literatures.map( l => {
          l.status = false;
        });
      }, error => {
      }
    );
  }

  openLink(link: string): void {
    window.open(link);
  }

  clickEvent(lit): void{
    lit.status = !lit.status;
  }

  fileDownload(link): void {
    window.open(environment.picUrl + '/media/' +  link, '_blank');
  }

  addLiter(): void{
    const dialogRef = this.dialog.open(DialogLiterComponent, {
      width: '660px',
      autoFocus: false,
      data: this.idProgram
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
      if (result) {
        this.loadLiterature();
      }
    });
  }


}
