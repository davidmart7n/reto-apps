export interface RegisterRequest {
  username: string;
  password?: string;
  email: string;
  firstName: string;
  lastName: string;
}

export interface LoginRequest{
    username:string;
    password?:string;
}

export interface MfaRequest {
    username:string;
    secretPin:string;
}

export interface AuthResponse {
  token?: string;
  mfaRequired?: boolean;
  message: string;
}