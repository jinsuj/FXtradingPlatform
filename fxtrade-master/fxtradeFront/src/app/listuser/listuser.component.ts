import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ListuserService } from '../services/listuser.service'

@Component({
  selector: 'app-listuser',
  templateUrl: './listuser.component.html',
  styleUrls: ['./listuser.component.css']
})
export class ListuserComponent implements OnInit {
  userData: any[][] = [[],[]];
  page = 1;
  count = 0;
  tableSize = 7;
  tableSizes = [7 , 14 , 21, 35];
  total = 0;

  constructor( private listUserService: ListuserService, private router: Router) { }

  ngOnInit(): void {
    this.listUserService.getData()
      .subscribe(data => {
        this.userData = data;
        console.log(this.userData);
      });
  }

  getHistory(): void {
    this.listUserService.getData()
      .subscribe(data => {
        this.userData = data;
        console.log(this.userData);
      });
  }

  goDetails(id: string): void {
    this.router.navigate(['/user-details'], {queryParams: {id: id}})
  }

  onTableDataChange(event: any){
    this.page = event;
    this.getHistory();
  }

  onTableSizeChange(event: any): void {
    this.tableSize = event.target.value;
    this.page = 1;
    this.getHistory();
  }

  public selectedName: any;

}
