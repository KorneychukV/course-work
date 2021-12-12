import { Component, OnInit } from '@angular/core';
import {RestService} from '../../services/rest.service';

@Component({
  selector: 'app-lk',
  templateUrl: './lk.component.html',
  styleUrls: ['./lk.component.css']
})
export class LkComponent implements OnInit {

  constructor(private restService: RestService) { }

  ngOnInit(): void {
    this.restService.get('get_user_info', {
    }).subscribe(
      result => {
        console.log(result);
      }, error => {
      }
    );
  }

}
