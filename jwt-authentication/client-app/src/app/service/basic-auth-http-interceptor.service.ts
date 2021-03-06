import { Injectable } from "@angular/core";
import {
  HttpInterceptor,
  HttpRequest,
  HttpHandler
} from "@angular/common/http";

@Injectable({
  providedIn: "root"
})
export class BasicAuthHttpInterceptorService implements HttpInterceptor {
  constructor() {}

  intercept(req: HttpRequest<any>, next: HttpHandler) {
    if (sessionStorage.getItem("username") && sessionStorage.getItem("token")) {
      req = req.clone({
        setHeaders: {
          "Access-Control-Allow-Origin": "*",
          Authorization: sessionStorage.getItem("token")
        }
      });
    }
    return next.handle(req);
  }
}
