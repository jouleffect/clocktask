package objects;

import fileIO.FileJson;
import fileIO.JsonIO;
import fileIO.JsonRead;
import fileIO.JsonSave;
import org.json.simple.JSONObject;
import java.util.function.Consumer;

/**
 * <h1>Freelance</h1>
 * Classe dell'oggetto Freelance, contenente gli attributi e
 * i metodi per creare e salvare l'oggetto.
 *
 * @author  Giulia Maraventano
 * @version 1.0
 * @since   2021-03-30
 */

public class Freelance {

    private String nome;
    private String professione;
    private String indirizzo;
    private String partitaIva;
    private double valore;


    private Freelance(String nome, String professione, String indirizzo, String partitaIva, double valore) {
        this.nome = nome;
        this.professione = professione;
        this.indirizzo = indirizzo;
        this.partitaIva = partitaIva;
        this.valore = valore;
    }

    public Freelance() {

    }

    /**
     * Questo &egrave; il metodo per salvare il Freelance in un file .json.
     * @return Nothing.
     */
    public void salvaFreelance() {
        JSONObject freelance = new JSONObject();
        freelance.put("Nome", nome);
        freelance.put("Professione", professione);
        freelance.put("Indirizzo", indirizzo);
        freelance.put("Partita_IVA", partitaIva);
        freelance.put("Valore", valore);
        new JsonSave(new FileJson("freelance.json",freelance,"",nome)).execute();
    }

    /**
     * Questo &egrave; il metodo per leggere il Freelance da un file .json.
     * @return Freelance.
     */
    public Freelance getFreelance() {
        JsonIO file_freelance = new JsonRead(new FileJson("freelance.json"));
        JSONObject freelance = file_freelance.execute();
        return new Freelance.Builder()
                .with($ -> {
                    $.nome = freelance.get("Nome").toString();
                    $.professione = freelance.get("Professione").toString();
                    $.indirizzo = freelance.get("Indirizzo").toString();
                    $.partitaIva = freelance.get("Partita_IVA").toString();
                    $.valore = (Double) freelance.get("Valore");
                }).build();
    }

    public String getNome() { return nome; }
    public String getProfessione() {
        return professione;
    }
    public String getIndirizzo() {
        return indirizzo;
    }
    public String getPartitaIva() {
        return partitaIva;
    }
    public double getValore() { return valore; }

    /**
     * <h1>Freelance.Builder</h1>
     * Classe per la costruzione dell'oggetto Freelance.
     */
    public static class Builder {

        public String nome, professione, indirizzo;
        public String partitaIva;
        public double valore;

        public Freelance.Builder with(Consumer<Freelance.Builder> builder) {
            builder.accept(this);
            return this;
        }
        /**
         * Questo &egrave; il metodo per restituire l'oggetto Freelance.
         * @return Freelance.
         */
        public Freelance build() {
            return new Freelance(nome, professione, indirizzo, partitaIva, valore);
        }

    }
}
