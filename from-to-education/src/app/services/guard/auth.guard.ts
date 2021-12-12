import { Injectable } from '@angular/core';
import {CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree, Router} from '@angular/router';
import { Observable } from 'rxjs';
import {KeycloakAuthGuard, KeycloakService} from 'keycloak-angular';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard extends KeycloakAuthGuard {

  constructor(
    protected readonly router: Router,
    protected readonly keycloak: KeycloakService
) {
    super(router, keycloak);
  }

  async isAccessAllowed(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Promise<boolean | UrlTree> {

    return false;
    if (!this.authenticated) {
      await this.keycloak.login({
        //TODO login

      });
    }
    return this.authenticated;
  }

  logout() {
    console.log('**  LOGOUT');
    this.keycloakAngular.logout();
  }

  public checkAuth() {
    console.log(this.authenticated);
    return this.authenticated;
  }
}
