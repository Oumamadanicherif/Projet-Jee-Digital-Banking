import {Component, OnInit} from '@angular/core';
import {AuthentificationService} from "../services/authentification.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements  OnInit{
  constructor(public authService : AuthentificationService, private router : Router) {
  }

  ngOnInit(): void {
  }

  handlelogout() {
    this.authService.logout().subscribe(
      {
        next : (data )=>{
         this.router.navigateByUrl("/login");
        }
      });
  }
}
