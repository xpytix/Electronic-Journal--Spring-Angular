import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { CategoryService } from 'src/app/core/service/category.service';
import { Category } from 'src/app/shared/category';

@Component({
  selector: 'app-category',
  templateUrl: './category.component.html',
  styleUrls: ['./category.component.css']
})
export class CategoryComponent implements OnInit {
  public categories!: Category[];
  public editCategory!: Category;
  public deleteCategory!: Category;
  title = 'school-app';
  constructor(private categoryService: CategoryService){}
  ngOnInit(): void {
    this.getCategory();
  }
  public getCategory():void{
    this.categoryService.getCategory().subscribe( 
      (response: Category[]) =>{
        this.categories = response;
      },
      (error: HttpErrorResponse)=>{
        alert(error.message);
      }
    )
  }
  public onAddCategory(addForm:NgForm):void{
    document.getElementById('add-category-form')?.click();
    this.categoryService.addCategory(addForm.value).subscribe(
      (response: Category) =>{
        this.getCategory();
      },
      (error:HttpErrorResponse)=>{
        alert(error.message);
        addForm.reset();
      }
    )
  }
  public onUpdateCategory(category: Category): void {     
    this.categoryService.updateCategory(category).subscribe(
      (response: Category) => {
        this.getCategory();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }
  public onDeleteCategory(categoryId: number): void {
    this.categoryService.deleteCategory(categoryId).subscribe(
      (response: void) => {
        console.log(response);
        this.getCategory();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }
  public onOpenModal(category: Category | null, mode: string): void {
    const container = document.getElementById('main-container');
    const button = document.createElement('button');
    button.type = 'button';
    button.style.display = 'none';
    button.setAttribute('data-toggle', 'modal');
    if (mode === 'add') {
      button.setAttribute('data-target', '#addCategoryModal');
    }
    if (mode === 'edit') {     
      if(category !=null)
      this.editCategory = category;
      button.setAttribute('data-target', '#editCategoryModal');
    }
    if (mode === 'delete') {
      if(category !=null)
      this.deleteCategory = category;
      button.setAttribute('data-target', '#deleteCategoryModal');
    }
    if(container!=null) container.appendChild(button);
    button.click();
  }
}