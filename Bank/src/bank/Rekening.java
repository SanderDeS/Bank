package bank;

public abstract class Rekening {

    private double saldo;
    private String rekeninghouder;
    private String nummer;
    private double intrest;

    //constructor
    public Rekening(String rekeninghouder, String nummer) {
        this.rekeninghouder = rekeninghouder;
        this.nummer = nummer;
        intrest = 0;
    }

    //getters
    public double getSaldo() {
        return saldo;
    }

    public String getRekeninghouder() {
        return rekeninghouder;
    }

    public String getNummer() {
        return nummer;
    }

    public double getIntrest() {
        return intrest;
    }

    public void setIntrest(double intrest) {
        this.intrest = intrest;
    }

    //methodes
    public abstract void print();

    public double berekenIntrest() {
        return saldo * intrest / 100;
    }

    public void eindeJaar() {
        System.out.println("Eindejaar: €" + berekenIntrest() + " erbij op rekening " + getNummer());
        saldo += berekenIntrest();
    }

    public boolean haalAf(double bedrag) {
        if (bedrag <= 0) {
            System.out.println("Je kan geen negatief bedrag afhalen.");
            return false;
        }
        if (saldo >= bedrag) {
            System.out.println("€ " + bedrag + " afgehaald van " + getNummer());
            saldo -= bedrag;
            return true;
        } else {
            System.out.println("Saldo ontoereikend.");
        }
        return false;
    }

    public void doeStorting(double bedrag) {
        if (bedrag > 0) {
            System.out.println("€ " + bedrag + " gestort op " + getNummer());
            saldo += bedrag;
        }
    }

    public String getSummary() {
        return getNummer();
    }
}