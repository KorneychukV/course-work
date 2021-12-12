import {Component, OnDestroy, OnInit, Pipe, PipeTransform} from '@angular/core';
import {RestService} from '../../services/rest.service';
import {ActivatedRoute, Router} from '@angular/router';
import {Subscription, timer} from 'rxjs';
import {MatDialog} from '@angular/material/dialog';
import {DialogComponent} from './dialog/dialog.component';
import {InfoDialogComponent} from '../../common/info-dialog/info-dialog.component';
import {error} from 'protractor';
import {OkInformComponent} from '../../common/ok-inform/ok-inform.component';
import {environment} from '../../../environments/environment';

@Component({
  selector: 'app-test',
  templateUrl: './test.component.html',
  styleUrls: ['./test.component.css']
})
export class TestComponent implements OnInit, OnDestroy {

  question;
  next = true;
  testId: number;
  tryAnswerId: number;
  countDown: Subscription;
  counter: number;
 // counter = 5;
  tick = 1000;
  progress: any;
  path: string;

  constructor(private restService: RestService,
              private activatedRoute: ActivatedRoute,
              public dialog: MatDialog,
              private router: Router) { }

  ngOnInit(): void {
    this.path = environment.picUrl;
    console.log(this.activatedRoute.snapshot.params.id);
    this.restService.post('start_test', {
      "program_id": this.activatedRoute.snapshot.params.id,
      "is_final": this.activatedRoute.snapshot.params.type
    }).subscribe(
      result => {
        this.testId = result.test.test_id;
        this.counter = result.test.test_time * 60;
        this.getQuestion();
      }, err => {
        console.log(err);
        this.tick = 0;
        this.router.navigate(['education']);
        const dialogRef = this.dialog.open(OkInformComponent, {
          width: '380px',
          data: err.message,
          autoFocus: false,
        });

        dialogRef.afterClosed().subscribe(result => {
          console.log('The dialog was closed');
        });
      }
    );

    this.countDown = timer(0, this.tick).subscribe(() => {
      --this.counter;
      if (this.counter === 0) {
        this.countDown.unsubscribe();
        const dialogRef = this.dialog.open(DialogComponent, {
        });

        dialogRef.afterClosed().subscribe(resu => {
          this.router.navigate(['education']);
        });
      }
    });
  }
  ngOnDestroy(){
    this.countDown = null;
  }

  onClick(answer: any) {
    this.next = false;
    this.question.answers.filter(ans => {
      ans.select = ans === answer;
    });
  }

  exit() {
    const dialogRef = this.dialog.open(InfoDialogComponent, {
      width: '380px',
      data: 'Вы действиельно хотите выйти?',
      autoFocus: false,
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
      if (result) {
        this.router.navigate(["/education"]);
      }
    });
  }

  getQuestion() {
    this.restService.post('get_question', {
      'test_id': this.testId
    }).subscribe(
      res => {
        console.log(res);
        this.progress = (100 / res.test.amount) * res.test.curr;
        this.question = res.test;
        this.tryAnswerId = res.test.try_answer_id;
      }, error => {
      }
    );
    this.question.answers.filter(ans => {
      ans.select = false;
    });
  }

  getResult() {
    this.putAnswer();
    this.router.navigate(['result/' + this.testId]);
  }

  putAnswer(){
    this.next = true;
    this.question.answers.map(ans => {
      console.log(ans);
      if (ans.select === true) {
        this.restService.post('put_answer', {
          "answer_id": ans.id,
          "try_answer_id": this.tryAnswerId
        }).subscribe(
          result => {
            this.getQuestion();
          });
      }
    });
  }

}

@Pipe({
  name: "formatTime"
})
export class FormatTimePipe implements PipeTransform {
  transform(value: number): string {
    const minutes: number = Math.floor(value / 60);
    return (
      ('00' + minutes).slice(-2) +
      ':' +
      ('00' + Math.floor(value - minutes * 60)).slice(-2)
    );
  }
}
