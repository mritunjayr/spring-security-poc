import { BrowserModule } from "@angular/platform-browser";
import { NgModule } from "@angular/core";
import { FormsModule } from "@angular/forms";
import { HttpClientModule, HTTP_INTERCEPTORS } from "@angular/common/http";

import { AppRoutingModule } from "./app-routing.module";
import { AppComponent } from "./app.component";
import { LoginComponent } from "./login/login.component";
import { LogoutComponent } from "./logout/logout.component";
import { HeaderComponent } from "./header/header.component";
import { SuccessComponent } from "./success/success.component";
import { BasicAuthHttpInterceptorService } from "./service/basic-auth-http-interceptor.service";

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    LogoutComponent,
    HeaderComponent,
    SuccessComponent
  ],
  imports: [BrowserModule, AppRoutingModule, FormsModule, HttpClientModule],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: BasicAuthHttpInterceptorService,
      multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule {}
