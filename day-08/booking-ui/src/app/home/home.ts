import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { DatePickerModule } from 'primeng/datepicker'

@Component({
  selector: 'app-home',
  imports: [FormsModule, DatePickerModule],
  templateUrl: './home.html',
  styleUrl: './home.css',
})
export class Home {
  
  date="";


}
