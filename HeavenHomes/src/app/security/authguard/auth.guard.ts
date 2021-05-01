import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree, Router } from '@angular/router';
import { TokenStorageService } from '../service/token-storage.service';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {
  roles: string[] = [];

  dataroles:any;

  user:any;

  constructor(private router: Router,private authService: TokenStorageService) { }
    
  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        this.roles=this.authService.getUser().roles;
        //console.log(this.roles)
        this.user=this.authService.getUser();
        this.dataroles=route.data.roles;
        //console.log(this.dataroles)

      if (this.authService.isUserLoggedIn()){
        if(this.dataroles.some((ai: string)=>this.roles.includes(ai))){
           return true;
        }
        else{
           return false;}
      }
    this.router.navigate(['login']);
   return false;
  }
  
}
