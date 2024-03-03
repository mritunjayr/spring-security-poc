import { NgModule } from "@angular/core";
import { Routes, RouterModule } from "@angular/router";
import { LoginComponent } from "./login/login.component";
import { LogoutComponent } from "./logout/logout.component";
import { SuccessComponent } from "./success/success.component";
import { AppComponent } from "./app.component";
import { AuthGaurdService } from './service/auth-gaurd.service';
import { AuthenticationService } from "./service/authentication.service";

const routes: Routes = [
  { path: "", component: SuccessComponent, canActivate: [AuthGaurdService] },
  { path: "login", component: LoginComponent },
  {
    path: "logout",
    component: LogoutComponent,
    canActivate: [AuthGaurdService]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {})],
  exports: [RouterModule]
})
export class AppRoutingModule {}
