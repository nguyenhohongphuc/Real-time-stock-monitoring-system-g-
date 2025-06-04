package com.myproject;

import java.util.HashMap;
import java.util.Map;

public class StockAlertView implements StockViewer {
    private double alertThresholdHigh;
    private double alertThresholdLow;
    private Map<String, Double> lastAlertedPrices = new HashMap<>(); // TODO: Stores last alerted price per stock

    public StockAlertView(double highThreshold, double lowThreshold) {
        this.alertThresholdHigh =  highThreshold;
        this.alertThresholdLow = lowThreshold;
        // TODO: Implement constructor
    }

    @Override
    public void onUpdate(StockPrice stockPrice) {
        String code = stockPrice.getCode();
        double price = stockPrice.getAvgPrice();
        Double lastPrice = lastAlertedPrices.get(code);
        if(lastAlertedPrices.containsKey(code) && lastAlertedPrices.get(code).equals(price)){
            return;
        }
        lastAlertedPrices.put(code, price);
        if(price >= alertThresholdHigh && (lastPrice == null || !lastPrice.equals(price))){
            
            alertAbove(code, price);
        }
        if(price <= alertThresholdLow && (lastPrice == null || !lastPrice.equals(price))){
            alertBelow(code, price);
        }
        // TODO: Implement alert logic based on threshold conditions
    }

    private void alertAbove(String stockCode, double price) {
        Logger.logAlert(stockCode, price);
        // TODO: Call Logger to log the alert
    }

    private void alertBelow(String stockCode, double price) {
        Logger.logAlert(stockCode, price);
        // TODO: Call Logger to log the alert
    }
}
