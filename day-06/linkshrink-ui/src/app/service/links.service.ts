import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { Link } from "../model/models";

@Injectable({providedIn:'root'})
export class LinksService{

    private apiUrl="http://localhost:8085/api/link";

    constructor(private http:HttpClient){}

    public getPageLinks(page:number,size:number):Observable<any>{
        const params=`?page=${page}&size=${size}`;

        return this.http.get<any>(this.apiUrl+"/short-links"+params);
    }

    public createShortLink(url:string):Observable<Link>{
        return this.http.post<Link>(this.apiUrl,{url});
    }
}