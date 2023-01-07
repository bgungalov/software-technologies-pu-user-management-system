import { AppLink } from './../../../models/app-link.model';
import { Component, OnInit } from '@angular/core';
import { links } from 'src/app/utils/link-constants';
import { AuthenticationService } from 'src/app/services/authentication-service/authentication.service';

/**
 * Renders protected layout component.
 */
@Component({
  selector: 'app-protected-layout',
  templateUrl: './protected-layout.component.html',
  styleUrls: ['./protected-layout.component.scss'],
})
export class ProtectedLayoutComponent {
  appLinks: AppLink[];

  change_menu_icon: boolean = true;

  constructor(private authenticationService: AuthenticationService) {
    this.appLinks = [
      links.home,
      links.readUsers,
      links.settings,
      links.actionsDashboard,
    ];
  }

  /**
   * This calls the logout() in authentication service component.
   * This is the logic for logout button.
   */
  handleLogout() {
    this.authenticationService.logout();
  }
}
