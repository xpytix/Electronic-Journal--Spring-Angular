import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { NgForm} from '@angular/forms'
import { Course } from '../../shared/course';
import { Teacher } from '../../shared/teacher';
import { TeachersService } from '../../core/service/teachers.service';
import { FormsModule } from '@angular/forms';
import { AuthService } from 'src/app/core/service/auth.service';

@Component({
  selector: 'app-teachers',
  templateUrl: './teachers.component.html',
  styleUrls: ['./teachers.component.css']
})
export class TeachersComponent implements OnInit {
  public teachers!: Teacher[];
  public editTeacher!: Teacher;
  public deleteTeacher!: Teacher;
  title = 'school-app';
  isLoginFailed = false;
  errorMessage = '';
  constructor(private authService: AuthService, private teachersService: TeachersService){}
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
  public onAddTeacher(addForm:NgForm):void{
    document.getElementById('add-teacher-form')?.click();
    this.teachersService.addTeacher(addForm.value).subscribe(
      (response: Teacher) =>{
        this.getTeachers();
      },
      (error:HttpErrorResponse)=>{
        alert(error.message);
        addForm.reset();
      }
    )
  }
  public registerNewTeacher(addForm:NgForm):void{

    let user = { username: addForm.controls.username.value, password: addForm.controls.password.value, role: [addForm.controls.role.value.toString()]}
    console.log(user);
    console.log(addForm.value);
    this.authService.registerTeacher(user, addForm.value).subscribe(
      
      data => {
        this.reloadPage();
      },
      err => {
        this.errorMessage = err.error.message;
        this.isLoginFailed = true;
      }
    );
  }
  
  reloadPage() {
    window.location.reload();
  }

  public onUpdateTeacher(teacher: Teacher): void {     
    this.teachersService.updateTeacher(teacher).subscribe(
      (response: Teacher) => {
        this.getTeachers();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public onDeleteTeacher(teacherId: number): void {
    this.teachersService.deleteTeacher(teacherId).subscribe(
      (response: void) => {
        console.log(response);
        this.getTeachers();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }
  public onOpenModal(teacher: Teacher | null, mode: string): void {
    const container = document.getElementById('main-container');
    const button = document.createElement('button');
    button.type = 'button';
    button.style.display = 'none';
    button.setAttribute('data-toggle', 'modal');
    if (mode === 'add') {
      button.setAttribute('data-target', '#addTeacherModal');
    }
    if (mode === 'edit') {     
      if(teacher !=null)
      this.editTeacher = teacher;
      button.setAttribute('data-target', '#editTeacherModal');
    }
    if (mode === 'delete') {
      if(teacher !=null)
      this.deleteTeacher = teacher;
      button.setAttribute('data-target', '#deleteTeacherModal');
    }
    if(container!=null) container.appendChild(button);
    button.click();
  }
}
