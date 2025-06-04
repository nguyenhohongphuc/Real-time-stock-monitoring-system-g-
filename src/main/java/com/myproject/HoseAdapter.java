package com.myproject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HoseAdapter implements PriceFetcher {
    private HosePriceFetchLib hoseLib;
    private List<String> stockCodes;
 
    public HoseAdapter(HosePriceFetchLib hoseLib, List<String> stockCodes) {
        this.hoseLib = hoseLib;
        this.stockCodes = stockCodes;
        // TODO: Implement constructor
    }

    @Override
    public List<StockPrice> fetch() {
        List<HoseData> hoseDataList = hoseLib.getPrices(stockCodes);
        List<StockPrice> stockPrices = new ArrayList<>();

        for (HoseData data : hoseDataList) {
            stockPrices.add(convertToStockPrice(data));
        }

        return stockPrices;

        // TODO: Fetch stock data and convert it to StockPrice list
    }

    private StockPrice convertToStockPrice(HoseData hoseData) {
        return new StockPrice(
        hoseData.getStockCode(),
        hoseData.getPrice(),
        hoseData.getVolume(),
        hoseData.getTimestamp()
    );
        // TODO: Convert HoseData to StockPrice
    }
}
