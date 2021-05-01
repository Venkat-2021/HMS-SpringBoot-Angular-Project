import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators, FormGroup } from '@angular/forms';
import {MatTableDataSource} from '@angular/material/table';
import { FromEventTarget } from 'rxjs/internal/observable/fromEvent';
import { RoomsModel } from './RoomsModel';
import { RoomService } from './service/room.service';
import {MatPaginator} from '@angular/material/paginator';
import {MatSort} from '@angular/material/sort';
import {AfterViewInit, ViewChild} from '@angular/core';
import { BookingModel } from './BookingModel';
import { IdModel } from './IdModel';

@Component({
  selector: 'app-room',
  templateUrl: './room.component.html',
  styleUrls: ['./room.component.css']
})
export class RoomComponent implements OnInit {

  status=['Available','Booked','Reserved']
  rooms=['Superior Room','Primier Room','Junior Suite','Master Suite']
  beds=['King Size','Double Bed','Twin Beds','Queen Sized Bed']

  addRoomForm = this.fb.group({
    id:['',Validators.required],
    roomName:['',Validators.required],
    bedType:['',Validators.required],
    price:['',Validators.required],
    description:['',Validators.required],
    status:['',Validators.required],
    bookings:this.fb.array([
      this.addBookingsForm()
    ])

  });

  addBookingsForm(): FormGroup{
    return this.fb.group({
      bookingId:'0',
      checkInDate:'',
      checkOutDate:''

    })
  }

  roomNo=0;

  form:IdModel={
   id:0
  }

  displayedColumns: string[] = ['id', 'roomName', 'bedType', 'price','description','status','delete'];
  dataSource!: MatTableDataSource<RoomsModel>;

  displayColumns: string[] = ['bookingId', 'checkInDate', 'checkOutDate'];
  bookingdataSource!: MatTableDataSource<BookingModel>;

  roomId=201;

  table=false;

  public bookings:BookingModel[] = [];

  @ViewChild(MatPaginator)
  paginator!: MatPaginator;
  @ViewChild(MatSort)
  sort!: MatSort;

  constructor(private fb: FormBuilder,private roomservice:RoomService) {
    this.roomservice.getRooms()
    .subscribe(data=>{this.dataSource=new MatTableDataSource(data)});
    
    this.bookingdataSource=new MatTableDataSource(this.bookings)  
    
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
    console.log(this.addRoomForm.value);
    this.roomservice.addRoom(this.addRoomForm.value).subscribe(data=>{alert(data); window.location.reload()},error=>{alert(error)})
    
  }

  Delete(id:number){
    this.roomservice.deleteRoom(id).subscribe(data=>{alert(data); window.location.reload()},error=>{alert('Some thing went wrong please try again')})
  }

  onSearch(){
    this.roomservice.getBookingById(this.form.id).subscribe(data=>{this.bookingdataSource=new MatTableDataSource(data)},error=>{alert('No Room found with id ::'+this.form.id)});
    this.table=true
  }

  // edit(id:number){
    
  // }
  

}


