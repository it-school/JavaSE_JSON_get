package itschool;

import itschool.classes.Currency;
import itschool.classes.JSONGetter;
import itschool.classes.Rates;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Main
{

    public static void main(String[] args)
    {
        JSONGetter jsonGetter = new JSONGetter();
        JSONGetter.url = "https://api.privatbank.ua/p24api/pubinfo?json&exchange&coursid=5";
        jsonGetter.run();

        System.out.println("Waiting for data...");
        String jsonString = jsonGetter.jsonIn;
        System.out.println(jsonString);

        // Считываем json
        Object obj = null;
        try
        {
            obj = new JSONParser().parse(jsonString);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        System.out.println();

        JSONArray jsonArray = (JSONArray) obj;
        System.out.println(jsonArray.toJSONString());
        System.out.println();

        Rates rates = new Rates();

        for (Object jsonObject : jsonArray)
        {
            JSONObject current = (JSONObject) jsonObject;
            String ccy = (String) current.get("ccy");
            String base_ccy = (String) current.get("base_ccy");
            double buy = Double.parseDouble((String) current.get("buy"));
            double sale = Double.parseDouble((String) current.get("sale"));
            Currency currency = new Currency(ccy, base_ccy, buy, sale);
            rates.add(currency);
        }

        System.out.println("Imported data after parsing:\n" + rates);
        System.out.println(rates);

        rates.getRates().sort(Currency.byNameAsc);
        System.out.println("After sorting by name ascending:\n" + rates);
        rates.getRates().sort(Currency.byNameDesc);
        System.out.println("After sorting by name descending:\n" + rates);

        rates.getRates().sort(Currency.byValueAsc);
        System.out.println("After sorting by Buy value ascending:\n" + rates);
        rates.getRates().sort(Currency.byValueDesc);
        System.out.println("After sorting by Buy value descending:\n" + rates);

        Rates withUR = rates.filterByCCY("UR");
        System.out.println("Filtered data with 'UR' in title:" + withUR);

        Rates withUSD = rates.filterByCCY("USD");
        System.out.println("Filtered data with 'UR' in title:" + withUSD);
    }
}