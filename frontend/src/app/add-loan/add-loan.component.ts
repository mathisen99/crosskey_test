// add-loan.component.ts
import { Component } from '@angular/core';
import { Loan } from '../loan.model';
import { ApiService } from '../api.service';

@Component({
  selector: 'app-add-loan',
  templateUrl: './add-loan.component.html',
  styleUrls: ['./add-loan.component.css']
})
export class AddLoanComponent {
  newLoan: Loan = {
    id: 0,
    name: '', // Initialize with empty values
    totalLoan: 0,
    interestRate: 0,
    years: 0,
    monthlyPayment: 0,
  };

  constructor(private apiService: ApiService) {}

  addLoan() {
    // Call the API service to add the new loan
    this.apiService.createLoan(this.newLoan).subscribe((response) => {
      console.log('Loan added successfully:', response);
      // You can handle success here
    }, (error) => {
      console.error('Error adding loan:', error);
      // Handle error here
    });
  }
}
