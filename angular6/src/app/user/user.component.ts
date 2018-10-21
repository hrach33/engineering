import { Component, OnInit } from '@angular/core';
import {UserManagementService} from "../_services/user-management.service";

@Component({
  selector: 'user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {

  users =[];
  constructor(private userManagement: UserManagementService) { }
  ngOnInit() {
    this.userManagement.getAllUsers().subscribe( res => {
      this.users = res.data;
    }, err => {
      console.log(err);
    })
  }
}
