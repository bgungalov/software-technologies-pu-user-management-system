import { TokenStorageService } from './../../../services/storage/token-storage.service';
import { Component } from '@angular/core';

/**
 * Renders home component.
 */
@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss'],
})
export class HomeComponent {
  decodedToken = this.tokenStorage.decodeToken();
  tokenUserFullName =
    `${this.decodedToken.user.name}`;
  tokenUserRoles = this.decodedToken.user.userRoles[0].authority;

  constructor(private tokenStorage: TokenStorageService) {}
}
