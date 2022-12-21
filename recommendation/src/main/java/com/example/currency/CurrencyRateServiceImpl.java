package com.example.currency;

import java.util.Currency;
import java.util.Random;

public class CurrencyRateServiceImpl implements CurrencyRateService {
    @Override
    public double getDailyCurrencyRate(Currency currency) {
       Random rnd = new Random();
       double result =100 * rnd.nextDouble();
       double scale = Math.pow(10,2);


        return Math.round(result*scale)/scale;
    }
}
