import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {UserManagementService} from "../_services/user-management.service";

@Component({
  selector: 'user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {

  users =[];
  itemsPerPage = 10;
  count = 0;
  pageIndex = 1;
  username;
  speciality;
  status;

  formOpened = false;
  editDisabled = true;
  form = {
    id:0,
    userName: '',
    firstName: '',
    lastName: '',
    password: '',
    speciality: '',
    birthDate: '',
    gender: '',
    status: ''
  }
  constructor(private userManagement: UserManagementService) { }
  ngOnInit() {
    this.search();
  }
  @ViewChild('userGrid') userGridRef: ElementRef;
  search(){
    let params = {
      user_name: this.username,
      speciality: this.speciality,
      status: this.status == 'Status' ? null : this.status}

    this.userManagement.count(params).subscribe( res => {
      this.count = res.data;
      let pageInfo = {index: 0, size: 0};
      pageInfo.index = this.pageIndex - 1;
      pageInfo.size = this.itemsPerPage;
      let orderInfo = {field: 'user_name', ascending: true};
      this.userManagement.search(params, pageInfo, orderInfo).subscribe(resp => {
        this.users = resp.data;
      }, error => {
        console.log(error);
      })
    }, err => {
      console.log(err);
    })
  }
  onPageChange(event){
    this.search();
  }

  rowClicked(event){
    this.form = event.data;
    this.editDisabled = false;
  }

  create() {
    this.form = {
      id:0,
      userName: '',
      firstName: '',
      lastName: '',
      password: '',
      speciality: '',
      birthDate: '',
      gender: '',
      status: ''
    }
    this.formOpened = true;
  }

  edit() {
    this.formOpened = true;
    this.form.password = null;
  }

  delete(){
      this.userManagement.deleteUser(this.form.id).subscribe( res => {
        this.search();
      }, err => {
        console.log(err);
      })
  }

  save() {
    this.userManagement.updateUser(this.form).subscribe(res => {
      this.form = {
        id:0,
        userName: '',
        firstName: '',
        lastName: '',
        password: '',
        speciality: '',
        birthDate: '',
        gender: '',
        status: ''
      }
      this.formOpened = false;
      this.search();
    }, err => {
      console.log(err);
    })
  }


}
