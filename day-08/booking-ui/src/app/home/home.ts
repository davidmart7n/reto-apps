import { Component } from '@angular/core';
import { FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { DatePickerModule } from 'primeng/datepicker'
import { ApiService } from '../service/api.service';
import { Appointment } from '../model/models';
import { Paginator } from 'primeng/paginator';
import { Card } from 'primeng/card';
import { Button } from 'primeng/button';
import { FloatLabel } from 'primeng/floatlabel';



@Component({
  selector: 'app-home',
  imports: [FormsModule, DatePickerModule, Card, ReactiveFormsModule, Button,FloatLabel],
  templateUrl: './home.html',
  styleUrl: './home.css',
})
export class Home {

  appointments: Appointment[] = [];
  date: Date = new Date();

  constructor(private apiService: ApiService) { }

  appointmentForm:FormGroup=new FormGroup({
    client: new FormControl('', [Validators.required]),
    startHour: new FormControl('',[Validators.required])
  })

  ngOnInit() {

    this.loadAppointments();

  }

  loadAppointments() {
    const formattedDate = this.formatDate(this.date);
    this.apiService.getByDate(formattedDate).subscribe(res => {
      this.appointments = res
    })
  }

  saveAppointment(){
    if(this.appointmentForm.valid){
      const appointmentCreation:Appointment={
        client:this.appointmentForm.value.client,
        startHour:this.appointmentForm.value.startHour,
        date:this.formatDate(this.date)
      }
      this.apiService.createAppointment(appointmentCreation).subscribe(res=>{
        this.appointmentForm.reset();
        this.loadAppointments();
      });
    }
  }

  private formatDate(date: Date): string {
    const day = String(date.getDate()).padStart(2, '0');
    const month = String(date.getMonth() + 1).padStart(2, '0');
    const year = date.getFullYear();
    return `${day}-${month}-${year}`;
  }
}