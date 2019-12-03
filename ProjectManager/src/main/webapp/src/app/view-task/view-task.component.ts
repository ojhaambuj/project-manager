import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { HttpService } from '../http.service';
import { NgxSpinnerService } from "ngx-spinner";
import { Router } from '@angular/router';
declare var $;

@Component({
  selector: 'app-view-task',
  templateUrl: './view-task.component.html'
})
export class ViewTaskComponent implements OnInit {

  viewTaskForm: FormGroup;
  searchProjectForm: FormGroup;

  public projectData;
  public tempProjectData;

  public taskData;
  public tempTaskData;

  isSuccess: boolean = false;
  alertMsg: String = '';

  private currentSortButton: String = '';
  private sortIcon: String = '';
  private isStartDateAsc: boolean = false;
  private isEndDateAsc: boolean = false;
  private isPriorityAsc: boolean = false;
  private isCompletedAsc: boolean = false;

  private loadProjectURL = './loadProject';
  private loadTaskURL = './loadTask';
  private endTaskURL = './updateTask';
  private deleteTaskURL = './deleteTask';

  constructor(private fb: FormBuilder, 
    private _httpService: HttpService, 
    private spinner: NgxSpinnerService,
    private router: Router) {

  }

  ngOnInit() {
    this.initForm();
    this.loadTasks();
    this.search();
  }

  initForm() {
    this.viewTaskForm = this.fb.group({
      project: [{value: null, disabled: true}]
    });
    this.searchProjectForm = this.fb.group({
      searchProject: [null]
    });
  }

  loadProjects() {
    this.spinner.show();
      this._httpService.get(this.loadProjectURL).subscribe(
      (data: any) =>{
        this.projectData = data;
        this.copyProjectData(this.projectData);
        this.spinner.hide();
        $('#projectModal').modal('show');
      },
      error => {
          console.log(error);
          this.spinner.hide();
      }
    );
  }

  loadTasks() {
    this.spinner.show();
    this._httpService.get(this.loadTaskURL).subscribe(
      (data: any) =>{
        this.taskData = data;
        this.copyTaskData(this.taskData);
        this.spinner.hide();
      },
      error => {
          console.log(error);
          this.spinner.hide();
      }
    );
  }

  searchTask() {

  }

  editTask(task) {
    this.router.navigate(['/add-task', task.task]);
  }

  endTask(task) {
    this.spinner.show();
    task.status = 'Completed';
    this._httpService.post(task, this.endTaskURL).subscribe(
      (data: any) =>{
        this.spinner.hide();
        this.loadTasks();
        this.isSuccess = true;
        this.alertMsg = 'Task is successfully ended';
        setTimeout(() => {
          this.alertMsg = '';
        }, 5000);
      },
      error => {
        console.log(error);
        this.spinner.hide();
      }
    );
  }

  deleteTask(task) {
    this.spinner.show();
    this._httpService.post(task, this.deleteTaskURL).subscribe(
      (data: any) =>{
        this.loadTasks();
        this.spinner.hide();
      },
      error => {
          console.log(error);
          this.spinner.hide();
      }
    );
  }

  selectProject(project) {
    this.viewTaskForm.controls['project'].setValue(project.project);
    this.viewTaskForm.updateValueAndValidity();
    $('#projectModal').modal('hide');
  }

  sortByStartDate() {
    this.currentSortButton = 'startDate';
    if (!this.isStartDateAsc) {
      this.taskData.sort((a,b) => {
        if (a.startDate == null && b.startDate == null) return 0;
        else if (a.startDate == null) return -1;
        else if (b.startDate == null) return 1;
        else return a.startDate.localeCompare(b.startDate);
      });
      this.isStartDateAsc = true;
      this.sortIcon = 'fa fa-sort-asc fa-sm';
    } else {
      this.taskData.sort((a,b) => {
        if (a.startDate == null && b.startDate == null) return 0;
        else if (a.startDate == null) return 1;
        else if (b.startDate == null) return -1;
        else return b.startDate.localeCompare(a.startDate);
      });
      this.isStartDateAsc = false;
      this.sortIcon = 'fa fa-sort-desc fa-sm';
    }
  }

  sortByEndDate() {
    this.currentSortButton = 'endDate';
    if (!this.isEndDateAsc) {
      this.taskData.sort((a,b) => {
        if (a.endDate == null && b.endDate == null) return 0;
        else if (a.endDate == null) return -1;
        else if (b.endDate == null) return 1;
        else return a.endDate.localeCompare(b.endDate);
      });
      this.isEndDateAsc = true;
      this.sortIcon = 'fa fa-sort-asc fa-sm';
    } else {
      this.taskData.sort((a,b) => {
        if (a.endDate == null && b.endDate == null) return 0;
        else if (a.endDate == null) return 1;
        else if (b.endDate == null) return -1;
        else return b.endDate.localeCompare(a.endDate);
      });
      this.isEndDateAsc = false;
      this.sortIcon = 'fa fa-sort-desc fa-sm';
    }
  }

  sortByPriority() {
    this.currentSortButton = 'priority';
    if (!this.isPriorityAsc) {
      this.taskData.sort((a,b) => {
        if (+a.priority > +b.priority) return 1;
        else if (+a.priority < +b.priority) return -1;
        else 0;
      });
      this.isPriorityAsc = true;
      this.sortIcon = 'fa fa-sort-asc fa-sm';
    } else {
      this.taskData.sort((a,b) => {
        if (+a.priority > +b.priority) return -1;
        else if (+a.priority < +b.priority) return 1;
        else 0;
      });
      this.isPriorityAsc = false;
      this.sortIcon = 'fa fa-sort-desc fa-sm';
    }
  }

  sortByCompleted() {
    this.currentSortButton = 'completed';
    if (!this.isCompletedAsc) {
      this.taskData.sort((a,b) => a.status.localeCompare(b.status));
      this.isCompletedAsc = true;
      this.sortIcon = 'fa fa-sort-asc fa-sm';
    } else {
      this.taskData.sort((a,b) => b.status.localeCompare(a.status));
      this.isCompletedAsc = false;
      this.sortIcon = 'fa fa-sort-desc fa-sm';
    }
  }

  getSortIcon(input: String) {
    if (input == this.currentSortButton)
      return this.sortIcon;
    else return ''; 
  }

  /**
   * Copy the result data into temporary object
   * for filter operation
   */
  copyProjectData(sourceData: any){
    this.tempProjectData = new Array<any>();
    for(let value of sourceData){
        this.tempProjectData.push(value);
    }    
  }

  copyTaskData(sourceData: any){
    this.tempTaskData = new Array<any>();
    for(let value of sourceData){
        this.tempTaskData.push(value);
    }    
  }

  search() {
    this.searchProjectForm.valueChanges.subscribe( value => {
      var initialFlag = false;
      var resultFound = false;
      var filterString =  value ? value.searchProject : "";
      filterString = filterString.trim();
        var recordInserted = false;
        if(filterString == ""){
            this.projectData = Array<any>();
               for(let val of this.tempProjectData){
                     this.projectData.push(val);
                 }
            initialFlag = false;
            resultFound = true;
        }  else {   
            this.tempProjectData.filter(item => {
                recordInserted =false;
                 for(let key in item){
                     if((key == 'project' || key == 'manager') && item[key] && (typeof  item[key] === "string") 
                        && (item[key].toLowerCase().indexOf(filterString.toLowerCase()) !== -1 )){
                         if(!initialFlag){
                            initialFlag = true;
                            resultFound = true;
                            this.projectData = Array<any>();
                         }
                         if(!recordInserted){
                            recordInserted = true;
                            this.projectData.push(item);   
                         }    
                     }
                 }
             });
           initialFlag = false;
          }   
        if(resultFound == false){
           this.projectData = Array<any>(); 
        }
    } );

    this.viewTaskForm.valueChanges.subscribe( value => {
      var initialFlag = false;
      var resultFound = false;
      var filterString =  value ? value.project : "";
      filterString = filterString.trim();
        var recordInserted = false;
        if(filterString == ""){
            this.taskData = Array<any>();
               for(let val of this.tempTaskData){
                     this.taskData.push(val);
                 }
            initialFlag = false;
            resultFound = true;
        }  else {   
            this.tempTaskData.filter(item => {
                recordInserted =false;
                 for(let key in item){
                     if(key == 'project' && item[key] && (typeof  item[key] === "string") 
                        && (item[key].toLowerCase() == filterString.toLowerCase())){
                         if(!initialFlag){
                            initialFlag = true;
                            resultFound = true;
                            this.taskData = Array<any>();
                         }
                         if(!recordInserted){
                            recordInserted = true;
                            this.taskData.push(item);   
                         }    
                     }
                 }
             });
           initialFlag = false;
          }   
        if(resultFound == false){
           this.taskData = Array<any>(); 
        }
    } );
  }

}
