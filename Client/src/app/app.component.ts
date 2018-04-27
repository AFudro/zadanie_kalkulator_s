import { Component } from '@angular/core';
import { Currency } from './currency';
import { CurrencyService } from './currency.service';
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'calc';
  amount;
  result;
  response;
  selected: Currency;
  currencies: Currency[];
  errorMessage;
  sentCurrency;
  sentAmount;
  resultControl;

  onSubmit(): void {
    this.errorMessage = false;
    this.sentAmount = this.amount;
    this.sentCurrency = this.selected.currency;


    this.currencyService.getNetIncome(this.selected.id, this.amount)
      .subscribe((result) => {
        this.result = result;
        if (result === null || result === undefined) { this.resultControl = 0 } else { this.resultControl = 1 }
      }, error => this.errorMessage = true);
}


constructor(private currencyService: CurrencyService) { }


getCurrencies(): void {
  this.currencyService.getCurrencies()
    .subscribe(currencies => this.currencies = currencies, error => this.errorMessage = true);
}

ngOnInit() {
  this.getCurrencies();
}

}
