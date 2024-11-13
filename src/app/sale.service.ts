import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class SaleService {

  private apiUrl = 'http://localhost:8080/sale/addSale';

  private url = 'http://localhost:8080/sale/getAllProducts'

  constructor(private http: HttpClient) {}

  addSale(saleData: any) {
    return this.http.post<any>(this.apiUrl, saleData); 
  }

  getProducts() {
    return this.http.get<any[]>(this.url);
  }

}
