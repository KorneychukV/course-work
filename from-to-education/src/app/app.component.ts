import {Component, OnInit} from '@angular/core';
import {Menu} from './classes/Menu';
import {MatDialog} from '@angular/material/dialog';
import {LoginComponent} from './pages/login/login.component';
import {RestService} from './services/rest.service';
import {environment} from '../environments/environment';
import {AuthGuard} from './services/guard/auth.guard';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent  implements OnInit {
  title = 'from-to-education';
  user: any;
  public menuList: Menu[] = [];
  certificates;

  constructor(public dialog: MatDialog,
              private restService: RestService,
              public authGuard: AuthGuard) {
  }

  ngOnInit(): void {
    console.log(this.authGuard.checkAuth());

    this.restService.post('get_certificates', {
    }).subscribe(
      result => {
        console.log(result);
        this.certificates = result.certificates;
        console.log(this.certificates);
      }, error => {
      }
    );

    this.menuList = APP_MENU;
    const roles = '';
    this.menuList = this.menuList.filter((item) => {
      let result = false;
      if (item.roles === undefined) {
        result = true;
      }
      if (item.children.length !== 0) {
        item.children = item.children.filter((child) => {
          return false;
        });
        if (item.children.length !== 0) {
          result = true;
        }
      }
      return result;
    });
  }

  login() {
    const dialogRef = this.dialog.open(LoginComponent, {
      // width: '65%',
    });
    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
    });
  }
  openLink(link: string) {
    // if (res && res.reportURL) {
    window.open(environment.picUrl + '/media/' +  link, '_blank');
    //   //   if (res && res.specURL) {
    //   //     window.open(res.specURL);
    //   //   }
  }

  logout() {
   // this.auth.logout();
   // this.restService.post('logout', {
   //  }).subscribe(
   //    result => {
   //    }, error => {
   //    }
   //  );
  }
}


export const APP_MENU: Menu[] = [
  {
    title: 'Обучение',
    route: 'education',
    children: [],
    icon: 'collections_bookmark'
  },
  {
    title: 'Программы',
    route: 'show',
    children: [],
  },
  {
    title: 'Администрирование',
    route: 'про',
    children: [],
    roles: ['Admin']
  }
];
