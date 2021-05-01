import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { logging } from 'protractor';
import { AuthService } from 'src/app/security/service/auth.service';
import { TokenStorageService } from 'src/app/security/service/token-storage.service';

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.css']
})
export class NavigationComponent {

  private roles: string[] = [];

  isLoggedIn = false ;
  //showSideNav=false;
  showdashboard = false;
  showEmpTab = false;
  //showGuestTab = false;
  showInvTab = false;
  //showRoomTab = false;
  //showBookingTab = false;
  username?: string;
  id: any;
  constructor(private router:Router,private authService: AuthService, private tokenStorage: TokenStorageService) {}

  ngOnInit(): void {
    this.isLoggedIn = !!this.tokenStorage.getToken();

    if (this.isLoggedIn) {
      const user = this.tokenStorage.getUser();
      this.roles = user.roles;

      this.username = user.username;
      this.id = user.id;
 
      //this.showdashBoard = this.roles.includes('ROLE_ADMIN' || 'ROLE_MANAGER' || 'ROLE_RECEPTIONIST');
      this.showInvTab = this.roles.includes('ROLE_ADMIN' || 'ROLE_MANAGER' );
      this.showEmpTab = this.roles.includes('ROLE_ADMIN' || 'ROLE_MANAGER' );
      //this.showGuestTab = this.roles.includes('ROLE_ADMIN' || 'ROLE_MANAGER' || 'ROLE_RECEPTIONIST');
      //this.showRoomTab = this.roles.includes('ROLE_ADMIN' || 'ROLE_MANAGER' || 'ROLE_RECEPTIONIST');
      //this.showRoomTab = this.roles.includes('ROLE_ADMIN' || 'ROLE_MANAGER' || 'ROLE_RECEPTIONIST');
    };
  }


   
  
  
  logout(): void {
    this.tokenStorage.signOut();
    window.location.reload();
  };
}
