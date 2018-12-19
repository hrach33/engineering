import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class TaskManagementService {

  constructor(private http: HttpClient) { }

  count(){
    return this.http.get<any>('/emergency/countTasks');
  }

  search(pageInfo){
    return this.http.post<any>('/emergency/searchTasks', {pageInfo:pageInfo});

  }

  getAllTeams(){
    return this.http.get<any>('/emergency/getAllTeams');
  }

  save(task){
    return this.http.post<any>('/emergency/addTask', task);
  }
}
