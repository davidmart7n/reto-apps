import { Component, NgModule } from '@angular/core';
import { TableModule } from 'primeng/table'
import { LinksService } from '../service/links.service';
import { Link } from '../model/models';
import { InputText } from "primeng/inputtext";
import { Button } from 'primeng/button';
import { CommonModule } from '@angular/common';
import { FormsModule, NgModel } from '@angular/forms';
@Component({
  selector: 'app-home',
  imports: [CommonModule,TableModule, InputText,Button,FormsModule],
  templateUrl: './home.html',
  styleUrl: './home.css',
})
export class Home {

  links:Link[]=[]
  page:number=0;
  size:number=10;

  inputLink="";

 constructor(private service:LinksService){}

 ngOnInit(){
  this.service.getPageLinks(this.page,this.size).subscribe({
    next:(result)=>this.links=result.content,
    error:(err)=>console.log("Fallo en conexion con backend: "+err)
  })
 }
 sendLink(){
  console.log("input Link: "+this.inputLink);
  this.service.createShortLink(this.inputLink).subscribe({
    next:(result)=>{this.ngOnInit();console.log(result)}
  });

 }
}
