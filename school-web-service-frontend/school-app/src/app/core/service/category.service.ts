import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Category } from 'src/app/shared/category';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {


  private apiServerUrl = environment.apiBaseUrl;
  constructor(private http:HttpClient) { }

  public getCategory(): Observable<Category[]>{
    return this.http.get<Category[]>(`${this.apiServerUrl}/category`)
  }
  public addCategory(category: Category): Observable<Category>{
    console.log(category);

    return this.http.post<Category>(`${this.apiServerUrl}/category`, category)
  }
  public updateCategory(category: Category): Observable<Category>{
    console.log("funkcja update: " +  category);
    return this.http.put<Category>(`${this.apiServerUrl}/v`, category)
  }
  public deleteCategory(categoryId: number): Observable<void>{
    return this.http.delete<void>(`${this.apiServerUrl}/category/${categoryId}`)
  }
}