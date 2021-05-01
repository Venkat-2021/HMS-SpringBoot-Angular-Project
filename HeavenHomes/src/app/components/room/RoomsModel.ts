import { BookingModel } from "./BookingModel";

export interface RoomsModel{
    id:number,
    roomName:String,
    bedType:String,
    price:String,
    description:String,
    status:String
    Bookings: BookingModel[]
}