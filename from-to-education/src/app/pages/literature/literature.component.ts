import { Component, OnInit } from '@angular/core';
import {RestService} from '../../services/rest.service';
import {MatDialog} from '@angular/material/dialog';
import {ActivatedRoute, Router} from '@angular/router';
import {DomSanitizer} from '@angular/platform-browser';
import {environment} from '../../../environments/environment';

@Component({
  selector: 'app-literature',
  templateUrl: './literature.component.html',
  styleUrls: ['./literature.component.css']
})
export class LiteratureComponent implements OnInit {

  literatures: any;
  status = false;
  displayURL;
  constructor(private restService: RestService,
              public dialog: MatDialog,
              private activatedRoute: ActivatedRoute,
              private router: Router,
              public sanitizer: DomSanitizer) {
    this.displayURL = sanitizer.bypassSecurityTrustResourceUrl('https://www.youtube.com/embed/tgbNymZ7vqY');
  }

  ngOnInit(): void {
    this.restService.post('get_literature', {
      "program_id": this.activatedRoute.snapshot.params.id,
    }).subscribe(
      result => {
        this.literatures = result.literature;
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

  clickEvent(lit){
    lit.status = !lit.status;
  }

  fileDownload(link) {
    window.open(environment.picUrl + '/media/' +  link, '_blank');
  }

}
