import { Routes, RouterModule } from '@angular/router';

import { HomeComponent } from './home';
import { LoginComponent } from './login';
import { RegisterComponent } from './register';
import { AuthGuard } from './_guards';
import {UserComponent} from "./user/user.component";

const appRoutes: Routes = [
  { path: '', component: HomeComponent,canActivate: [AuthGuard],
    children: [
      { path: '', redirectTo: 'user', pathMatch: 'full' },
      { path: 'user', component: UserComponent }
    ]
  },
    { path: 'login', component: LoginComponent },
    { path: 'register', component: RegisterComponent },


    // otherwise redirect to home
    { path: '**', redirectTo: '' }
];

export const routing = RouterModule.forRoot(appRoutes);
