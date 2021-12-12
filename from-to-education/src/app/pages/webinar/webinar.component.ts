import { Component, OnInit } from '@angular/core';
import {DomSanitizer} from '@angular/platform-browser';
import {RestService} from '../../services/rest.service';

@Component({
  selector: 'app-webinar',
  templateUrl: './webinar.component.html',
  styleUrls: ['./webinar.component.css']
})
export class WebinarComponent implements OnInit {

  displayURL;
  webinars;
  constructor(private sanitizer: DomSanitizer,
              private restService: RestService) {
    this.displayURL = sanitizer.bypassSecurityTrustResourceUrl('https://www.youtube.com/embed/tgbNymZ7vqY');
  }

  ngOnInit(): void {
    this.restService.post('get_webinars', {
    }).subscribe(
      result => {
        this.webinars = result.webinars;
      }, error => {
      }
    );
  }

}
