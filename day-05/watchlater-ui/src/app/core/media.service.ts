import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Media } from "../shared/models";
import { Observable } from "rxjs";

@Injectable({providedIn:'root'})
export class MediaService{
    
    private apiUrl="http://localhost:8085/api/media";

    constructor(private httpClient:HttpClient){}

    

    public getAllMedia():Observable<Media[]>{
       return this.httpClient.get<Media[]>(this.apiUrl);
    }

    public getMediaPage(page:number,size:number):Observable<any>{
        const params=`?page=${page}&size=${size}`;
        return this.httpClient.get<{totalElements:number,media:Media[]}>(this.apiUrl+ params);
    }

}