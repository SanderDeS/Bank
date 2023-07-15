package bank;

import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.Collections;


public class Controller {

    public TextField txtNaam;
    public TextField txtRekNr;
    public ComboBox<String> cboTypeRekening;
    public ComboBox<String> cboRekening;
    public ComboBox<String> cboTypeTransactie;
    public TextField txtBedrag1;
    public ComboBox<String> cboBron;
    public ComboBox<String> cboDoel;
    public TextField txtBedrag2;

    private Bank bank;

    //constructor
    public Controller() {
        bank = new Bank();
    }

    public void initialize() {
        cboTypeRekening.getItems().add("Zichtrekening");
        cboTypeRekening.getItems().add("Spaarrekening");
        cboTypeRekening.getSelectionModel().selectFirst();

        updateRekeningComboBoxes();

        cboTypeTransactie.getItems().add("Storten");
        cboTypeTransactie.getItems().add("Afhalen");
        cboTypeTransactie.getSelectionModel().selectFirst();

    }

    private void updateRekeningComboBoxes() {
        updateRekeningComboBox(cboRekening);
        updateRekeningComboBox(cboBron);
        updateRekeningComboBox(cboDoel);
    }

    private void updateRekeningComboBox(ComboBox cbo) {
        cbo.getItems().clear();
        for(Rekening rekening : bank.getRekeningen()) {
            cbo.getItems().add(rekening.getSummary());
        }

        Collections.sort(cbo.getItems());

        if (bank.getRekeningen().size() > 0) {
            cbo.getSelectionModel().selectFirst();
        }
    }

    public void voegRekeningToe(ActionEvent actionEvent) {
        String naam = txtNaam.getText().trim();
        String reknr = txtRekNr.getText().trim();

        if (naam.length() == 0) {
            System.out.println("Ongeldige naam.");
        } else if (reknr.length() == 0) {
            System.out.println("Ongeldig rekeningnummer");
        } else {
            String type = cboTypeRekening.getSelectionModel().getSelectedItem();
            Rekening r;
            if (type.equals("Zichtrekening")) {
                r = new Zichtrekening(naam, reknr);
            } else {
                r = new Spaarrekening(naam, reknr);
            }
            bank.addRekening(r);
            updateRekeningComboBoxes();
        }
    }

    public void voerUit(ActionEvent actionEvent) {

        if (bank.getRekeningen().size() == 0) {
            System.out.println("Geen rekening geselecteerd.");
        } else {
            String reknr = cboRekening.getSelectionModel().getSelectedItem();
            String bedrag = txtBedrag1.getText().trim();
            double bedrag2 = Double.parseDouble(bedrag);
            String type = cboTypeTransactie.getSelectionModel().getSelectedItem();

            Rekening r = bank.getRekening(reknr);

            if (type.equalsIgnoreCase("storten")) {
                r.doeStorting(bedrag2);
            } else {
                r.haalAf(bedrag2);
            }
        }
        txtBedrag1.clear();
        updateRekeningComboBoxes();
    }

    public void doeOverschrijving(ActionEvent actionEvent) {

        if (bank.getRekeningen().size() == 0) {
            System.out.println("Geen rekening geselecteerd.");
        } else {
            Rekening r1;
            Rekening r2;

            r1 = bank.getRekening(cboBron.getSelectionModel().getSelectedItem());
            r2 = bank.getRekening(cboDoel.getSelectionModel().getSelectedItem());

            if (r1 == null || r2 == null || txtBedrag2.getText().trim().length() == 0) {
                System.out.println("Fout bij overschrijven.");
            } else {
                double bedrag = Double.parseDouble(txtBedrag2.getText().trim());
                bank.doeOverschrijving(r1, r2, bedrag);
                updateRekeningComboBoxes();
            }
        }
    }

    public void drukAlleRekeningenAf(ActionEvent actionEvent) {
        bank.print();
    }

    public void doeEindejaarsVerrichtingen(ActionEvent actionEvent) {
        bank.doeEindejaarsverrichtingen();
    }
}