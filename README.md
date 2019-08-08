# TDD Currency Exchange

This is based on the
[Multi-Currency Money](https://books.google.co.uk/books?id=CUlsAQAAQBAJ&lpg=PP1&dq=tdd%20by%20example&pg=PA3#v=onepage&q&f=false)
Example in the *TDD by Example* Book.

Our requirements are:

* It should be possible for one or more currency exchanges to be set up,
  each offering a defined set of exchange rates.
* Customers need to convert money from one currency to another

Our first stab at a basic conversion test is:

```java
class MoneyTest {
    
    @Test
    void basicCurrencyExchange () {
        Money usd = Money.build("12.65", Money.CurrencyName.USD);
        Money chf = Money.build("6.30", Money.CurrencyName.CHF);
        
        cx = CurrencyExchange.builder()
                .withName("Swiss Holidays Currency Exchange")
                .withRate(Money.CurrencyName.USD, Money.CurrencyName.CHF, "0.97")
                .withRate(Money.CurrencyName.CHF, Money.CurrencyName.USD, "1.01")
                .build();
        
        Money usdToChf = cx.convert(usd, Money.CurrencyName.CHF);
        Money chfToUsd = cx.convert(chf, Money.CurrencyName.USD);

        // we expect the money to be rounded properly to 2 decimals
        assertEquals(Money.build("12.27", Money.CurrencyName.CHF), usdToChf);
        assertEquals(Money.build("6.36", Money.CurrencyName.USD), chfToUsd);
    }
}

```
## Compiling and Running

`mvn package` and `mvn exec:java`
