import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { map } from 'rxjs/operators';

import { environment } from '../../environments/environment';
import {JwtHelperService} from "@auth0/angular-jwt";

@Injectable()
export class AuthenticationService {
  helper = new JwtHelperService();
  constructor(private http: HttpClient) { }

    login(username: string, password: string) {
        return this.http.post<any>('/auth/login', { username: username, password: password })
            .pipe(map(res => {
                // login successful if there's a jwt token in the response
                if (res && res.data) {
                  let decoded = this.helper.decodeToken(res.data.jwt);
                    // store user details and jwt token in local storage to keep user logged in between page refreshes
                    localStorage.setItem('currentUser', JSON.stringify({username: decoded.userName, token:res.data.jwt}));
                }

                return res;
            }));
    }

    logout() {
        // remove user from local storage to log user out
        localStorage.removeItem('currentUser');
    }
}
