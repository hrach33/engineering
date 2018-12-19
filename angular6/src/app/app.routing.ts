import { Routes, RouterModule } from '@angular/router';

import { HomeComponent } from './home';
import { LoginComponent } from './login';
import { RegisterComponent } from './register';
import { AuthGuard } from './_guards';
import {UserComponent} from "./user/user.component";
import {TeamComponent} from "./team/team.component";
import {TaskComponent} from "./task/task.component";

const appRoutes: Routes = [
  { path: '', component: HomeComponent,
    children: [
      { path: '', redirectTo: 'user', pathMatch: 'full' },
      { path: 'user', component: UserComponent },
      { path: 'team', component: TeamComponent },
      { path: 'task', component: TaskComponent }
    ]
  },
    { path: 'login', component: LoginComponent },
    { path: 'register', component: RegisterComponent },


    // otherwise redirect to home
    { path: '**', redirectTo: '' }
];

export const routing = RouterModule.forRoot(appRoutes);
