package net.pdutta.currencyexchange;

import java.math.BigDecimal;

public class Money {

    private BigDecimal amount;
    private CurrencyName currencyName;

    public static Money build (String amountAsString, CurrencyName currencyName) {
        return Money.build(new BigDecimal(amountAsString), currencyName);
    }

    public static Money build (BigDecimal amount, CurrencyName currencyName)
            throws NumberFormatException {
        Money m = new Money();
        m.amount = amount;
        m.currencyName = currencyName;
        return m;
    }

    @Override
    public boolean equals (Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Money money = (Money) o;

        if (!amount.equals(money.amount)) return false;
        return currencyName == money.currencyName;
    }

    @Override
    public int hashCode () {
        int result = amount.hashCode();
        result = 31 * result + currencyName.hashCode();
        return result;
    }

    @Override
    public String toString () {
        return currencyName.name() + " " + amount;
    }

    //region Getters
    public BigDecimal getAmount () {
        return this.amount;
    }

    public CurrencyName getCurrencyName () {
        return this.currencyName;
    }
    //endregion

    public enum CurrencyName { CAD, CHF, SEK, USD  }

}
