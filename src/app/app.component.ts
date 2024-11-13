import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { SaleService } from './sale.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  id: number | null = null; // Store ID for sale data
  date: string = ''; 
  data: any[] = [];
  errorMessage: string = ''; 
  noDataMessage: string = ''; 
  idd: { id: number, name: string }[] = []; // store id for store details
  maxDate: string= '';
  
  
  isAddSaleModalVisible: boolean = false;

  private apiUrl = 'http://localhost:8080/sale/totalSaleData'; 
  private storesApiUrl = 'http://localhost:8080/sale/storesId'; 

  constructor(private http: HttpClient, private saleService: SaleService) {}

  ngOnInit() {
    this.fetchStoreIds(); 
    this.datet();
  }

  datet() {
    // Set maxDate to today’s date in 'YYYY-MM-DD' format
    const today = new Date();
   
    this.maxDate = today.toISOString().split('T')[0];
  }

  fetchStoreIds() {
    this.http.get<any[]>(this.storesApiUrl).subscribe(
      (response) => {
        
        this.idd = response.map(store => ({
          id: store.id,
          name: store.sname
        }));

        if (this.idd.length > 0) {
          this.id = this.idd[0].id; // Set default store ID to the first one
        }
      },
      (error) => {
        this.errorMessage = 'Error fetching store IDs.';
      }
    );
  }

  

  onSubmit() {
    if (!this.date) {
      this.errorMessage = 'Date is required.';
      return;
    }
    this.errorMessage = '';
    this.noDataMessage = '';
    this.data = [];
    this.fetchDataFromBackend();
  }

  fetchDataFromBackend() {
    this.http
      .get<any[]>(`${this.apiUrl}?id=${this.id}&date=${this.date}`)
      .subscribe(
        (response) => {
          if (response && response.length > 0) {
            this.data = response;
            this.noDataMessage = '';
          } 
        },
        (error) => {
          if (error.status === 404) {
            const selectedStore = this.idd.find(store => store.id === this.id);
          const storeName = selectedStore ? selectedStore.name : 'Unknown Store';

          this.errorMessage = `No data available for Store ${storeName} on ${this.date}`;
          } else {
            this.errorMessage = 'Error fetching data from the backend.';
          }
        }
      );
  }

  openAddSaleModal() {
    this.isAddSaleModalVisible = true;
  }

  closeAddSaleModal() {
    this.isAddSaleModalVisible = false;
  }
}
