package com.example.subscription;

public class Subscriber2 implements IObserver{
    private IObservable product;

    public Subscriber2(IObservable product) {

        this.product = product;
        IObservable obj = product;
        obj.addObserver( this);
    }


    @Override
    public void update(double p) {
if (p<100){
    System.out.println("Рекомендация покупки товара" + p);
    product.removeObserver(this);
}
    }
}
