import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { map } from "rxjs/operators";
export class User {
  staus: String;
}
@Injectable({
  providedIn: "root"
})
export class AuthenticationService {
  constructor(private httpClient: HttpClient) {}

  authenticate(username, password) {
    const headers = new HttpHeaders({
      Authorization: "Basic " + btoa(username + ":" + password)
    }).set("Content-Type", "text/plain;charset=UTF-8");
    console.log(headers);
    return this.httpClient
      .get<User>("http://localhost:8080/employee", {
        headers,
        responseType: "json"
      })
      .pipe(userData => {
        sessionStorage.setItem("username", username);
        let authString = "Basic " + btoa(username + ":" + password);
        sessionStorage.setItem("basicauth", authString);
        return userData;
      });
  }

  isUserLoggedIn() {
    let user = sessionStorage.getItem("username");
    console.log(!(user === null));
    return !(user === null);
  }

  logOut() {
    sessionStorage.removeItem("username");
  }
}
