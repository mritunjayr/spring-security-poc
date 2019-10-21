import { Component, OnInit } from "@angular/core";
import { HttpClient } from "@angular/common/http";

@Component({
  selector: "app-success",
  templateUrl: "./success.component.html",
  styleUrls: ["./success.component.css"]
})
export class SuccessComponent implements OnInit {
  message$: any;

  constructor(private http: HttpClient) {}

  ngOnInit() {
    this.http.get<any>("http://localhost:8080/employee").subscribe(data => {
      this.message$ = data.status;
    });
  }
}
