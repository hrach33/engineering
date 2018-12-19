import { Component, OnInit } from '@angular/core';
import {TeamManagementService} from "../_services/team-management.service";
import {UserManagementService} from "../_services/user-management.service";

@Component({
  selector: 'app-team',
  templateUrl: './team.component.html',
  styleUrls: ['./team.component.css']
})
export class TeamComponent implements OnInit {

  teams =[];
  itemsPerPage = 10;
  count = 0;
  pageIndex = 1;
  teamName;
  speciality;
  status;


  dropdownList = [];
  selectedItems = [];
  dropdownSettings = {};


  formOpened = false;
  editDisabled = true;
  form = {
    id:0,
    name: '',
    speciality: '',
    capacity: 0,
    users: [],
    status: ''
  }

  constructor(private teamManagement: TeamManagementService, private userManagement: UserManagementService) { }

  ngOnInit() {
    this.userManagement.getAllUsers().subscribe( res=> {
      for(var i = 0; i< res.data.length; i++){
        this.dropdownList.push({"id" : res.data[i].id, "itemName": res.data[i].userName})
      }
    }, err=> {

    })
    this.search();
    this.dropdownList = [
    ];
    this.selectedItems = [
    ];
    this.dropdownSettings = {
      singleSelection: false,
      text:"Select Users",
      selectAllText:'Select All',
      unSelectAllText:'UnSelect All',
      enableSearchFilter: true,
      classes:"myclass custom-class"
    };
  }

  onItemSelect(item:any){
    console.log(item);
    console.log(this.selectedItems);
  }
  OnItemDeSelect(item:any){
    console.log(item);
    console.log(this.selectedItems);
  }
  onSelectAll(items: any){
    console.log(items);
  }
  onDeSelectAll(items: any){
    console.log(items);
  }

  search(){
    let params = {name: this.teamName, speciality: this.speciality, team_status: this.status == 'Status' ? null : this.status}

    this.teamManagement.count(params).subscribe( res => {
      this.count = res.data;
      let pageInfo = {index: 0, size: 0};
      pageInfo.index = this.pageIndex - 1;
      pageInfo.size = this.itemsPerPage;
      let orderInfo = {field: 'user_name', ascending: true};
      this.teamManagement.search(params, pageInfo, orderInfo).subscribe(resp => {
        this.teams = resp.data;
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

  calculateCellValue(rowdata){
    let value = '';
    for(var i = 0; i <rowdata.users.length; i++){
      value+= rowdata.users[i].userName + ' '
    }
    return value;
  }

  rowClicked(event){
    this.form = event.data;

    this.editDisabled = false;
  }

  create() {
    this.form = {
      id:0,
      name: '',
      speciality: '',
      capacity: 0,
      users: [],
      status: ''
    }
    this.formOpened = true;
  }

  edit() {
    this.formOpened = true;
    for(var i = 0; i< this.form.users.length; i++){
      this.form.users[i].itemName = this.form.users[i].userName;
    }
  }

  delete(){
    this.teamManagement.deleteTeam(this.form.id).subscribe( res => {
      this.search();
    }, err => {
      console.log(err);
    })
  }

  save() {
    this.teamManagement.update(this.form).subscribe(res => {
      this.form = {
        id:0,
        name: '',
        speciality: '',
        capacity: 0,
        users: [],
        status: ''
      };
      this.formOpened = false;
      this.search();
    }, err => {
      console.log(err);
    })
  }

}
