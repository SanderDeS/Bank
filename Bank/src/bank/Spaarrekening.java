package bank;


public class Spaarrekening extends Rekening {
    public Spaarrekening(String rekeninghouder, String nummer) {
        super(rekeninghouder, nummer);
        setIntrest(1.5);
    }

    @Override
    public void print() {
        System.out.println("Spaarrekening nummer: " + this.getNummer());
        System.out.println("Rekeninghouder: " + this.getRekeninghouder());
        System.out.println("Saldo: € " + this.getSaldo());
        System.out.println("Jaarlijkse intrestvoet: " + getIntrest() + "%");
        System.out.println("");
    }
}
