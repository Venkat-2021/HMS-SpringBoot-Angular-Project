import { OrderModel } from "./orders-model";

export interface ProductModel {
   
    productId: number,
    productName: string,
    quantity: number,
    pricePerUnit: number,
    totalAmount: number,
    Orders: OrderModel[]
   
}
