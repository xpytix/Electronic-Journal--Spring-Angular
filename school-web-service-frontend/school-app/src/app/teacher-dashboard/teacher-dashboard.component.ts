import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder,FormGroup} from '@angular/forms'
import { Teacher } from '../shared/teacher';
import { TeachersService } from '../teachers.service';


@Component({
  selector: 'app-teacher-dashboard',
  templateUrl: './teacher-dashboard.component.html',
  styleUrls: ['./teacher-dashboard.component.css']
})
export class TeacherDashboardComponent implements OnInit {
  public teachers: Teacher[] = [];
  title = 'school-app';
  constructor(private teachersService: TeachersService){}
  ngOnInit(): void {
    this.getTeachers();
    
  }

  public getTeachers():void{
    this.teachersService.getTeachers().subscribe( 
      (response: Teacher[]) =>{
        this.teachers = response;
      },
      (error: HttpErrorResponse)=>{
        alert(error.message);
      }
      
    )
  }

  
}
