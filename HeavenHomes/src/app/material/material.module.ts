import { NgModule } from '@angular/core';
import { MatIconModule } from "@angular/material/icon";
import {MatToolbarModule} from '@angular/material/toolbar'; 
import { MatButtonModule } from '@angular/material/button';
import {MatGridListModule} from '@angular/material/grid-list';
import { MatCardModule } from '@angular/material/card';
import { MatMenuModule } from '@angular/material/menu';
import {MatSnackBarModule} from '@angular/material/snack-bar';
import {MatTabsModule} from '@angular/material/tabs';
import { MatProgressSpinnerModule} from '@angular/material/progress-spinner';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule} from '@angular/material/input';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatListModule } from '@angular/material/list';
import { FlexLayoutModule } from '@angular/flex-layout';


const Materials = [
  MatIconModule,
  MatToolbarModule,
  MatButtonModule ,
  MatGridListModule,
  MatCardModule,
  MatIconModule,
  MatIconModule,  
  MatMenuModule,
  MatProgressSpinnerModule,
  FormsModule,
  MatSnackBarModule,
  MatFormFieldModule,
  MatInputModule,
  MatTabsModule,
  MatListModule,
  MatSidenavModule,
  FlexLayoutModule,
  FormsModule,
  ReactiveFormsModule,
  MatIconModule
  
];



@NgModule({
 
  imports: [Materials],
  exports:[Materials],
})
export class MaterialModule { }
