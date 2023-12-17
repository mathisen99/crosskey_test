export interface Loan {
    id: number;
    name: string;
    totalLoan: number;
    interestRate: number;
    years: number;
    monthlyPayment: number;
  }
  
  export interface LoanCreation {
    name: string;
    totalLoan: number;
    interestRate: number;
    years: number;
  }