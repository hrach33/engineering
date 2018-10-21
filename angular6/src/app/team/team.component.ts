import { Component, OnInit } from '@angular/core';
import {TeamManagementService} from "../_services/team-management.service";

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

  constructor(private teamManagement: TeamManagementService) { }

  ngOnInit() {
    this.search();
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

}
