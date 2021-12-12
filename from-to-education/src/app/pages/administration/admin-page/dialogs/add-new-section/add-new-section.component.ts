import { Component, OnInit } from '@angular/core';
import {MatDialog, MatDialogRef} from '@angular/material/dialog';
import {RestService} from '../../../../../services/rest.service';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {OkInformComponent} from '../../../../../common/ok-inform/ok-inform.component';

@Component({
  selector: 'app-add-new-section',
  templateUrl: './add-new-section.component.html',
  styleUrls: ['./add-new-section.component.css']
})
export class AddNewSectionComponent implements OnInit {

  form: FormGroup;
  err = false;
  errText: string;

  constructor(public dialogRef: MatDialogRef<AddNewSectionComponent>,
              public restServise: RestService,
              public dialog: MatDialog) { }

  ngOnInit(): void {
    this.form =  new FormGroup({
      "name": new FormControl(null, Validators.required),
      "description": new FormControl(null, Validators.required)
    });
  }

  onNoClick(): void {
    this.dialogRef.close(false);
  }

  addSec() {
    this.restServise.post('prof/edu/addStudySection', {
      "name": this.form.get('name').value,
      "description": this.form.get('description').value
    }).subscribe(
      result => {
        if (result.type === 'ok') {
          this.dialogRef.close(true);
          const dialogRef = this.dialog.open(OkInformComponent, {
            width: '380px',
            data: result.message
          });
          dialogRef.afterClosed().subscribe(result => {
            console.log('The dialog was closed');
          });
        } else if (result.type === 'error') {
          this.err = true;
          this.errText = result.message;
        }
      }, err => {
      }
    );
  }
}
