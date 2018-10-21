import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})

export class TaskManagementService {

  constructor(private http: HttpClient) { }

  getAllTasks() {
    return this.http.get<any>('/emergency/getAllTasks');
  }

}
