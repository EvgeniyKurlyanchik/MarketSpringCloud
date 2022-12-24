package com.example.subscription;

import java.util.ArrayList;
import java.util.List;

class Product implements IObservable{
     private List<IObserver> observers;
     private  double price;

    public Product(double p) {
        observers = new ArrayList<>();
        price = p;
    }
public void changePrice(double p){
    price=p;
updateNotify();
}

    @Override
     public void addObserver(IObserver o) {
observers.add(o);
     }

     @Override
     public void removeObserver(IObserver o) {
observers.remove(o);

     }

     @Override
     public void updateNotify() {
        for (Object o: observers.toArray()){
            ((IObserver)o).update(price);


        }
     }



 }
