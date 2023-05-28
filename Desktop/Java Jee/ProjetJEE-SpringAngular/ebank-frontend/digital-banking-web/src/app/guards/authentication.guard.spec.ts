import { TestBed } from '@angular/core/testing';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router } from '@angular/router';

import { AuthenticationGuard } from './authentication.guard';
import { AuthentificationService } from '../services/authentification.service';

class MockAuthenticationService {
  isAuthenticated() {
    // Implement a mock version of isAuthenticated() for testing purposes
    return true; // or false based on your test scenario
  }
}

describe('AuthenticationGuard', () => {
  let guard: AuthenticationGuard;
  let authService: AuthentificationService;
  let router: Router;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [
        AuthenticationGuard,
        AuthentificationService, // Provide the actual service
        { provide: Router, useValue: {} }
      ]
    });
    guard = TestBed.inject(AuthenticationGuard);
    authService = TestBed.inject(AuthentificationService);
    router = TestBed.inject(Router);
  });

  it('should be created', () => {
    expect(guard).toBeTruthy();
  });

  // Add more test cases for the guard as needed
});
