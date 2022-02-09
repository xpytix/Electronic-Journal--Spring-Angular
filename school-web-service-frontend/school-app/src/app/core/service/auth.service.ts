import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Student } from 'src/app/shared/student';

const AUTH_API = 'http://localhost:8082/api/auth/';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient) { }

  login(credentials: { username: any; password: any; }): Observable<any> {
    return this.http.post(AUTH_API + 'signin', {
      username: credentials.username,
      password: credentials.password
    }, httpOptions);
  }
  register(user: { username: any; password: any; role: any}, student: Student): Observable<any> {
   console.log({
    student: {
      
  },
  signupRequest: {
    username: user.username,
    password: user.password,
    role: user.role,
  }});
   
   
    return this.http.post(AUTH_API + 'signup', {
      studentDtoRegister: {
        email: student.email,
        firstName:student.firstName,
        lastName:student.lastName,
        dateOfBirth: student.dateOfBirth
    },
    signupRequest: {
      username: user.username,
      password: user.password,
      role: user.role,
    }
     
    }, httpOptions);
  }
}