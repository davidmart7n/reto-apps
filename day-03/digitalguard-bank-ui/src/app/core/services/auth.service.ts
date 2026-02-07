import { HttpClient } from "@angular/common/http";
import { inject, Injectable, signal } from "@angular/core";
import { RegisterRequest } from "../../shared/models/auth.model";
import { Observable } from "rxjs";

@Injectable({providedIn:'root'})
export class AuthService{

    private readonly http = inject(HttpClient);
    private readonly apiUrl = 'http://localhost8084/api/auth';

    currentUserToken = signal<string | null>(localStorage.getItem('jwt_token'));
    isMfaRequired = signal<boolean>(false);

    register(data: RegisterRequest):Observable<AuthRespo>{

    }
}