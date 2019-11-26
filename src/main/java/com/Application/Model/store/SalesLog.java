package com.b07.store;

import java.util.ArrayList;

public interface SalesLog {

  public ArrayList<Sale> getSales();

  public void setSales(ArrayList<Sale> sales);

  public void addSale(Sale sale);

  public Sale getSale(int saleId);
}
