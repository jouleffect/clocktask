package filePDF;

import gui.MyTimer;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDField;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.function.Consumer;

/**
 * <h1>FatturaPDF</h1>
 * Classe che implementa l'interfaccia PDF, per il calcolo della fattura e la creazione
 * del file PDF con i valori del risultato.
 *
 * @author  Giulia Maraventano
 * @version 1.0
 * @since   2021-03-30
 */

public class FatturaPDF implements Fattura {

    private final String nome_task, professionista, cliente;
    private final Double importo;
    private final Double iva;
    private final Double totale;

    /**
     * Questo &egrave; il costruttore che calcola il valore dell'importo totale della fattura
     * in base al valore orario del Freelance e al tempo impiegato per lo svolgimento
     * del task.
     * @return Nothing.
     */
    public FatturaPDF(String nome_task, Double valore, MyTimer timer, String professionista, String cliente) {

        this.nome_task = nome_task;
        this.professionista = professionista;
        this.cliente = cliente;

        if (timer.ore != 0) {
            importo = valore * timer.ore + valore * timer.minuti / 60;
        }
        else {
            importo = valore * timer.minuti / 60;
        }
        iva = importo * 22 / 100;
        totale = importo + iva;
    }

    /**
     * Questo &egrave; il metodo che serve a popolare i campi del file PDF, con i valori
     * calcolati nel costruttore.
     * @return Nothing.
     */
    public void create() {

        DecimalFormat centesimi = new DecimalFormat("#.##");
        String dateStamp = new SimpleDateFormat("dd/MM/yyyy").format(new Date())+"\n";
        try {
            PDDocument pDDocument = PDDocument.load(new File("template_fattura.pdf"));
            PDAcroForm pDAcroForm = pDDocument.getDocumentCatalog().getAcroForm();
            PDField field = pDAcroForm.getField("area_professionista");
            field.setValue(professionista);
            field = pDAcroForm.getField("area_cliente");
            field.setValue("Spett.le "+cliente);
            field = pDAcroForm.getField("nome_task");
            field.setValue(nome_task);
            field = pDAcroForm.getField("importo");
            field.setValue(centesimi.format(importo)+" Euro");
            field = pDAcroForm.getField("iva");
            field.setValue(centesimi.format(iva)+ " Euro");
            field = pDAcroForm.getField("totale");
            field.setValue(centesimi.format(totale)+" Euro");
            field = pDAcroForm.getField("data");
            field.setValue("data:  "+dateStamp);
            pDDocument.save("fattura.pdf");
            pDDocument.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    @Override
    public void open() {
        try {

            File pdfFile = new File("fattura.pdf");
            if (pdfFile.exists()) {

                if (Desktop.isDesktopSupported()) {
                    Desktop.getDesktop().open(pdfFile);
                } else {
                    System.out.println("Desktop non supportato!");
                }

            } else {
                System.out.println("Il file non esiste!");
            }

            System.out.println("Fatto");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public static class Builder {

        public String nome_task, professionista, cliente;
        public MyTimer timer;
        public Double valore;

        public FatturaPDF.Builder with(Consumer<FatturaPDF.Builder> builder) {
            builder.accept(this);
            return this;
        }
        /**
         * Questo &egrave; il metodo per restituire l'oggetto Customer.
         * @return Customer.
         */
        public FatturaPDF build() {
            return new FatturaPDF(nome_task,valore,timer, professionista, cliente);
        }
    }
}

