import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { ProductModel } from '../product-model';
import { Observable } from 'rxjs';
import { OrderModel } from '../orders-model';

const ADD_PRODUCT = 'http://localhost:8084/product/add';
const GET_PRODUCTS = 'http://localhost:8084/product/get';
const GET_PRODUCTBYID = "http://localhost:8084/product/find/{id}";
const UPDATE_PRODUCTBYID = "http://localhost:8084/product/update/{id}";
const DELETE_PRODUCTBYID = "http://localhost:8084/product/delete/";
const ADD_ORDER = "http://localhost:8084/product/order/";
const GET_ALLORDERSBYID = "http://localhost:8084/product/orders/get/";

@Injectable({
  providedIn: 'root'
})
export class InventoryServiceService {

  constructor(private http: HttpClient) { }

  addProduct(productDate: any){
    return this.http.post(ADD_PRODUCT,productDate,{responseType:'text'});
  }

  getProducts(): Observable<ProductModel[]>{
    return this.http.get<ProductModel[]>(GET_PRODUCTS);
  }

  deleteProduct(id:number){
    return this.http.delete(DELETE_PRODUCTBYID+id,{responseType:'text'})
  }

  getOrderById(id:number){
    return this.http.get<OrderModel[]>(GET_ALLORDERSBYID+id)
  }

  addOrderById(id:number,form:any){
    return this.http.post(ADD_ORDER+id,form,{responseType:'text'})
  }
}
