import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {RestService} from '../../services/rest.service';

@Component({
  selector: 'app-program',
  templateUrl: './program.component.html',
  styleUrls: ['./program.component.css']
})
export class ProgramComponent implements OnInit {

  constructor(private activatedRoute: ActivatedRoute,
              private router: Router,
              private restService: RestService) { }

  buttons = [
    {
      name: 'Пробное тестирование',
      action: () => {
        this.test();
      }
    },
    {
      name: 'Итоговое тестирование',
      action: () => {
        this.finish();
      }
    }
    ];

  ngOnInit(): void {
    const id = this.activatedRoute.snapshot.params.id || 0;
    console.log(id);
  }

  test() {
    console.log('test');
    this.router.navigate(['test/' + this.activatedRoute.snapshot.params.id]);
  }

  finish() {
    console.log('finish');
  }
}
