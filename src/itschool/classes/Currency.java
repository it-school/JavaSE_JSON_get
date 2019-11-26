package itschool.classes;


import java.util.Comparator;

public class Currency {

    private String ccy;
    private String baseCcy;
    private double buy;
    private double sale;

    /**
     * @param sale
     * @param baseCcy
     * @param buy
     * @param ccy
     */
    public Currency(String ccy, String baseCcy, double buy, double sale) {
        super();
        this.ccy = ccy;
        this.baseCcy = baseCcy;
        this.buy = buy;
        this.sale = sale;
    }

    public String getCcy() {
        return ccy;
    }

    public void setCcy(String ccy) {
        this.ccy = ccy;
    }

    public String getBaseCcy() {
        return baseCcy;
    }

    public void setBaseCcy(String baseCcy) {
        this.baseCcy = baseCcy;
    }

    public double getBuy() {
        return buy;
    }

    public void setBuy(double buy) {
        this.buy = buy;
    }

    public double getSale() {
        return sale;
    }

    public void setSale(double sale) {
        this.sale = sale;
    }

    @Override
    public String toString() {
        return ccy + " -> " + baseCcy + " [" + buy + ", " + sale + "]";
    }

    public static Comparator<Currency> byNameAsc = (o1, o2) -> o1.ccy.compareTo(o2.ccy);
    public static Comparator<Currency> byNameDesc = (o1, o2) -> o2.ccy.compareTo(o1.ccy);
    public static Comparator<Currency> byValueAsc = (o1, o2) -> o1.buy > o2.buy ? 1 : o1.buy < o2.buy ? -1 : 0;
    public static Comparator<Currency> byValueDesc = (o1, o2) -> o1.buy < o2.buy ? 1 : o1.buy > o2.buy ? -1 : 0;


}