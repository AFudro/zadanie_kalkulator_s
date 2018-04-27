import { Injectable } from '@angular/core';
import { Currency } from './currency';
import { Observable } from 'rxjs/Observable';
import { of } from 'rxjs/observable/of';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable()
export class CurrencyService {
  // private url = 'http://localhost:8080/currencies';

  getCurrencies(): Observable<Currency[]> {
    let url = 'http://localhost:8080/currencies';
    return this.http.get<Currency[]>(url)
  }
  getNetIncome(id: number ,amount: number): Observable<number> {
    let url = `http://localhost:8080/net-monthly-income?id=${id}&amount=${amount}`;
    return this.http.get<number>(url)
  }

  constructor(private http: HttpClient) { }

}
