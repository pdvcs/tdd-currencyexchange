package test;

import net.pdutta.currencyexchange.CurrencyExchange;
import net.pdutta.currencyexchange.Money;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CurrencyExchangeTest {

    @Mock
    CurrencyExchange mockCx;

    @Test
    void currencyExchange () {
        when(mockCx.getName()).thenReturn("A Test FX Shop");
        when(mockCx.convert(Money.build("100", Money.CurrencyName.USD), Money.CurrencyName.SEK))
                .thenReturn(Money.build("958", Money.CurrencyName.SEK));

        assertEquals("A Test FX Shop", mockCx.getName());
        assertEquals(Money.build("958", Money.CurrencyName.SEK),
                mockCx.convert(Money.build("100", Money.CurrencyName.USD), Money.CurrencyName.SEK));
    }
}
