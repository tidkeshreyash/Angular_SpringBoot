import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { SaleService } from '../sale.service';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-add-sale',
  templateUrl: './add-sale.component.html',
  styleUrls: ['./add-sale.component.css']
})
export class AddSaleComponent implements OnInit{
  @Input() isVisible: boolean = false;  // Show or hide the modal
  @Input() stores: any[] = [];  // List of stores
  @Output() closeModal = new EventEmitter<void>();

  productId: number = 0;
  quantity: number = 0;
  storeId: number | null = null;
  date: string = '';
  mrp: number = 0;
  errorMessage : String ='';

  product: any[] = [];
  
  maxDate: string = '';


  ngOnInit() {
    this.fetchProducts(); 
    this.datet();
  }

  
  
  datet() {
    // Set maxDate to today’s date in 'YYYY-MM-DD' format
    const today = new Date();
   
    this.maxDate = today.toISOString().split('T')[0];
  }



  constructor(private saleService: SaleService,private http:HttpClient) { }

  close() {
    this.closeModal.emit(); // Close the modal
  }

  onSubmit() {
    if (!this.date || !this.productId || !this.quantity || !this.storeId || !this.mrp) {
      this.errorMessage = 'All fields are required';
      return;
    }
    this.errorMessage = '';
    this.isVisible = false; // Close the modal after submission

    const saleData = {
      date: this.date,
      product: [
        {
          product_id: this.productId,
          sale_quantity: this.quantity,
          store_id: this.storeId,
          mrp: this.mrp
        }
      ]
    };

    this.saleService.addSale(saleData).subscribe(
      (response: any) => {
        console.log(response);
        alert(response.message); // Show success message from the response
        this.close();
      },
      (error) => {
        console.error(error);
        this.errorMessage = 'Error adding sale';
      }
    );
  }

  fetchProducts() {
    this.saleService.getProducts().subscribe(
      (response) => {
        this.product = response;
      },
      (error) => {
        console.error('Error fetching products:', error);
        this.errorMessage = 'Error fetching product data.';
      }
    );
  }

  

  
}
