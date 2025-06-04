package com.myproject;

import java.util.*;

public class StockFeeder {
    private List<Stock> stockList = new ArrayList<>();
    private Map<String, List<StockViewer>> viewers = new HashMap<>();
    private static StockFeeder instance = null;

    // TODO: Implement Singleton pattern
    private StockFeeder() {}

    public static StockFeeder getInstance() {
        if (instance == null) {
                if (instance == null) {
                    instance = new StockFeeder();
                }
        }
        return instance;
        // TODO: Implement Singleton logic
    }

    public void addStock(Stock stock) 
    {
        for(Stock st : stockList) 
            if(st == stock) return; 
        stockList.add(stock);
    }

    public void registerViewer(String code, StockViewer stockViewer) {
        boolean existence = false;
        for(Stock stock : stockList ){
            if(stock.getCode() == code){
                existence = true;
                break;
            } 
        }
        if(!existence){
            Logger.errorRegister(code);
            return;
        }
        List<StockViewer> viewList = viewers.getOrDefault(code, new ArrayList<>());
            for(StockViewer sv : viewList ){
                if(sv.getClass().equals(stockViewer.getClass())){
                    Logger.errorRegister(code);
                    return;
                }
            }
        

        viewers.putIfAbsent(code, new ArrayList<>());
        viewers.get(code).add(stockViewer);
    }    

    public void unregisterViewer(String code, StockViewer stockViewer) 
    {
        boolean existence = false;
        for(Stock stock : stockList )
        {
            if(stock.getCode().equals(code))
            {
                existence = true;
                break;
            }  	
        }
        if(!existence)
        {
            Logger.errorUnregister(code);
            return;
        }
        viewers.get(code).remove(stockViewer);
    }

    public void notify(StockPrice stockPrice) 
    {
        String code = stockPrice.getCode();
        if(viewers.get(code) != null)
            for(StockViewer stockViewer : viewers.get(code))
                stockViewer.onUpdate(stockPrice);
    }
}
