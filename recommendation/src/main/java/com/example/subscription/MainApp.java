package com.example.subscription;

public class MainApp {


    public static void main(String[] args) {
        System.out.println("Созданы товар и подписчики");
        Product product = new Product( 230);
        Subscriber1 subscriber1 =new Subscriber1(product);
        Subscriber2 subscriber2 =new Subscriber2(product);
        System.out.println("===========================");

        System.out.println("Изменение цены продукта");
        product.changePrice(180);
        System.out.println("==========================");
        System.out.println("Изменение цены продукта");
        product.changePrice(95);

    }
}
