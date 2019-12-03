import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { HttpService } from '../http.service';
import { NgxSpinnerService } from "ngx-spinner";
import { ActivatedRoute } from '@angular/router';
import { Router } from '@angular/router';
declare var $;

@Component({
  selector: 'app-add-task',
  templateUrl: './add-task.component.html'
})
export class AddTaskComponent implements OnInit {

  taskForm: FormGroup;
  searchForm: FormGroup;
  searchProjectForm: FormGroup;
  searchTaskForm: FormGroup;
  searchUserForm: FormGroup;
  addOrUpdate: String = 'Add';
  isSuccess: boolean = false;
  alertMsg: String = '';

  public userData;
  public tempUserData;

  public projectData;
  public tempProjectData;

  public taskData;
  public tempTaskData;

  private getTaskURL = './getTask?task=';
  private loadProjectURL = './loadProject';
  private loadUserURL = './loadUser';
  private addTaskURL = './addTask';
  private updateTaskURL = './updateTask';
  private loadTaskURL = './loadTask';

  constructor(private fb: FormBuilder, 
    private _httpService: HttpService, 
    private spinner: NgxSpinnerService,
    private router: Router,
    private route: ActivatedRoute) {

  }

  ngOnInit() {
    this.initForm();
    this.route.params.subscribe(params => {
      if (params.task) {
        this.getTask( params.task );
      }
    });
  }

  initForm() {
    let startDate = new Date();
    let endDate = new Date();
    endDate.setDate(endDate.getDate() + 1 );
    this.taskForm = this.fb.group({
      project: [{value: null, disabled: true}],
      task: [null],
      parentTaskCheckBox: false,
      priority: [15],
      parentTask: [{value: null, disabled: true}],
      startDate: [startDate.toISOString().substring(0,10)],
      endDate: [endDate.toISOString().substring(0,10)],
      user: [{value: null, disabled: true}],
      status: ['Pending']
    });
    this.searchProjectForm = this.fb.group({
      searchProject: [null]
    });
    this.searchTaskForm = this.fb.group({
      searchTask: [null]
    });
    this.searchUserForm = this.fb.group({
      searchUser: [null]
    });
    this.search();
  }

  getTask(task: any): void {
    this._httpService.get(this.getTaskURL + task).subscribe(
      (data: any) =>{
        this.taskForm.setValue(data);

        setTimeout(() => {
          if (data.parentTaskCheckBox) {
            let startDate = new Date();
            let endDate = new Date();
            endDate.setDate(endDate.getDate() + 1 );
            this.taskForm.get('priority').disable();
            this.taskForm.get('startDate').disable();
            this.taskForm.get('startDate').setValue(startDate.toISOString().substring(0,10));
            this.taskForm.get('endDate').disable();
            this.taskForm.get('endDate').setValue(endDate.toISOString().substring(0,10));
            this.taskForm.controls['parentTask'].setValue('');
            this.taskForm.updateValueAndValidity();
          }
        }, 100);

        this.taskForm.updateValueAndValidity();
        this.addOrUpdate = 'Update';
      },
      error => {
          console.log(error);
      }
    );
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

  loadUsers() {
    this.spinner.show();
    this._httpService.get(this.loadUserURL).subscribe(
      (data: any) =>{
        this.userData = data;
        this.copyUserData(this.userData);
        this.spinner.hide();
        $('#userModal').modal('show');
      },
      error => {
          console.log(error);
          this.spinner.hide();
      }
    );
  }

  isValidTask() {
    if (this.taskForm.get('project').value == null
          || this.taskForm.get('project').value == '') {
        this.isSuccess = false;
        this.alertMsg = 'Project is required';
        return false;
    }
    if (this.taskForm.get('task').value == null
          || this.taskForm.get('task').value == '') {
        this.isSuccess = false;
        this.alertMsg = 'Task is required';
        return false;
    }
    if (this.taskForm.get('user').value == null
          || this.taskForm.get('user').value == '') {
        this.isSuccess = false;
        this.alertMsg = 'User is required';
        return false;
    }
    let startDate = new Date(this.taskForm.get('startDate').value);
    let endDate = new Date(this.taskForm.get('endDate').value);
    if (!this.taskForm.value.parentTaskCheckBox && 
        startDate.getTime() > endDate.getTime()) {
        this.isSuccess = false;
        this.alertMsg = 'End Date should be greater than Start Date';
        return false;
    }
    return true;
  }

  manageTask() {
    let url = '';
    if (this.addOrUpdate == 'Add') {
      url = this.addTaskURL;
    } else {
      url = this.updateTaskURL;
    }
    if (this.isValidTask()) {
      this.spinner.show();
      this.taskForm.get('project').enable();
      this.taskForm.get('parentTask').enable();
      this.taskForm.get('user').enable();
      this._httpService.post(this.taskForm.value, url).subscribe(
        (data: any) =>{
          this.taskForm.get('project').disable();
          this.taskForm.get('parentTask').disable();
          this.taskForm.get('user').disable();
          this.spinner.hide();
          data = JSON.parse(data);
          this.isSuccess = data.success;
          this.alertMsg = data.message;
          if (this.addOrUpdate == 'Add') {
            setTimeout(() => {
              this.alertMsg = '';
            }, 5000);
            this.reset();
          } else {
            setTimeout(() => {
              this.router.navigate(['/view-task']);
            }, 1000);
          }
          
        },
        error => {
          this.taskForm.get('project').disable();
          this.taskForm.get('parentTask').disable();
          this.taskForm.get('user').disable();
          console.log(error);
          this.spinner.hide();
        }
      );      
    } else {
      setTimeout(() => {
        this.alertMsg = '';
      }, 5000);
    }
  }

  reset() {
    this.addOrUpdate = 'Add';
    this.initForm();
    setTimeout(() => {
      this.taskForm.get('priority').enable();
      this.taskForm.get('startDate').enable();
      this.taskForm.get('endDate').enable();
    }, 100);
  }

  loadTasks() {
    this.spinner.show();
    this._httpService.get(this.loadTaskURL).subscribe(
      (data: any) =>{
        this.taskData = data;
        this.copyTaskData(this.taskData);
        this.spinner.hide();
        $('#taskModal').modal('show');
      },
      error => {
          console.log(error);
          this.spinner.hide();
      }
    );
  }

  selectProject(project) {
    this.taskForm.controls['project'].setValue(project.project);
    this.taskForm.updateValueAndValidity();
    $('#projectModal').modal('hide');
  }

  selectTask(task) {
    this.taskForm.controls['parentTask'].setValue(task.task);
    this.taskForm.updateValueAndValidity();
    $('#taskModal').modal('hide');
  }

  selectUser(user) {
    let managerName = user.firstName + ' ' + user.lastName + ' (' + user.employeeId + ')';
    this.taskForm.controls['user'].setValue(managerName);
    this.taskForm.updateValueAndValidity();
    $('#userModal').modal('hide');
  }

  enableOptions() {
    if (this.taskForm.value.parentTaskCheckBox) {
      this.taskForm.get('priority').disable();
      this.taskForm.get('startDate').disable();
      this.taskForm.get('endDate').disable();
      this.taskForm.controls['parentTask'].setValue('');
      this.taskForm.updateValueAndValidity();
    } else {
      this.taskForm.get('priority').enable();
      this.taskForm.get('startDate').enable();
      this.taskForm.get('endDate').enable();
    }
  }

  /**
   * Copy the result data into temporary object
   * for filter operation
   */
  copyUserData(sourceData: any){
    this.tempUserData = new Array<any>();
    for(let value of sourceData){
        this.tempUserData.push(value);
    }    
  }

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
                     if(item[key] && (typeof  item[key] === "string") 
                        && ((key == 'project' || key == 'manager') && item[key].toLowerCase().indexOf(filterString.toLowerCase()) !== -1 )){
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

    this.searchUserForm.valueChanges.subscribe( value => {
      var initialFlag = false;
      var resultFound = false;
      var filterString =  value ? value.searchUser : "";
      filterString = filterString.trim();
        var recordInserted = false;
        if(filterString == ""){
            this.userData = Array<any>();
               for(let val of this.tempUserData){
                     this.userData.push(val);
                 }
            initialFlag = false;
            resultFound = true;
        }  else {   
            this.tempUserData.filter(item => {
                recordInserted =false;
                 for(let key in item){
                     if(item[key] && (typeof  item[key] === "string") 
                        && (item[key].toLowerCase().indexOf(filterString.toLowerCase()) !== -1 )){
                         if(!initialFlag){
                         initialFlag = true;
                         resultFound = true;
                          this.userData = Array<any>();
                         }
                         if(!recordInserted){
                             recordInserted = true;
                             this.userData.push(item);   
                         }    
                     }
                 }
             });
           initialFlag = false;
          }   
        if(resultFound == false){
           this.userData = Array<any>(); 
        }
    } );

    this.searchTaskForm.valueChanges.subscribe( value => {
      var initialFlag = false;
      var resultFound = false;
      var filterString =  value ? value.searchTask : "";
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
                     if(item[key] && (typeof  item[key] === "string") 
                        && (item[key].toLowerCase().indexOf(filterString.toLowerCase()) !== -1 )){
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
