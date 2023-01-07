import { links } from 'src/app/utils/link-constants';
import { Component, ViewChild } from '@angular/core';
import { Router } from '@angular/router';

import { UserListComponent } from 'src/app/components/user-list/user-list.component';


/**
 * Renderes the read user component.
 * Here are shown all users in a grid view.
 * We can navigate to create new user page.
 */
@Component({
  selector: 'app-read-user',
  templateUrl: './read-users.component.html',
  styleUrls: ['./read-users.component.scss'],
})
export class ReadUserComponent {


  constructor(
    private router: Router,
  ) {}

  @ViewChild(UserListComponent) userListComponent: UserListComponent;


  /**
   * Redirect to the create new user page.
   */
  navigateToCreateUserPage() {
    this.router.navigate([links.createUser.navigateTo]);
  }


}
