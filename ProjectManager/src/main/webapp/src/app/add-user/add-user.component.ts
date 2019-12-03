import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { HttpService } from '../http.service';
import { NgxSpinnerService } from "ngx-spinner";

@Component({
  selector: 'app-add-user',
  templateUrl: './add-user.component.html'
})
export class AddUserComponent implements OnInit {

  userForm: FormGroup;
  searchForm: FormGroup;
  addOrUpdate: String = 'Add';
  isSuccess: boolean = false;
  alertMsg: String = '';

  public userData;
  public tempUserData;

  private currentSortButton: String = '';
  private sortIcon: String = '';
  private isFirstNameAsc: boolean = false;
  private isLastNameAsc: boolean = false;
  private isEmployeeIdAsc: boolean = false;

  private addUserURL = './addUser';
  private updateUserURL = './updateUser';
  private deleteUserURL = './deleteUser';
  private loadUserURL = './loadUser';

  constructor(private fb: FormBuilder, 
    private _httpService: HttpService, 
    private spinner: NgxSpinnerService) {

  }

  ngOnInit() {
    this.initForm();
    this.loadUsers();
  }

  initForm() {
    this.userForm = this.fb.group( {
      firstName: [null],
      lastName: [null],
      employeeId: [null],
    } );
    this.searchForm = this.fb.group( {
      search: [null],
    } );
    this.search();
  }

  loadUsers() {
    this.spinner.show();
    this._httpService.get(this.loadUserURL).subscribe(
      (data: any) =>{
        this.userData = data;
        this.copyData(this.userData);
        this.currentSortButton = '';
        this.spinner.hide();
      },
      error => {
          console.log(error);
          this.spinner.hide();
      }
    );
  }

  isValidUser() {
    if (this.userForm.get('firstName').value == null
          || this.userForm.get('firstName').value.trim() == '') {
        this.isSuccess = false;
        this.alertMsg = 'First Name is required';
        return false;
    }
    if (this.userForm.get('lastName').value == null
          || this.userForm.get('lastName').value.trim() == '') {
        this.isSuccess = false;
        this.alertMsg = 'Last Name is required';
        return false;
    }
    if (this.userForm.get('employeeId').value == null
          || this.userForm.get('employeeId').value.trim() == '') {
        this.isSuccess = false;
        this.alertMsg = 'Employee ID is required';
        return false;
    }
    return true;
  }

  manageUser() {
    let url = '';
    if (this.addOrUpdate == 'Add') {
      url = this.addUserURL;
    } else {
      url = this.updateUserURL;
    }
    if (this.isValidUser()) {
      this.spinner.show();
      this._httpService.post(this.userForm.value, url).subscribe(
        (data: any) =>{
          this.spinner.hide();
          data = JSON.parse(data);
          this.isSuccess = data.success;
          this.alertMsg = data.message;
          this.loadUsers();
          setTimeout(() => {
            this.alertMsg = '';
          }, 5000);
          if (this.isSuccess) {
            this.reset();
          }
        },
        error => {
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

  editUser(user: any) {
    this.addOrUpdate = 'Update';
    this.userForm.setValue(user);
    this.userForm.updateValueAndValidity();
  }

  deleteUser(user: any) {
    this.spinner.show();
    this._httpService.post(user, this.deleteUserURL).subscribe(
      (data: any) =>{
        this.loadUsers();
        this.spinner.hide();
      },
      error => {
          console.log(error);
          this.spinner.hide();
      }
    );
  }

  reset() {
    this.initForm();
    this.addOrUpdate = 'Add';
  }

  /**
   * Copy the result data into temporary object
   * for filter operation
   */
  copyData(sourceData: any){
    this.tempUserData = new Array<any>();
    for(let value of sourceData){
        this.tempUserData.push(value);
    }    
  }

  sortByFirstName() {
    this.currentSortButton = 'firstName';
    if (!this.isFirstNameAsc) {
      this.userData.sort((a,b) => a.firstName.localeCompare(b.firstName));
      this.isFirstNameAsc = true;
      this.sortIcon = 'fa fa-sort-asc fa-sm';
    } else {
      this.userData.sort((a,b) => b.firstName.localeCompare(a.firstName));
      this.isFirstNameAsc = false;
      this.sortIcon = 'fa fa-sort-desc fa-sm';
    }
  }

  sortByLastName() {
    this.currentSortButton = 'lastName';
    if (!this.isLastNameAsc) {
      this.userData.sort((a,b) => a.lastName.localeCompare(b.lastName));
      this.isLastNameAsc = true;
      this.sortIcon = 'fa fa-sort-asc fa-sm';
    } else {
      this.userData.sort((a,b) => b.lastName.localeCompare(a.lastName));
      this.isLastNameAsc = false;
      this.sortIcon = 'fa fa-sort-desc fa-sm';
    }
  }

  sortByEmployeeId() {
    this.currentSortButton = 'employeeId';
    if (!this.isEmployeeIdAsc) {
      this.userData.sort((a,b) => {
        if (+a.employeeId > +b.employeeId) return 1;
        else if (+a.employeeId < +b.employeeId) return -1;
        else 0;
      });
      this.isEmployeeIdAsc = true;
      this.sortIcon = 'fa fa-sort-asc fa-sm';
    } else {
      this.userData.sort((a,b) => {
        if (+a.employeeId > +b.employeeId) return -1;
        else if (+a.employeeId < +b.employeeId) return 1;
        else 0;
      });
      this.isEmployeeIdAsc = false;
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
