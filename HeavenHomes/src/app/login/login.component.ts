import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../security/service/auth.service';
import { TokenStorageService } from '../security/service/token-storage.service';
import { FormGroup, FormBuilder, Validators, FormControl } from '@angular/forms';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
 

  loginForm = this.fb.group({
    username: ['', Validators.required],
    password: ['', Validators.required]
  })

  isLoggedIn = false;
  isLoginFailed = false;
  errorMessage = '';
  roles : string[] = [];
  
  constructor(private fb: FormBuilder,private router:Router,private authService: AuthService, private tokenStorage: TokenStorageService) { }

  ngOnInit(): void {
    if (this.tokenStorage.getToken()) {
      this.isLoggedIn = true;
      this.roles = this.tokenStorage.getUser().roles;
    }
  }

  onSubmit(): void {
    const {username,password}=this.loginForm.value;

    this.authService.login(username,password).subscribe(
      async data => {
        this.tokenStorage.saveToken(data.accessToken);
        this.tokenStorage.saveUser(data);

        this.isLoginFailed = false;
        this.isLoggedIn = true;
        this.roles = this.tokenStorage.getUser().roles;
        
        if(this.roles.includes('ROLE_ADMIN')){
          window.location.reload( await this.router.navigate(['employee'])); 
          //alert("Logged In Successfully with Role :: ROLE_ADMIN")  
        }
        else if(this.roles.includes('ROLE_MANAGER'))
        {
          window.location.reload( await this.router.navigate(['employee']));
         // alert("Logged In Successfully with Role :: ROLE_MANAGER")
        }else if(this.roles.includes('ROLE_RECEPTIONIST'))
        {
          
          window.location.reload( await this.router.navigate(['bookings']));
          //alert("Logged In Successfully with Role :: ROLE_RECEPTIONIST")
        }
        else{this.router.navigate(['']);}
        
      },
      
      err => {
        this.errorMessage = err.error.message;
        this.isLoginFailed = true;
      }
   );

  
  }
  reloadPage(): void {
    window.location.reload();
   }
 

}


