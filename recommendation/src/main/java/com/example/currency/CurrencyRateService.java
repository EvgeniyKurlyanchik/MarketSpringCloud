package com.example.currency;

import java.util.Currency;

public interface CurrencyRateService {
    double getDailyCurrencyRate(Currency currency);
}
