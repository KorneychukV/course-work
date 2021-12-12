import { Component, OnInit } from '@angular/core';
import {RestService} from '../../services/rest.service';
import {ActivatedRoute, Router} from '@angular/router';
import {MatDialog} from '@angular/material/dialog';

@Component({
  selector: 'app-result',
  templateUrl: './result.component.html',
  styleUrls: ['./result.component.css']
})
export class ResultComponent implements OnInit {

  result;
  status = false;
  constructor(private restService: RestService,
              private activatedRoute: ActivatedRoute,
              public dialog: MatDialog,
              private router: Router) { }

  ngOnInit(): void {
    // this.restService.getLocalReportData('data.json')
    //   .then(data => {
    //     console.log(data);
    //     this.result = data;
    //   })
    //   .catch(e => console.log(e));
    this.restService.post('get_result', {
      'test_id': this.activatedRoute.snapshot.params.id
    }).subscribe(
      res => {
        console.log(res);
        this.result = res.result;
      }, error => {
      }
    );
  }


  clickEvent(){
    this.status = !this.status;
  }
}
