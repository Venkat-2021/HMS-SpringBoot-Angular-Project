import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { EmployeeModel } from '../EmployeeModel';
import { TaskModel } from '../TaskModel';

const ADD_EMPLOYEE = 'http://localhost:8082/employee/add';
const GET_ALLEMPLOYEES = 'http://localhost:8082/employee/get';
const GET_EMPLOYEEBYID = "http://localhost:8082/employee/getby/{id}";
const GET_EMPLOYEEBYROLE = "http://localhost:8082/employee/findby/{role}";
const GET_EMPLOYEEBYEMAIL = "http://localhost:8082/employee/get/{email}";
const GET_TASKBYID = "http://localhost:8082/employee/get/task/";
const ADD_TASKBYID = "http://localhost:8082/employee/task/";
const UPDATE_EMPLOYEEBYID = "http://localhost:8082/employee/update/{id}";
const DELETE_EMPLOYEEBYID = "http://localhost:8082/employee/delete/";


@Injectable({
  providedIn: 'root'
})
export class EmployeeService {

  constructor(private http: HttpClient) { }

  addEmployee(employeeData: any){
    return this.http.post(ADD_EMPLOYEE,employeeData,{responseType:"text"});
  }

  getAllEmployee(): Observable<EmployeeModel[]>{
    return this.http.get<EmployeeModel[]>(GET_ALLEMPLOYEES);
  }

  deleteEmployee(id:number){
    return this.http.delete(DELETE_EMPLOYEEBYID+id,{responseType:'text'})
  }

  addTask(empId:number,form:any){
    return this.http.post(ADD_TASKBYID+empId,form,{responseType:"text"})
  }

  getTaskById(id:number){
    return this.http.get<TaskModel[]>(GET_TASKBYID+id)
  }

}
