import { Component, OnInit } from '@angular/core';
import { ApiService } from '../api.service'; // Adjust the import path based on your project structure
import { Loan } from '../loan.model'; // Adjust the import path based on your project structure

@Component({
  selector: 'app-loan-list',
  templateUrl: './loan-list.component.html',
  styleUrls: ['./loan-list.component.css']
})
export class LoanListComponent implements OnInit {
  loans: Loan[] = [];

  constructor(private apiService: ApiService) {}

  ngOnInit() {
    this.loadLoans();
  }

  loadLoans() {
    this.apiService.getLoans().subscribe((data) => {
      this.loans = data;
    });
  }
}
