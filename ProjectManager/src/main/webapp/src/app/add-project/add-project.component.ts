import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { HttpService } from '../http.service';
import { NgxSpinnerService } from "ngx-spinner";
declare var $;

@Component({
  selector: 'app-add-project',
  templateUrl: './add-project.component.html'
})
export class AddProjectComponent implements OnInit {

  projectForm: FormGroup;
  searchForm: FormGroup;
  searchManagerForm: FormGroup;
  addOrUpdate: String = 'Add';
  isSuccess: boolean = false;
  alertMsg: String = '';

  public userData;
  public tempUserData;

  public projectData;
  public tempProjectData;

  private currentSortButton: String = '';
  private sortIcon: String = '';
  private isStartDateAsc: boolean = false;
  private isEndDateAsc: boolean = false;
  private isPriorityAsc: boolean = false;
  private isCompletedAsc: boolean = false;

  private loadUserURL = './loadUser';
  private loadProjectURL = './loadProject';
  private addProjectURL = './addProject';
  private updateProjectURL = './updateProject';
  private deleteProjectURL = './deleteProject';


  constructor(private fb: FormBuilder, 
    private _httpService: HttpService, 
    private spinner: NgxSpinnerService) {

  }

  ngOnInit() {
    this.initForm();
    this.loadProject();
  }

  initForm() {
    let startDate = new Date();
    let endDate = new Date();
    endDate.setDate(endDate.getDate() + 1 );
    this.projectForm = this.fb.group( {
      project: [null],
      projectCheckBox: [false],
      startDate: [{value: startDate.toISOString().substring(0,10), disabled: true}],
      endDate: [{value: endDate.toISOString().substring(0,10), disabled: true}],
      priority: [15],
      manager: [{value: null, disabled: true}],
      noOfTasks: [0],
      completed: [0]
    } );
    this.searchForm = this.fb.group({
      search: [null]
    });
    this.searchManagerForm = this.fb.group( {
      searchManager: [null],
    } );
    this.search();
  }

  reset() {
    this.addOrUpdate = 'Add';
    this.initForm();
    setTimeout(() => {
      this.projectForm.get('startDate').disable();
      this.projectForm.get('endDate').disable();
    }, 100);
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

  loadProject() {
    this.spinner.show();
    this._httpService.get(this.loadProjectURL).subscribe(
      (data: any) =>{
        this.projectData = data;
        this.copyProjectData(this.projectData);
        this.currentSortButton = '';
        this.spinner.hide();
      },
      error => {
          console.log(error);
          this.spinner.hide();
      }
    );
  }

  isValidProject() {
    if (this.projectForm.get('project').value == null
          || this.projectForm.get('project').value == '') {
        this.isSuccess = false;
        this.alertMsg = 'Project is required';
        return false;
    }
    let startDate = new Date(this.projectForm.get('startDate').value);
    let endDate = new Date(this.projectForm.get('endDate').value);
    if (this.projectForm.get('projectCheckBox').value && 
        startDate.getTime() > endDate.getTime()) {
        this.isSuccess = false;
        this.alertMsg = 'End Date should be greater than Start Date';
        return false;
    }
    if (this.projectForm.get('manager').value == null
          || this.projectForm.get('manager').value == '') {
        this.isSuccess = false;
        this.alertMsg = 'Manager is required';
        return false;
    }
    return true;
  }

  manageProject() {
    let url = '';
    if (this.addOrUpdate == 'Add') {
      url = this.addProjectURL;
    } else {
      url = this.updateProjectURL;
    }
    if (this.isValidProject()) {
      this.spinner.show();
      this.projectForm.get('manager').enable();
      this._httpService.post(this.projectForm.value, url).subscribe(
        (data: any) =>{
          this.projectForm.get('manager').disable();
          this.spinner.hide();
          data = JSON.parse(data);
          this.isSuccess = data.success;
          this.alertMsg = data.message;
          setTimeout(() => {
            this.alertMsg = '';
          }, 5000);
          if (this.isSuccess) {
            this.loadProject();
            this.reset();
          }
        },
        error => {
          this.projectForm.get('manager').disable();
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

  editProject(project: any) {
    this.addOrUpdate = 'Update';
    if (project.projectCheckBox) {
      this.projectForm.get('startDate').enable();
      this.projectForm.get('endDate').enable();
      this.projectForm.setValue(project);
      this.projectForm.updateValueAndValidity();
    } else {
      let startDate = new Date();
      let endDate = new Date();
      endDate.setDate(endDate.getDate() + 1 );
      let copyProject = JSON.parse(JSON.stringify(project));
      copyProject.startDate = startDate.toISOString().substring(0,10);
      copyProject.endDate = endDate.toISOString().substring(0,10);
      this.projectForm.setValue(copyProject);
      this.projectForm.updateValueAndValidity();
    }
  }

  suspendProject(project: any) {
    this.spinner.show();
    this._httpService.post(project, this.deleteProjectURL).subscribe(
      (data: any) =>{
        this.loadProject();
        this.spinner.hide();
      },
      error => {
          console.log(error);
          this.spinner.hide();
      }
    );
  }

  enableDate() {
    if (this.projectForm.value.projectCheckBox) {
      this.projectForm.get('startDate').enable();
      this.projectForm.get('endDate').enable();
    } else {
      this.projectForm.get('startDate').disable();
      this.projectForm.get('endDate').disable();
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

  selectManager(user: any) {
    let managerName = user.firstName + ' ' + user.lastName + ' (' + user.employeeId + ')';
    this.projectForm.controls['manager'].setValue(managerName);
    this.projectForm.updateValueAndValidity();
    $('#userModal').modal('hide');
  }

  sortByStartDate() {
    this.currentSortButton = 'startDate';
    if (!this.isStartDateAsc) {
      this.projectData.sort((a,b) => {
        if (a.startDate == null && b.startDate == null) return 0;
        else if (a.startDate == null) return -1;
        else if (b.startDate == null) return 1;
        else return a.startDate.localeCompare(b.startDate);
      });
      this.isStartDateAsc = true;
      this.sortIcon = 'fa fa-sort-asc fa-sm';
    } else {
      this.projectData.sort((a,b) => {
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
      this.projectData.sort((a,b) => {
        if (a.endDate == null && b.endDate == null) return 0;
        else if (a.endDate == null) return -1;
        else if (b.endDate == null) return 1;
        else return a.endDate.localeCompare(b.endDate);
      });
      this.isEndDateAsc = true;
      this.sortIcon = 'fa fa-sort-asc fa-sm';
    } else {
      this.projectData.sort((a,b) => {
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
      this.projectData.sort((a,b) => {
        if (+a.priority > +b.priority) return 1;
        else if (+a.priority < +b.priority) return -1;
        else 0;
      });
      this.isPriorityAsc = true;
      this.sortIcon = 'fa fa-sort-asc fa-sm';
    } else {
      this.projectData.sort((a,b) => {
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
      this.projectData.sort((a,b) => {
        if (+a.completed > +b.completed) return 1;
        else if (+a.completed < +b.completed) return -1;
        else 0;
      });
      this.isCompletedAsc = true;
      this.sortIcon = 'fa fa-sort-asc fa-sm';
    } else {
      this.projectData.sort((a,b) => {
        if (+a.completed > +b.completed) return -1;
        else if (+a.completed < +b.completed) return 1;
        else 0;
      });
      this.isCompletedAsc = false;
      this.sortIcon = 'fa fa-sort-desc fa-sm';
    }
  }

  getSortIcon(input: String) {
    if (input == this.currentSortButton)
      return this.sortIcon;
    else return ''; 
  }

  search() {
    this.searchForm.valueChanges.subscribe( value => {
      var initialFlag = false;
      var resultFound = false;
      var filterString =  value ? value.search : "";
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

    this.searchManagerForm.valueChanges.subscribe( value => {
      var initialFlag = false;
      var resultFound = false;
      var filterString =  value ? value.searchManager : "";
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
  }

}
