import { Component, OnInit } from '@angular/core';;
import {TeamManagementService} from "../_services/team-management.service";

@Component({
  selector: 'app-team',
  templateUrl: './team.component.html',
  styleUrls: ['./team.component.css']
})
export class TeamComponent implements OnInit {
  teams =[];
  constructor(private teamManagement: TeamManagementService) { }

  ngOnInit() {
    this.teamManagement.getAllTeams().subscribe(res=>{
      this.teams = res.data;
    },err =>{
      console.log(err);
      }
      )
  }
}
