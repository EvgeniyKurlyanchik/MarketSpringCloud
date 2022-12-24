package com.example.recommendation;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class App {
    public static void main(String[] args) {
        RecommendationProduct ProductOfTheDay = pd();
        RecommendationProduct  bestProduct  =  pb();
        log.info("RecommendationProduct 1 : {}", ProductOfTheDay);
        log.info("RecommendationProduct 2: {}", bestProduct);
    }

    public static RecommendationProduct pd(){
        return RecommendationProduct.builder()
                .id(12)
                .productTitle("Milk")
                .price(80)
                .weight(0.95)
                .discount(20)
                .build();

    }
    public static RecommendationProduct pb(){
        return RecommendationProduct.builder()
                .id(120)
                .productTitle("Nuts")
                .price(800)
                .weight(1.4)
                .discount(10)
                .loyaltyPoints(100)
                .build();

    }
}
