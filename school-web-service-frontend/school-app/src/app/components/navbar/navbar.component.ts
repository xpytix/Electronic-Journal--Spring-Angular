import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/core/service/auth.service';
import { TokenStorageService } from 'src/app/core/service/token-storage.service';
import { LoginComponent } from '../login/login.component';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
  isLoggedIn: any;
  isLoginFailed: any;
  roles: any;


  constructor(private tokenStorage: TokenStorageService) { }

  ngOnInit(): void {
    console.log(this.isLoginFailed);
    console.log(this.isLoggedIn);
  }
  logout() {
    this.tokenStorage.signOut();
    window.location.reload();
  }
}
