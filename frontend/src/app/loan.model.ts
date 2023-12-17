export class Loan {
    constructor(
        public id: number,
        public name: string,
        public totalLoan: number,
        public interestRate: number,
        public years: number,
        public monthlyPayment: number
    ) { }
}