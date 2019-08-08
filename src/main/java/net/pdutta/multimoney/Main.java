package net.pdutta.multimoney;

public class Main {
    public static void main (String[] args) {

        CurrencyExchange cx = CurrencyExchange.builder()
                .withName("Bob's FX Shop")
                .withRate(Money.CurrencyName.USD, Money.CurrencyName.SEK, "9.58")
                .withRate(Money.CurrencyName.SEK, Money.CurrencyName.USD, "0.08")
                .withRate(Money.CurrencyName.USD, Money.CurrencyName.CAD, "1.30")
                .withRate(Money.CurrencyName.CAD, Money.CurrencyName.USD, "0.74")
                .build();

        System.out.println(cx);

        Money swedenTripCashUsd = Money.build("100", Money.CurrencyName.USD);
        Money swedenTripCashSek = cx.convert(swedenTripCashUsd, Money.CurrencyName.SEK);

        Money changeFromCanadaTripCad = Money.build("87.40", Money.CurrencyName.CAD);
        Money changeFromCanadaTripUsd = cx.convert(changeFromCanadaTripCad, Money.CurrencyName.USD);

        System.out.println("Sweden Trip Cash: " + swedenTripCashSek);
        System.out.println("Change from Canada Trip: " + changeFromCanadaTripUsd);
    }
}