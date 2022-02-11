import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/core/service/auth.service';
import { StudentsService } from 'src/app/core/service/students.service';
import { TokenStorageService } from 'src/app/core/service/token-storage.service';
import { Student } from 'src/app/shared/student';
import { LoginComponent } from '../login/login.component';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
  isLoggedIn = false;
  isLoginFailed: any;
  roles: any;
  userLogged: any;
  studentId = 0;
  students: Student[] = [];
  
  constructor(private router: Router, private studentsService: StudentsService, private tokenStorage: TokenStorageService) { }

  ngOnInit(): void {
    this.getStudents();
    console.log(this.tokenStorage.getUser().username);
      if (this.tokenStorage.getToken()) {
        this.isLoggedIn = true;  
        this.roles = this.tokenStorage.getUser().roles;
        this.userLogged = this.tokenStorage.getUser().username;       
    }
  }

  public getStudents():void{
    this.studentsService.getStudents().subscribe( 
      (response: Student[]) =>{
        this.students = response;
      },
      (error: HttpErrorResponse)=>{
        alert(error.message);
      }
      
    )
  }
  
  findStudent(id: Number){
    console.log("hejo");
    
    this.students.forEach((student) => {
     if(student.user.id === id)
     {console.log(student.id);
        return student.id;}
      else
        return 0;
    });
    return 0;
  }
  logout() {
    this.isLoggedIn = false;
    this.tokenStorage.signOut();
    window.location.reload();
  }
  checkUserAccount(){
    console.log("dzialam");
    
    this.studentId = this.findStudent(this.tokenStorage.getUser().id);
    console.log(this.findStudent(this.tokenStorage.getUser().id))
    
    this.router.navigate(['/student', { id: this.studentId }]);
  }
}

