package com.Application.Model.store;

import java.util.ArrayList;

public class SalesLogImpl implements SalesLog {

  private ArrayList<Sale> sales;

  public SalesLogImpl() {
    sales = new ArrayList<Sale>();
  }

  public ArrayList<Sale> getSales() {
    return sales;
  }

  public void setSales(ArrayList<Sale> sales) {
    this.sales = sales;
  }

  public void addSale(Sale sale) {
    sales.add(sale);
  }

  public Sale getSale(int saleId) {
    for (Sale sale : sales) {
      if (sale.getId() == saleId) {
        return sale;
      }
    }
    return null;
  }

}
