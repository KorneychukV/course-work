import {Component, OnInit, Output, ViewChild} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {RestService} from '../../services/rest.service';
import {OkInformComponent} from '../ok-inform/ok-inform.component';
import {MatDialog} from '@angular/material/dialog';

@Component({
  selector: 'app-request',
  templateUrl: './request.component.html',
  styleUrls: ['./request.component.css']
})
export class RequestComponent implements OnInit {

  form: FormGroup;
  selectedFile: File = undefined;
  file: File = null; //
  constructor(public dialog: MatDialog,
              private restService: RestService) { }

  ngOnInit(): void {
    this.form =  new FormGroup({
      "name": new FormControl(null, Validators.required),
      "organization": new FormControl(null, Validators.required),
      "phone": new FormControl(null, Validators.required),
      "email": new FormControl(null, [Validators.required, Validators.email]),
      "request_text": new FormControl(''),
      "file": new FormControl(null)
    });
  }

  request(): void {
      this.restService.postFile('put_order', {
        'fio': this.form.get('name').value,
        'organization': this.form.get('organization').value,
        'email': this.form.get('email').value,
        'phone': this.form.get('phone').value,
        'request_text': this.form.get('request_text').value,
      }, this.selectedFile).subscribe(
        res => {
          console.log(res);
          const timeout = 3000;
          const dialogRef = this.dialog.open(OkInformComponent, {
            width: '380px',
            data: 'Заявка отправлена',
            autoFocus: false,
          });

          dialogRef.afterOpened().subscribe(_ => {
            setTimeout(() => {
              dialogRef.close();
            }, timeout);
          });
        }, error => {
        }
      );
    }


  csvInputChange(fileInputEvent: any) {
    console.log(fileInputEvent.target.files[0]);
    this.selectedFile = fileInputEvent.target.files[0];
  }

  onFileInput(event): void {
    console.log(event.target.files);
    if (event.target.files.length !== 0) {
      this.selectedFile = event.target.files[0];
    } else {
    }
  }
}
