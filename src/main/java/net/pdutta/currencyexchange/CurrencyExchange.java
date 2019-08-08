package net.pdutta.currencyexchange;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;

public class CurrencyExchange {
    private String name;
    private HashMap<String, BigDecimal> rates;

    private CurrencyExchange (String name, HashMap<String, BigDecimal> rates) {
        this.name = name;
        this.rates = rates;
    }

    public static CurrencyExchangeBuilder builder () {
        return new CurrencyExchangeBuilder();
    }

    @Override
    public String toString () {
        var sb = new StringBuilder(name).append(" Currency Exchange\n");
        for (String currencyPair: rates.keySet()) {
            sb.append(currencyPair).append(": ").append(rates.get(currencyPair)).append("\n");
        }
        return sb.toString();
    }

    public Money convert (Money amountInSourceCurrency, Money.CurrencyName targetCurrency) {

        // handle same currency -> same currency conversion
        if (amountInSourceCurrency.getCurrencyName().name().equals(targetCurrency.name()))
            return amountInSourceCurrency;

        String currencyPair = amountInSourceCurrency.getCurrencyName() + "/" + targetCurrency;
        if (!rates.containsKey(currencyPair))
            throw new IllegalArgumentException("Conversion not offered for currency pair: " + currencyPair);

        BigDecimal result = amountInSourceCurrency.getAmount().multiply(rates.get(currencyPair))
                                .setScale(2, RoundingMode.HALF_UP);

        return Money.build(result, targetCurrency);
    }

    public static class CurrencyExchangeBuilder {
        private String name;
        private ArrayList<String> fromCurrencyList, toCurrencyList;
        private ArrayList<BigDecimal> amountList;

        CurrencyExchangeBuilder () {
            fromCurrencyList = new ArrayList<>();
            toCurrencyList = new ArrayList<>();
            amountList =  new ArrayList<>();
        }

        public CurrencyExchangeBuilder withName (String name) {
            this.name = name;
            return this;
        }

        public CurrencyExchangeBuilder withRate (
                Money.CurrencyName fromCurrency, Money.CurrencyName toCurrency,
                String amountAsString) throws NumberFormatException {
            this.fromCurrencyList.add(fromCurrency.name());
            this.toCurrencyList.add(toCurrency.name());
            this.amountList.add(new BigDecimal(amountAsString));
            return this;
        }

        public CurrencyExchange build () {
            assert (fromCurrencyList.size() == toCurrencyList.size()
                    && toCurrencyList.size() == amountList.size());
            HashMap<String, BigDecimal> rates = new HashMap<>();

            int nRates = fromCurrencyList.size();
            for (int ix = 0; ix < nRates; ix++) {
                String key = fromCurrencyList.get(ix) + "/" + toCurrencyList.get(ix);
                rates.put(key, amountList.get(ix));
            }
            return new CurrencyExchange(name, rates);
        }
    }
}
