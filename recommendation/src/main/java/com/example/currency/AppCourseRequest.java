package com.example.currency;

import java.util.Currency;

public class AppCourseRequest {
    private static final String  USD = "USD";

    public static void main(String[] args) {
        CurrencyRateService currencyRateService= new CurrencyRateServiceProxy();
        Currency usd = Currency.getInstance(USD);

        System.out.println(currencyRateService.getDailyCurrencyRate(usd));
        System.out.println(currencyRateService.getDailyCurrencyRate(usd));
        System.out.println(currencyRateService.getDailyCurrencyRate(usd));
        System.out.println(currencyRateService.getDailyCurrencyRate(usd));
    }
}
