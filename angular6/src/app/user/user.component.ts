import { Component, OnInit } from '@angular/core';
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
  constructor(private userManagement: UserManagementService) { }
  ngOnInit() {
    this.search();
  }
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
}
