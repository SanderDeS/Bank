package bank;


public class Zichtrekening extends Rekening {
    public Zichtrekening(String rekeninghouder, String nummer) {
        super(rekeninghouder, nummer);
        setIntrest(0.1);
    }

    @Override
    public void print() {
        System.out.println("Zichtrekening nummer: " + this.getNummer());
        System.out.println("Rekeninghouder: " + this.getRekeninghouder());
        System.out.println("Saldo: € " + this.getSaldo());
        System.out.println("Jaarlijkse intrestvoet: " + getIntrest() + "%");
        System.out.println("");
    }


}
