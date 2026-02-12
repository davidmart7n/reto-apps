import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { DatePickerModule } from 'primeng/datepicker'
import { ApiService } from '../service/api.service';
import { Appointment } from '../model/models';
import { Paginator } from 'primeng/paginator';
import { Card } from 'primeng/card';

@Component({
  selector: 'app-home',
  imports: [FormsModule, DatePickerModule, Paginator, Card],
  templateUrl: './home.html',
  styleUrl: './home.css',
})
export class Home {
  
  appointments:Appointment[]=[];
  date="";
  

  constructor(private apiService:ApiService){}

  ngOnInit(){
    const date=this.getDayFormatted();

    this.apiService.getByDate(date).subscribe({
      next:(data)=>{
        this.appointments=data;
        console.log(data);
      },
      error:(error)=>console.error("Failed data fetch: ",error)
    });

  }

  getDayFormatted():string{
    const today= new Date();

    const day = String(today.getDate()).padStart(2,"0");
    const month= String(today.getMonth()+1).padStart(2,"0");
    const year=String(today.getFullYear());

    return String(`${day}-${month}-${year}`);
  }
}