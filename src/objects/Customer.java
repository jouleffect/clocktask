package objects;

import fileIO.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * <h1>Freelance</h1>
 * Classe dell'oggetto Customer, contenente gli attributi e
 * i metodi per creare e salvare l'oggetto.
 *
 * @author  Giulia Maraventano
 * @version 1.0
 * @since   2021-03-30
 */

public class Customer {

    private String nome, ragioneSociale, indirizzo, partitaIva, telefono;

    public Customer(String nome, String ragioneSociale, String indirizzo, String partitaIva,String telefono) {
        this.nome = nome;
        this.ragioneSociale = ragioneSociale;
        this.indirizzo = indirizzo;
        this.partitaIva = partitaIva;
        this.telefono = telefono;
    }

    public Customer(String nome) {
        this.nome = nome;
    }

    public Customer() {}

    /**
     * Questo metodo serve leggere i dati del cliente dal file .json
     * conoscendone il nome.
     * @return Customer cliente.
     */
    public Customer getCustomer() {
        JSONArray file_customer = new JsonReadObject(new FileJson("clienti.json")).readObject();
        JSONObject customer = (JSONObject) file_customer.stream()
                .map(o -> ((JSONObject) o).get("Cliente"))
                .filter(o ->
                        ((JSONObject) o).get("Nome").toString().matches(this.nome))
                .findFirst()
                .get();
        return new Customer.Builder()
                .with($ -> {
                    $.nome = customer.get("Nome").toString();
                    $.ragioneSociale = customer.get("RagioneSociale").toString();
                    $.indirizzo = customer.get("Indirizzo").toString();
                    $.partitaIva = customer.get("Partita_IVA").toString();
                    $.telefono = (String) customer.get("Telefono");
                }).build();
    }

    /**
     * Questo &egrave; il metodo per eliminare un cliente dal file .json.
     * @return Nothing.
     */
    public void deleteCustomer() {
        new JsonDeleteObject(new FileJson("clienti.json", null, "Cliente",this.getNome())).execute();
    }

    /**
     * Questo &egrave; il metodo per leggere la lista dei nomi dei clienti dal file .json.
     * @return List.
     */
    public List<String> listaClienti() {
        JSONArray file_customer = new JsonReadObject(new FileJson("clienti.json")).readObject();
        return (List<String>) file_customer.stream()
                .filter(t -> !((JSONObject) t).get("Cliente").toString().isEmpty())
                .map(t -> ((JSONObject) t).get("Cliente"))
                .map(t -> ((JSONObject) t).get("Nome"))
                .collect(Collectors.toList());
    }

    public String getNome() {
        return nome;
    }
    public String getRagioneSociale() {
        return ragioneSociale;
    }
    public String getIndirizzo() {
        return indirizzo;
    }
    public String getPartitaIva() {
        return partitaIva;
    }
    public String getTelefono() {
        return telefono;
    }

    /**
     * <h1>Customer.Builder</h1>
     * Classe per la costruzione dell'oggetto Customer.
     */
    public static class Builder {

        public String nome, ragioneSociale, indirizzo, partitaIva, telefono;

        public Customer.Builder with(Consumer<Customer.Builder> builder) {
            builder.accept(this);
            return this;
        }
        /**
         * Questo &egrave; il metodo per restituire l'oggetto Customer.
         * @return Customer.
         */
        public Customer build() {
            return new Customer(nome, ragioneSociale, indirizzo, partitaIva,telefono);
        }
    }

}
