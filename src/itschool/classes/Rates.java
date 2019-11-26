package itschool.classes;

import java.util.ArrayList;
import java.util.Date;

public class Rates {
    public ArrayList<Currency> getRates() {
        return rates;
    }

    public Rates(ArrayList<Currency> rates) {
        this.rates = rates;
    }

    public void setRates(ArrayList<Currency> rates) {
        this.rates = rates;
    }

    private ArrayList<Currency> rates;

    public Rates() {
        rates = new ArrayList<>();
    }

    public void add(Currency currency) {
        this.rates.add(currency);
    }

    @Override
    public String toString() {
        String result = "Rates (" + (new Date()).toLocaleString () + ") "+System.lineSeparator();
        for (Currency c : rates) {
            result += c + System.lineSeparator();
        }
        return result;
    }

    public Rates filterByCCY(String ccy)
    {
        Rates tempRats = new Rates();
        for (Currency currency : this.rates)
        {
            if (currency.getCcy().toLowerCase().contains(ccy.toLowerCase()))
                tempRats.add(currency);
        }
        return tempRats;
    }
}
