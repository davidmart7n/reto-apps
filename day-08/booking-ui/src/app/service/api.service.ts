import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { Appointment } from "../model/models";

@Injectable({providedIn:'root'})
export class ApiService{

  apiUrl ="http://localhost:8085/api/booking";

  constructor(private http:HttpClient){}

  getByDate(date:string):Observable<Appointment[]>{
    let urlWithDate=`${this.apiUrl}?date=${date}`;
    return this.http.get<Appointment[]>(urlWithDate);
  }

  createAppointment(appointment:Appointment):Observable<Appointment>{
    return this.http.post<Appointment>(this.apiUrl,appointment);
  }

  deleteAppointment(id:number):Observable<any>{
    return this.http.delete<any>(this.apiUrl+String(id));
  }
}