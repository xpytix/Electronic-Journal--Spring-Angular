import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Teacher } from '../../shared/teacher';

@Injectable({
  providedIn: 'root'
})
export class TeachersService {

  private apiServerUrl = environment.apiBaseUrl;
  constructor(private http:HttpClient) { }

  public getTeachers(): Observable<Teacher[]>{
    return this.http.get<Teacher[]>(`${this.apiServerUrl}/teacher`)
  }
  public getTeacher(teacherId: number): Observable<Teacher>{
    return this.http.get<Teacher>(`${this.apiServerUrl}/teacher/${teacherId}`)
  }
  public addTeacher(teacher: Teacher): Observable<Teacher>{
    console.log(teacher);
    
    return this.http.post<Teacher>(`${this.apiServerUrl}/teacher`, teacher)
  }
  public updateTeacher(teacher: Teacher): Observable<Teacher>{
    console.log("funkcja update: " +  teacher);
    return this.http.put<Teacher>(`${this.apiServerUrl}/teacher`, teacher)
  }
  public deleteTeacher(teacherId: number): Observable<void>{
    return this.http.delete<void>(`${this.apiServerUrl}/teacher/${teacherId}`)
  }
}
