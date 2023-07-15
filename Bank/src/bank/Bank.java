package bank;

import java.util.ArrayList;
import java.util.List;


public class Bank {

    ArrayList<Rekening> lijst;

    public Bank() {
        lijst = new ArrayList<>();
    }

    public ArrayList<Rekening> getRekeningen() {
        return lijst;
    }

    public void addRekening(Rekening rekening) {
        if (!rekeningNummerBestaatAl(rekening.getNummer())) {
            lijst.add(rekening);
            System.out.println("Rekening " + rekening.getNummer() + " toegevoegd.");
        } else {
            System.out.println("Rekening " + rekening.getNummer() + " bestaat al.");
        }
    }

    public boolean rekeningNummerBestaatAl(String reknr) {
        for (Rekening r : lijst) {
            if (reknr.equalsIgnoreCase(r.getNummer())) {
                return true;
            }
        }
        return false;
    }

    public double getTotaalSaldo() {
        double som = 0;
        for(Rekening rekening : lijst) {
            som += rekening.getSaldo();
        }
        return som;
    }

    public void print() {
        System.out.println("Aantal rekeningen: " + getRekeningen().size());
        for (Rekening rekening : lijst) {
            rekening.print();
        }
        System.out.println("Totaal saldo: € " + getTotaalSaldo());
    }

    public void doeOverschrijving(Rekening r1, Rekening r2, double bedrag) {
        if (r1 != null && r2 != null) {
            if (r1 instanceof Spaarrekening && !r1.getRekeninghouder().equals(r2.getRekeninghouder())) {
                System.out.println("Overschrijven van een spaarrekening kan alleen naar een rekening van jezelf.");
            } else {
                System.out.println("Overschrijving van " + r1.getNummer() + " naar " + r2.getNummer() + " bedrag: € " + bedrag);
                if (r1.haalAf(bedrag)) {
                    r2.doeStorting(bedrag);
                } else {
                    System.out.println("Saldo ontoereikend.");
                }
            }
        } else {
            System.out.println("Mislukt!");
        }
    }

    public Rekening getRekening(String nummer) {
        for(Rekening rekening : lijst) {
            if (rekening.getNummer().equalsIgnoreCase(nummer)) {
                return rekening;
            }
        }
        return null;
    }

    public void doeEindejaarsverrichtingen() {
        for (Rekening rekening : lijst) {
            rekening.eindeJaar();
        }
    }
}
