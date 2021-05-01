import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { RoomsModel } from '../RoomsModel';
import { BookingModel } from '../BookingModel';

const ADD_ROOM = "http://localhost:8083/rooms/add";
const GET_ALLROOMS = "http://localhost:8083/rooms/get";
const GET_ROOMBYID = "http://localhost:8083/rooms/find/{id}";
const UPDATE_ROOMBYID = "http://localhost:8083/rooms/update/";
const DELETE_ROOMBYID = "http://localhost:8083/rooms/delete/";
const UPDATE_ROOMSTATUS = "http://localhost:8083/rooms/update/{id}/{status}";
const GET_BOOKINGSBYID = "http://localhost:8083/rooms/bookings/";


@Injectable({
  providedIn: 'root'
})
export class RoomService {

  constructor(private http: HttpClient) { }

  addRoom(roomData:any){
    return this.http.post<any>(ADD_ROOM,roomData)
  }

  getRooms(): Observable<RoomsModel[]>{
    return this.http.get<RoomsModel[]>(GET_ALLROOMS);
  }

  deleteRoom(id:number){
    return this.http.delete(DELETE_ROOMBYID+id,{responseType:'text'})
  }

  getBookingById(id:number){
    return this.http.get<BookingModel[]>(GET_BOOKINGSBYID+id)
  }

  updateRoom(id:number,roomData:any){
    return this.http.put<any>(UPDATE_ROOMBYID+id,roomData);
  }

}
