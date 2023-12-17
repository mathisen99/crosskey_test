// add-loan.component.ts
import { Component } from '@angular/core';
import { Loan, LoanCreation } from '../loan.model';
import { ApiService } from '../api.service';

@Component({
  selector: 'app-add-loan',
  templateUrl: './add-loan.component.html',
  styleUrls: ['./add-loan.component.css']
})
export class AddLoanComponent {
  newLoan: Loan = {
    id: 0,
    name: '',
    totalLoan: 0,
    interestRate: 0,
    years: 0,
    monthlyPayment: 0,
  };

  constructor(private apiService: ApiService) {}

  addLoan() {
    const loanData: LoanCreation = {
      name: this.newLoan.name,
      totalLoan: this.newLoan.totalLoan,
      interestRate: this.newLoan.interestRate,
      years: this.newLoan.years
    };
  
    this.apiService.createLoan(loanData).subscribe({
      next: (response) => {
        console.log('Loan added successfully:', response);
        // reload window
        window.location.reload();
      },
      error: (error) => {
        console.error('Error adding loan:', error);
        // Handle error here (show user-friendly message)
        alert('Error adding loan. Check console for details.');
      }
    });
  }
  

}
