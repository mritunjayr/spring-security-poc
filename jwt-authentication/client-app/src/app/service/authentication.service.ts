import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { map } from "rxjs/operators";
import { Router } from "@angular/router";
export class User {
  status: string;
}
@Injectable({
  providedIn: "root"
})
export class AuthenticationService {
  constructor(private httpClient: HttpClient, private router: Router) {}

  authenticate(username, password) {
    return this.httpClient
      .post<any>("http://localhost:8080/authenticate", { username, password })
      .subscribe(userData => {
        sessionStorage.setItem("username", username);
        let tokenStr = "Bearer " + userData.token;
        sessionStorage.setItem("token", tokenStr);
        this.router.navigate([""]);
        return userData;
      },(err) => {
        console.error('Authentication failed',err);
        sessionStorage.setItem("username", "failed to authenticate");
        return true;
      });

  }

  isUserLoggedIn() {
    let user = sessionStorage.getItem("username");
    // console.log(!(user === null));
    return !!user;
  }

  logOut() {
    sessionStorage.removeItem("username");
    sessionStorage.removeItem("token");
  }
}
