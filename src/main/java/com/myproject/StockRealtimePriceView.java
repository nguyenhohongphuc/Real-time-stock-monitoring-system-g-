package com.myproject;

import java.util.HashMap;
import java.util.Map;

public class StockRealtimePriceView implements StockViewer {
    private final Map<String, Double> lastPrices = new HashMap<>();

    @Override
    public void onUpdate(StockPrice stockPrice) {
        String code = stockPrice.getCode();
        double price =  stockPrice.getAvgPrice();
        if(code == null) return;
        if(!lastPrices.containsKey(code)) lastPrices.put(code, price);
        else if(lastPrices.get(code) != price){
            lastPrices.put(code, price);
            Logger.logRealtime(code,price);
        }
        // TODO: Implement logic to check if price has changed and log it
    }
}
