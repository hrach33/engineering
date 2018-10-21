import { Injectable } from '@angular/core';
import {map} from "rxjs/operators";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class UserManagementService {

  constructor(private http: HttpClient) { }

  getAllUsers() {
    return this.http.get<any>('/emergency/getAllUsers');
  }

  count(params){
    return this.http.post<any>('/emergency/countUsers', {searchFilter: {searchParams: params}});
  }

  search(params, pageInfo, orderInfo){
    return this.http.post<any>('/emergency/searchUsers', {searchFilter: {searchParams: params}, pageInfo:pageInfo, orderInfoList: orderInfo});

  }

}
