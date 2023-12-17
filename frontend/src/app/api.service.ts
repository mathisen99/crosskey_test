import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Loan, LoanCreation } from './loan.model';

@Injectable({
  providedIn: 'root',
})
export class ApiService {
  private baseUrl = '/api/customers';

  constructor(private http: HttpClient) {}

  getLoans(): Observable<Loan[]> {
    return this.http.get<Loan[]>(`${this.baseUrl}`);
  }

  createLoan(loanData: LoanCreation): Observable<Loan> {
    return this.http.post<Loan>(`${this.baseUrl}`, loanData);
  }
}
