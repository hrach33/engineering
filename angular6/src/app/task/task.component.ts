import { Component, OnInit } from '@angular/core';
import {TaskManagementService} from "../_services/task-management.service";
import { interval } from 'rxjs';

@Component({
  selector: 'app-task',
  templateUrl: './task.component.html',
  styleUrls: ['./task.component.css']
})
export class TaskComponent implements OnInit {
  tasks =[];
  teams=[]
  itemsPerPage = 10;
  count = 0;
  pageIndex = 1;

  formOpened = false;
  editDisabled = true;
  form = {
    id:0,
    type: '',
    destination: '',
    description: '',
    teamId: 0,
    level: 0,
    state: '',
    status: ''
  }
  constructor(private takManagement: TaskManagementService) { }

  ngOnInit() {
    this.takManagement.getAllTeams().subscribe(res => {
      this.teams = res.data;
    }, err => {

    })
    interval(2000).subscribe(res => {
      this.search();
    })


  }

  search(){

    this.takManagement.count().subscribe( res => {
      this.count = res.data;
      let pageInfo = {index: 0, size: 0};
      pageInfo.index = this.pageIndex - 1;
      pageInfo.size = this.itemsPerPage;

      this.takManagement.search(pageInfo).subscribe(resp => {
        this.tasks = resp.data;
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
      type: '',
      destination: '',
      description: '',
      teamId: 0,
      level: 0,
      state: '',
      status: ''
    }
    this.formOpened = true;
  }

  edit() {
    this.formOpened = true;
  }

  delete(){

  }

  save() {
    this.takManagement.save(this.form).subscribe(res => {
      this.form = {
        id:0,
        type: '',
        destination: '',
        description: '',
        teamId: 0,
        level: 0,
        state: '',
        status: ''
      }
      this.formOpened = false;
      this.search();
    }, err => {
      console.log(err);
    })
  }




}
