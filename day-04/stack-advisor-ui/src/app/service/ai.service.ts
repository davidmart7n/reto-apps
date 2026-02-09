import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Project, StackReport } from "../model/models";
import { Observable } from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class AiService {

  constructor ( private http: HttpClient) {}

  private apiUrl:string="http://localhost:8085/api/ai"

  getRecommendation(project:Project):Observable<StackReport>{
    return this.http.post<StackReport>(this.apiUrl,project);
  }

}