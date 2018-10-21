import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})

export class TeamManagementService {

  constructor(private http: HttpClient) { }
  getAllTeams() {
    return this.http.get<any>('/emergency/getAllTeams');
  }
}
