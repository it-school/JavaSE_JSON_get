package itschool;

import itschool.classes.Currency;
import itschool.classes.JSONGetter;
import itschool.classes.Rates;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Main {

    public static void main(String[] args) {
        JSONGetter jsonGetter = new JSONGetter();
        JSONGetter.url = "https://api.privatbank.ua/p24api/pubinfo?json&exchange&coursid=5";
        jsonGetter.run();

        System.out.println("Waining for data...");
        String jsonString = jsonGetter.jsonIn;
        System.out.println(jsonString);

        // Считываем json
        Object obj = null; // Object obj = new JSONParser().parse(new FileReader("JSONExample.json"));
        try {
            obj = new JSONParser().parse(jsonString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println();
// Кастим obj в JSONObject
        JSONArray jsonArray = (JSONArray) obj;
        System.out.println(jsonArray.toJSONString());
        System.out.println();

        Rates rates = new Rates();

        for (Object jsonObject : jsonArray) {
            //System.out.println(jsonObject);
            JSONObject current = (JSONObject) jsonObject;
            String ccy = (String) current.get("ccy");
            String base_ccy = (String) current.get("base_ccy");
            double buy = Double.parseDouble((String) current.get("buy"));
            double sale = Double.parseDouble((String) current.get("sale"));
            Currency currency = new Currency(ccy, base_ccy, buy, sale);
            rates.add(currency);
        }

        System.out.println(rates);
        rates.getRates().sort(Currency.byNameAsc);
        System.out.println(rates);
        rates.getRates().sort(Currency.byNameDesc);
        System.out.println(rates);

        rates.getRates().sort(Currency.byValueAsc);
        System.out.println(rates);
        rates.getRates().sort(Currency.byValueDesc);
        System.out.println(rates);

        Rates withUR = rates.filterByCCY("UR");
        System.out.println("with UR:" + withUR);

        Rates withUSD = rates.filterByCCY("USD");
        System.out.println("with USD:" + withUSD);

  /*
// Достаём firstName and lastName
        String firstName = (String) jo.get("ccy");
        String lastName = (String) jo.get("baseCcy");
        System.out.println("fio: " + firstName + " " + lastName);

// Достаем массив номеров
        JSONArray phoneNumbersArr = (JSONArray) jo.get("phoneNumbers");
        Iterator phonesItr = phoneNumbersArr.iterator();
        System.out.println("phoneNumbers:");
// Выводим в цикле данные массива
        while (phonesItr.hasNext()) {
            JSONObject test = (JSONObject) phonesItr.next();
            System.out.println("- type: " + test.get("type") + ", phone: " + test.get("number"));
        }
*/
    }
}
