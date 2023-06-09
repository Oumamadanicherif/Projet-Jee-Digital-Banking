import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Customer} from "../model/customer.model";
import {AccountDetails} from "../model/account.model";

@Injectable({
  providedIn: 'root'
})
export class CustomerService {
   backendHost : string="http://localhost:8085"; //in case changing backend server or do it in environnement
  constructor(private http:HttpClient) { }
  public getCustomers():Observable<Array<Customer>>{
    return this.http.get<Array<Customer>>(this.backendHost+"/customers");
  }
  public searchCustomers(keyword : string):Observable<Array<Customer>>{
    return this.http.get<Array<Customer>>(this.backendHost+"/customers/search?keyword="+keyword);
  }
  public saveCustomers(customer : Customer):Observable<Customer>{
    return this.http.post<Customer>(this.backendHost+"/customers",customer);
  }
  public deleteCustomer(id : number){
    return this.http.delete(this.backendHost+"/customers/"+id);
}
  public getCustomerAccounts(customerId: number): Observable<Array<AccountDetails>> {
    return this.http.get<Array<AccountDetails>>(this.backendHost+"/customers/{customerId}/accounts");
  }
}
