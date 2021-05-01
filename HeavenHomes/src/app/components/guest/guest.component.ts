import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/security/service/auth.service';
import { TokenStorageService } from 'src/app/security/service/token-storage.service';
import { GuestModel } from '../bookings/GuestModel';
import {MatTableDataSource} from '@angular/material/table';
import { BookingService } from '../bookings/booking.service';
import { IdModel } from '../room/IdModel';


@Component({
  selector: 'app-guest',
  templateUrl: './guest.component.html',
  styleUrls: ['./guest.component.css']
})



export class GuestComponent implements OnInit {

  private roles: string[] = [];
  isLoggedIn = false ;
  showTab = false;

  displayColumns: string[] = ['id', 'guestName', 'gender','designation','mobile','email','address','identityType','identityNumber'];
  guestdataSource!: MatTableDataSource<GuestModel>;

  table=false;

  formId:IdModel={
    id:0
   }


  constructor(private authService: AuthService, private tokenStorage: TokenStorageService,private bookservice:BookingService) {
    
   }



  ngOnInit(): void {
    this.isLoggedIn = !!this.tokenStorage.getToken();

    if (this.isLoggedIn) {
      const user = this.tokenStorage.getUser();
      this.roles = user.roles;
      
      this.showTab = this.roles.includes('ROLE_ADMIN' || 'ROLE_MANAGER' );
  
    };
  }

  onSearch(){
    this.bookservice.getGuest(this.formId.id).subscribe(data=>{this.guestdataSource=new MatTableDataSource(data),console.log(data)},error=>{alert('No Guest found with Booking id ::'+this.formId.id)});
    this.table=true;

  }

}
