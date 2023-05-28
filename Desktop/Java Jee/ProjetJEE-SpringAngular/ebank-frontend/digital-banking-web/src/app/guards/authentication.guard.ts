import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router } from '@angular/router';
import { Injectable } from '@angular/core';
import { AuthentificationService  } from '../services/authentification.service';
@Injectable()
export class AuthenticationGuard implements CanActivate {
  constructor(private authService: AuthentificationService, private router: Router) {}

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
    let authenticated = this.authService.isAuthenticated();
    if (authenticated === false) {
      this.router.navigateByUrl('/login');
      return false;
    } else {
      return true;
    }
  }
}
