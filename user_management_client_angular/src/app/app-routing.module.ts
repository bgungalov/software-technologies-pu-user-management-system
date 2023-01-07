import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ProtectedLayoutComponent } from './components/layouts/protected-layout/protected-layout.component';
import { PublicLayoutComponent } from './components/layouts/public-layout/public-layout.component';
import { AuthenticationGuard } from './guards/AuthenticationGuard';
import { links } from '../app/utils/link-constants';
import { PublicGuard } from './guards/PublicGuard';

const routes: Routes = [
  { path: '', redirectTo: links.home.path, pathMatch: 'full' },
  //Public Layout routes
  {
    path: '',
    component: PublicLayoutComponent,
    children: [
      //public routes go here
      {
        path: links.login.path,
        canActivate: [PublicGuard],
        component: links.login.component,
        data: { title: links.login.title },
      },
      {
        path: links.pageNotFound.path,
        component: links.pageNotFound.component,
        data: { title: links.pageNotFound.title },
      },
    ],
  },
  {
    path: '',
    component: ProtectedLayoutComponent,
    children: [
      //routes secured behind a login go here
      {
        path: links.home.path,
        canActivate: [AuthenticationGuard],
        component: links.home.component,
        data: { title: links.home.title },
      },
      {
        path: links.createUser.path,
        canActivate: [AuthenticationGuard],
        component: links.createUser.component,
        data: {
          title: links.createUser.title,
        },
      },
      {
        path: links.readUsers.path,
        canActivate: [AuthenticationGuard],
        component: links.readUsers.component,
        data: { title: links.readUsers.title },
      },
      {
        path: links.actionsDashboard.path,
        canActivate: [AuthenticationGuard],
        component: links.actionsDashboard.component,
        data: { title: links.actionsDashboard.title },
      },
      {
        path: links.userDetails.path,
        canActivate: [AuthenticationGuard],
        component: links.userDetails.component,
        data: {
          title: links.userDetails.title,
        },
      },
      {
        path: links.settings.path,
        canActivate: [AuthenticationGuard],
        component: links.settings.component,
        data: { title: links.settings.title },
      },
      {
        path: links.updateUser.path,
        canActivate: [AuthenticationGuard],
        component: links.updateUser.component,
        data: {
          title: links.updateUser.title,
        },
      },
    ],
  },
  { path: '**', redirectTo: links.pageNotFound.path, pathMatch: 'full' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
  providers: [AuthenticationGuard],
})
export class AppRoutingModule {}
