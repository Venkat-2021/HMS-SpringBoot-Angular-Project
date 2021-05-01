import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { BookingsModel } from './BookingsModel';
import { GuestModel } from './GuestModel';

const ADD_BOOKING = "http://localhost:8085/booking/add";
const GET_ALLBOOKINGS = "http://localhost:8085/booking/get";
const GET_BOOKINGBYID = "http://localhost:8085/booking/find/";
const GET_BOOKINGBYSTATUS = "http://localhost:8085/booking/findstatus/";
const DELETE_BOOKINGBYID = "http://localhost:8085/booking/delete/";
const UPDATE_BOOKINGBYSTATUS = "http://localhost:8085/booking/change/";


@Injectable({
  providedIn: 'root'
})
export class BookingService {

  constructor(private http: HttpClient) { }

  addBooking(bookingData:any){
    return this.http.post(ADD_BOOKING,bookingData,{responseType:'text'});
  }

  getBookings(): Observable<BookingsModel[]>{
    return this.http.get<BookingsModel[]>(GET_ALLBOOKINGS);
  }

  deleteBooking(id:number){
    return this.http.delete(DELETE_BOOKINGBYID+id,{responseType:'text'})
  }

  getGuest(id:number){
    return this.http.get<GuestModel[]>(GET_BOOKINGBYID+id)
  }

  getBookingByStatus(status:String){
    return this.http.get<BookingsModel[]>(GET_BOOKINGBYSTATUS+status)
  }

  // updateBookingStatus(id:number,){
  //   return this.http.
  // }


}
