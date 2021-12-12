import {Component, OnInit} from '@angular/core';
import {Menu} from './classes/Menu';
import {MatDialog} from '@angular/material/dialog';
import {LoginComponent} from './pages/login/login.component';
import {RestService} from './services/rest.service';
import {environment} from '../environments/environment';
import {AuthGuard} from './services/guard/auth.guard';
import {AuthService} from './services/auth.service';
import {KeycloakService} from 'keycloak-angular';
import {Router} from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent  implements OnInit {
  title = 'from-to-education';
  userName: string;
  public menuList: Menu[] = [];
  certificates;

  constructor(public dialog: MatDialog,
              private restService: RestService,
              public authService: AuthService,
              public keycloakService: KeycloakService,
              private router: Router
              ) {
  }

  ngOnInit(): void {
    const userInfo = this.keycloakService.getKeycloakInstance().loadUserInfo();
    // @ts-ignore
    userInfo.then( res => this.userName = res.preferred_username );

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
    this.authService.login();
  }

  openLink(link: string) {
    window.open(environment.picUrl + '/media/' +  link, '_blank');
  }

  logout() {
   this.authService.logout();
  }

  getLk(): void {
    console.log('go lk');
   const link = this.authService.getRoles().filter(role => {
      let result = false;
      if (role === 'admin') {
        this.router.navigate(['/administration']);
        result = true;
      }
      return result;
    });
    if (link.length === 0) {
     this.router.navigate(['/education']);
   }
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
