import { Component, OnInit } from '@angular/core';
import {TaskManagementService} from "../_services/task-management.service";

@Component({
  selector: 'app-task',
  templateUrl: './task.component.html',
  styleUrls: ['./task.component.css']
})
export class TaskComponent implements OnInit {
  tasks =[];
  constructor(private taskManagement: TaskManagementService) { }

  ngOnInit() {
  this.taskManagement.getAllTasks().subscribe(res =>{
    this.tasks = res.data;
  }, err =>{
    console.log(err);
    }
    )
  }
}
