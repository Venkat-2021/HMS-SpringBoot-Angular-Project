import { assertPlatform, Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ThemePalette } from '@angular/material/core';
import { EmployeeModel } from '../EmployeeModel';
import { EmployeeService } from '../service/employee.service';
import { TaskModel } from '../TaskModel';
import {MatTableDataSource} from '@angular/material/table';
import {MatPaginator} from '@angular/material/paginator';
import {MatSort} from '@angular/material/sort';
import {AfterViewInit, ViewChild} from '@angular/core';
import { IdModel } from '../../room/IdModel';

@Component({
  selector: 'app-employee',
  templateUrl: './employee.component.html',
  styleUrls: ['./employee.component.css']
})
export class EmployeeComponent implements OnInit {
 roles=['ROLE_ADMIN','ROLE_MANAGER','ROLE_RECEPTIONIST']
 status=['Pending','Completd']

 employeeForm = this.fb.group({
   employeeName:['',Validators.required],
   role:['',Validators.required],
   email:['',Validators.required],
   password:['',Validators.required],
   gender:['',Validators.required],
   dob:['',Validators.required],
   number:['',Validators.required],
   address:['',Validators.required],
   task:this.fb.array([
    this.addTaskForm()
   ])

 });


 addTaskForm(): FormGroup{
   return this.fb.group({
     title:'',
     description:'',
     status:'',
     createdAt:''
   })
 }

 form:TaskModel={
   title:'',
   description:'',
   status:'',
   createdAt:''
 }
 
 taskEmpId= 0;

 displayedColumns: string[] = ['id', 'employeeName', 'email', 'number','gender','dob','address','delete'];
 dataSource!: MatTableDataSource<EmployeeModel>;

 displayColumns: string[] = ['title', 'description', 'status','createdAt'];
 taskdataSource!: MatTableDataSource<TaskModel>;

 table=false;

 public tasks:TaskModel[] = [];

 formId:IdModel={
  id:0
 }

 @ViewChild(MatPaginator)
 paginator!: MatPaginator;
 @ViewChild(MatSort)
 sort!: MatSort;

  constructor(private fb: FormBuilder, private empService:EmployeeService) { 
    this.empService.getAllEmployee()
    .subscribe(data=>{this.dataSource=new MatTableDataSource(data)});

    this.taskdataSource=new MatTableDataSource(this.tasks),
    this.taskdataSource.paginator=this.paginator,
    this.taskdataSource.sort=this.sort;  
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
    this.empService.addEmployee(this.employeeForm.value)
        .subscribe(data=>{alert(data); window.location.reload()},error=>{alert(error)});
  }

  submitTask(){
    this.empService.addTask(this.taskEmpId,this.form).subscribe(data=>{alert(data); window.location.reload()},error=>{alert(error)});
   
  }

  Delete(id:number){
    this.empService.deleteEmployee(id).subscribe(data=>{alert(data); window.location.reload()},error=>{alert('Some thing went wrong please try again')});
  }

  onSearch(){
    this.empService.getTaskById(this.formId.id).subscribe(data=>{this.taskdataSource=new MatTableDataSource(data)},error=>{alert('No Task found with id ::'+this.formId.id)});
    this.table=true;
  }

}
