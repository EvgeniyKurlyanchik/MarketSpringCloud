package com.example.subscription;

public class Subscriber1 implements IObserver{
    private IObservable product;

    public Subscriber1(IObservable product) {
        this.product = product;
        IObservable obj = product;
        obj.addObserver( this);
    }

    @Override
    public void update(double p) {
if (p<200){
    System.out.println("Рекомендация покупки товара" + p);
    product.removeObserver(this);
}
    }
}
