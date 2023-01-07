import { links } from 'src/app/utils/link-constants';
import { ClickableCell } from './../../models/clickable-cell.model';
import { Router } from '@angular/router';
import { Component, OnDestroy, OnInit } from '@angular/core';
import { TableColumn } from 'src/app/models/table-column.model';
import { User } from 'src/app/models/user.model';
import { getUserColumns } from 'src/app/utils/table-columns';
import { UserApiService } from 'src/app/services/api-services/user-service/user-api.service';
import { Subscription } from 'rxjs';

/**
 * Renders the user list component.
 * Loads the users list into the grid.
 */
@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.scss'],
})
export class UserListComponent implements OnInit, OnDestroy {
  users: User[];
  columns: TableColumn[] = getUserColumns();
  apiSubscription: Subscription;

  constructor(private router: Router, private apiService: UserApiService) {}

  /**
   * Handling the clickable cell in the grid.
   * @param event which is passed.
   */
  handleCellClick(event: ClickableCell) {
    switch (event.column.dataKey) {
      case this.columns[0].dataKey:
        this.router.navigate([links.userDetails.navigateTo, event.value.id]);
        break;
      case this.columns[1].dataKey:
        console.log(event.value.firstName);
        break;
      case this.columns[2].dataKey:
        console.log(event.value.lastName);
        break;
    }
  }

  /**
   * Get all users from API and load them to the grid.
   */
  ngOnInit(): void {
    this.apiSubscription = this.apiService
      .getUsers()
      .subscribe((data) => (this.users = data));
  }

  /**
   * Runs after the component is destroyed.
   * Passing all subsctiptions that need to be unsubscribed from.
   */
  ngOnDestroy(): void {
    this.apiSubscription.unsubscribe();
  }
}
