package filePDF;

import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * <h1>NotePDF</h1>
 * Classe che implementa l'interfaccia Note, per la visualizzazione
 * delle note in un file PDF.
 *
 * @author  Giulia Maraventano
 * @version 1.0
 * @since   2021-03-30
 */
public class NotePDF implements Note{

    private final String nome_file;

    public NotePDF(String nome_file) {
        this.nome_file = nome_file;
    }

    /**
     * Questo &egrave; il metodo che serve a popolare i campi del file PDF, con il
     * testo contenuto nel file delle note
     * @return Nothing.
     */
    @Override
    public void create() {
        try {
            new BufferedReader(new FileReader(nome_file));
        } catch (FileNotFoundException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Questo &egrave; il metodo che serve ad aprire un file PDF per visualizzarne il contenuto
     * @return Nothing.
     */
    @Override
    public void open() {
        try {

            File pdfFile = new File(nome_file);
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
}
