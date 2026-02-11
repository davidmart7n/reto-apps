import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";

@Injectable({providedIn:'root'})
export class LinksService{

    constructor(private http:HttpClient){}

    public getPageLinks(){

    }

    public createShortLink(){
        
    }
}