package com.example.recommendation;

import lombok.Builder;
import lombok.ToString;

@Builder
@ToString
public class RecommendationProduct {
    private  final  int  id;
    private final String productTitle;
    private  final  int  price;
    private  final  double  weight;
    private  final  int   discount;
    private  final  int   loyaltyPoints;
}
