import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { InventoryServiceService } from 'src/app/components/InventoryManagement/service/inventory-service.service';
import { ProductModel } from '../product-model';
import {MatTableDataSource} from '@angular/material/table';
import {MatPaginator} from '@angular/material/paginator';
import {MatSort} from '@angular/material/sort';
import {AfterViewInit, ViewChild} from '@angular/core';
import { IdModel } from '../../room/IdModel';
import { OrderModel } from '../orders-model';

@Component({
  selector: 'app-inventory',
  templateUrl: './inventory.component.html',
  styleUrls: ['./inventory.component.css']
})
export class InventoryComponent implements OnInit {

  hide=false;

  addProductForm = this.fb.group({
    productName:['',Validators.required],
    quantity: ['', Validators.required],
    pricePerUnit: ['', Validators.required],
    totalAmount: ['',Validators.required],
    order:this.fb.array([
      this.addOrderForm()
    ])  
  });


  addOrderForm(): FormGroup{
    return this.fb.group ({
      quantityRemoved:'0',
      quantityAdded:'',
      dateOfIssue: '',
      supplier: '',
      employeeId: '',
      employeeName:'',
      roomNo:''
    })  

  }

  addquantity(){
    return this.addProductForm.get('quantity')?.value
  }

                                                          /* PRODUCT FORM FINISH */

  private roles: string[] = [];
  isLoggedIn = false ;
  username?: string;
  id: any;

  form:OrderModel={
    orderId: 0,
    quantityRemoved:0,
    quantityAdded:0,
    dateOfIssue:'',
    supplier:'',
    employeeId:0,
    employeeName:'',
    roomNo:''

   
  }
  
  productId= 0;

  displayedColumns: string[] = ['productId', 'productName', 'quantity', 'pricePerUnit','totalAmount','modifiedAt','delete'];
  dataSource!: MatTableDataSource<ProductModel>;

  displayColumns: string[] = ['orderId', 'quantityRemoved', 'quantityAdded','dateOfIssue','supplier','employeeId','employeeName','roomNo'];
  orderdataSource!: MatTableDataSource<OrderModel>;

  
 table=false;

  //Product Model   
  public products:ProductModel[] = [];

  formId:IdModel={
   id:0
  }

  @ViewChild(MatPaginator)
  paginator!: MatPaginator;
  @ViewChild(MatSort)
  sort!: MatSort;

  //Constructor
  constructor(private fb: FormBuilder,private invservice: InventoryServiceService) { 
    this.invservice.getProducts().subscribe(data=>{this.dataSource=new MatTableDataSource(data)});
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
    this.invservice.addProduct(this.addProductForm.value).subscribe( data=>{alert(data); window.location.reload()},error=>{alert(error)}); 
  }

  Delete(id:number){
    this.invservice.deleteProduct(id).subscribe(data=>{alert(data); window.location.reload()},error=>{alert('Some thing went wrong please try again')})
  }

  onSearch(){
    this.invservice.getOrderById(this.formId.id).subscribe(data=>{this.orderdataSource=new MatTableDataSource(data)},error=>{alert('No Order found with id ::'+this.formId.id)});
    this.table=true;
  }

  submitOrder(){
    console.log(this.productId)
    console.log(this.form)
    this.invservice.addOrderById(this.productId,this.form).subscribe(data=>{alert(data); window.location.reload()},error=>{alert(error)});
   
  }
  

}
