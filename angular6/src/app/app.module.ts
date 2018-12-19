import { NgModule }      from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { ReactiveFormsModule }    from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';

// used to create fake backend
import { fakeBackendProvider } from './_helpers';

import { AppComponent }  from './app.component';
import { routing }        from './app.routing';

import { AlertComponent } from './_directives';
import { AuthGuard } from './_guards';
import { JwtInterceptor, ErrorInterceptor } from './_helpers';
import { AlertService, AuthenticationService, UserService } from './_services';
import { HomeComponent } from './home';
import { LoginComponent } from './login';
import { RegisterComponent } from './register';
import { UserComponent} from "./user/user.component";
import { DxDataGridModule} from "devextreme-angular";
import { TaskComponent } from './task/task.component';
import { TeamComponent } from './team/team.component';
import {PaginationConfig, PaginationModule} from 'ngx-bootstrap';
// import {NgxPaginationModule} from "ngx-pagination";
import { FormsModule } from '@angular/forms';
import {AngularMultiSelectModule} from "angular2-multiselect-dropdown";

@NgModule({
    imports: [
        BrowserModule,
        ReactiveFormsModule,
        HttpClientModule,
        DxDataGridModule,
        PaginationModule,
        FormsModule,
      AngularMultiSelectModule,
        routing
    ],
  declarations: [
        AppComponent,
        AlertComponent,
        HomeComponent,
        LoginComponent,
        RegisterComponent,
        TeamComponent,
        TaskComponent,
        UserComponent],
    providers: [
        AuthGuard,
        AlertService,
        AuthenticationService,
        UserService,
        PaginationConfig,
        { provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true },
        { provide: HTTP_INTERCEPTORS, useClass: ErrorInterceptor, multi: true },

        // provider used to create fake backend
        fakeBackendProvider
    ],
    bootstrap: [AppComponent]
})

export class AppModule { }
