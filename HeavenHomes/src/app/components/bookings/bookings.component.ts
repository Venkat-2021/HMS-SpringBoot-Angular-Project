import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators, FormGroup } from '@angular/forms';
import { BookingService } from './booking.service';
import {MatTableDataSource} from '@angular/material/table';
import {MatPaginator} from '@angular/material/paginator';
import {MatSort} from '@angular/material/sort';
import {AfterViewInit, ViewChild} from '@angular/core';
import { BookingsModel } from './BookingsModel';


@Component({
  selector: 'app-bookings',
  templateUrl: './bookings.component.html',
  styleUrls: ['./bookings.component.css']
})
export class BookingsComponent implements OnInit {

  room=['201','202','203','301','302','303','304']
  rooms=['Superior Room','Primier Room','Junior Suite','Master Suite']
  prices=['800','1200','1600','1800']
  payments=['Cash','Online']


  addBookingForm = this.fb.group({
    checkInDate:['',Validators.required],
    checkOutDate:['',Validators.required],
    roomNo:['',Validators.required],
    roomName:['',Validators.required],
    price:['',Validators.required],
    numberOfNights:['',Validators.required],
    totalAmount:['',Validators.required],
    paymentMode:['',Validators.required],
    status:['Reserved',Validators.required],
    number:['',Validators.required],
    guestDetails:this.fb.array([
      this.addguestForm()
    ])

  });
  
  addguestForm(): FormGroup{
    return this.fb.group({
      id:'',
      guestName:'',
      gender:'',
      designation:'',
      mobile:'',
      email:'',
      address:'',
      identityType:'',
      identityNumber:''
    })
    
  }
  
 displayedColumns: string[] = ['id', 'checkInDate', 'checkOutDate', 'roomNo','totalAmount','paymentMode','number','status','delete'];
 dataSource!: MatTableDataSource<BookingsModel>;

 displayColumns: string[] = ['id', 'checkInDate', 'checkOutDate', 'roomNo','totalAmount','paymentMode','number','status','delete'];
 checkdataSource!: MatTableDataSource<BookingsModel>;

 public booking : BookingsModel[]=[]

 @ViewChild(MatPaginator)
 paginator!: MatPaginator;
 @ViewChild(MatSort)
 sort!: MatSort;

 show=false;

  constructor(private fb: FormBuilder,private bookservice:BookingService) { 
    this.bookservice.getBookings().subscribe(data=>{this.dataSource=new MatTableDataSource(data)});

    this.bookservice.getBookingByStatus('Occupied').subscribe(data=>{this.booking=(data),window.location.reload},error=>{alert(error)});

    this.checkdataSource=new MatTableDataSource(this.booking);
    
  }
  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }
  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }

  ngOnInit(): void {
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }

  onSubmit(){
    console.log(this.addBookingForm.value);
    this.bookservice.addBooking(this.addBookingForm.value).subscribe(data=>{alert(data); window.location.reload()},error=>{alert(error)})
  }
  Delete(id:number){
    this.bookservice.deleteBooking(id).subscribe(data=>{alert(data); window.location.reload()},error=>{alert('Some thing went wrong please try again')});
  }

  changeStatus(){
    
  }
}
