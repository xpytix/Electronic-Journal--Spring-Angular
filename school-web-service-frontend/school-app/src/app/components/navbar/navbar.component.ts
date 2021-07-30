import { Component, OnInit } from '@angular/core';
import { TokenStorageService } from 'src/app/core/service/token-storage.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
  isLoggedIn?: boolean;
  roles: any;


  constructor(private tokenStorage: TokenStorageService) { }

  ngOnInit(): void {
    if (this.tokenStorage.getToken()) {      
      this.isLoggedIn = true;
      this.roles = this.tokenStorage.getUser().roles;
    }
    console.log(this.isLoggedIn);

  }
  logout() {
    this.tokenStorage.signOut();
    window.location.reload();
  }
}
