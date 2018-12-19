import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class TeamManagementService {

  constructor(private http: HttpClient) { }
  count(params){
    return this.http.post<any>('/emergency/countTeams', {searchFilter: {searchParams: params}});
  }

  search(params, pageInfo, orderInfo){
    return this.http.post<any>('/emergency/searchTeams', {searchFilter: {searchParams: params}, pageInfo:pageInfo, orderInfoList: orderInfo});

  }

  update(team){
    return this.http.post<any>('/emergency/insertOrUpdateTeam', team);

  }

  deleteTeam(id){
    return this.http.get<any>('emergency/deleteTeam/' + id);
  }
}
