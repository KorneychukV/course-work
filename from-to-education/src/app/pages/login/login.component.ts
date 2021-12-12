import {Component, Inject, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {Router, RouterModule} from '@angular/router';
import {RestService} from '../../services/rest.service';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  error = '';

  username = '';
  password = '';

  constructor(private restService: RestService,
              public dialogRef: MatDialogRef<LoginComponent>,
              private router: Router,
              @Inject(MAT_DIALOG_DATA) public data: string) { }

  ngOnInit(): void {
  }

  login(): void {
    // if (this.username === '' || this.username === '-') {
    //   this.error = 'Имя пользователя не должно быть пустое';
    //   return;
    // }
    // if (this.password === '') {
    //   this.error = 'Пароль не должен быть пустым';
    //   return;
    // }
    // this.restService.post('login', {
    //   username: this.username,
    //   pswd: this.password
    // }).subscribe(
    //   result => {
    //     console.log(result);
    //     this.dialogRef.close();
    //     this.auth.setSuper(result.is_superuser);
    //     this.auth.setSessuinid(result.session_key);
    //     this.restService.post('get_user_info', {
    //     }).subscribe(
    //       res => {
    //         console.log(res);
    //         this.auth.setUser(res.user.lastname, res.user.firstname);
    //       }, error => {
    //       }
    //     );
    //     this.router.navigate(['education']);
    //   }, error => {
    //     //this.logger.error('Login error', error);
    //     this.error = error.error_message;
    //   }
    // );
  }

  test() {
    this.restService.get('login_test', {
      username: this.username,
      pswd: this.password
    }).subscribe();
  }

  cancel() {
    document.getElementById('id01').style.display='none';
  }

  onEnterKeyDown($event) {
    this.login();
  }
}
