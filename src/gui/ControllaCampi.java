package gui;
/**
 * <h1>ControllaCampi</h1>
 * Classe per controllare la correttezza dei valori inseriti nei campi di testo.
 *
 * @author  Giulia Maraventano
 * @version 1.0
 * @since   2021-03-30
 */
public class ControllaCampi {

    private final String codice;

    public ControllaCampi(String codice) {
        this.codice = codice;
    }

    /**
     * Questo metodo serve a controllare la correttezza dei campi Codice Fiscale e Valore orario.
     * @return true se i campi sono corretti.
     */
    public Boolean check() {
        Boolean match_codice = codice.matches("[a-zA-Z]{6}\\d\\d[a-zA-Z]\\d\\d[a-zA-Z]\\d\\d\\d[a-zA-Z]")
                || codice.matches("0[0-9]{10}") || codice.matches("");
        System.out.println(match_codice);
        return match_codice;
    }
}
