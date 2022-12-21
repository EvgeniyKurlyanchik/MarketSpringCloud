package com.example.currency;

import java.util.Currency;
import java.util.HashMap;

public class CurrencyRateServiceProxy implements  CurrencyRateService{

    private final CurrencyRateService currencyRateService;
  private  final HashMap < Currency,Double> currencyRatesMap =new HashMap<>();
   public CurrencyRateServiceProxy (){
       this.currencyRateService = new CurrencyRateServiceImpl();
   }

    @Override
    public double getDailyCurrencyRate(Currency currency) {
       if(currencyRatesMap.containsKey(currency)){
           return currencyRatesMap.get(currency);
       }
       double dailyRete = currencyRateService.getDailyCurrencyRate(currency);
       currencyRatesMap.put(currency,dailyRete);


        return dailyRete;
    }
}
