import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BookingsComponent } from './components/bookings/bookings.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { EmployeeComponent } from './components/EmployeeMangement/employee/employee.component';
import { GuestComponent } from './components/guest/guest.component';
import { HomeComponent } from './components/home/home.component';
import { InventoryComponent } from './components/InventoryManagement/inventory/inventory.component';
import { RoomComponent } from './components/room/room.component';
import { LoginComponent } from './login/login.component';
import { AuthGuard } from './security/authguard/auth.guard';

const routes: Routes = [
 {
   path:'', component: LoginComponent
  },
  {
    path:'login', component: LoginComponent
   },
  {
    path:'home', component: HomeComponent
   },
   {
    path:'employee', component: EmployeeComponent,
    canActivate: [AuthGuard], data:{roles:["ROLE_ADMIN","ROLE_MANAGER","ROLE_RECEPTIONIST"]}
   },
   {
    path:'inventory', component: InventoryComponent,
    canActivate: [AuthGuard], data:{roles:["ROLE_ADMIN","ROLE_MANAGER"]}
   },
   {
    path:'guest', component: GuestComponent,
    canActivate: [AuthGuard], data:{roles:["ROLE_ADMIN","ROLE_MANAGER","ROLE_RECEPTIONIST"]}
   },
  //  {
  //   path:'dashboard', component: DashboardComponent,
  //   canActivate: [AuthGuard], data:{roles:["ROLE_ADMIN","ROLE_MANAGER","ROLE_RECEPTIONIST"]}
  //  },
   {
    path:'room', component: RoomComponent,
    canActivate: [AuthGuard], data:{roles:["ROLE_ADMIN","ROLE_MANAGER","ROLE_RECEPTIONIST"]}
   },
   {
    path:'bookings', component: BookingsComponent,
    canActivate: [AuthGuard], data:{roles:["ROLE_ADMIN","ROLE_MANAGER","ROLE_RECEPTIONIST"]}
   }


];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
