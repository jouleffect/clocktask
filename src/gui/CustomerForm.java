package gui;

import fileIO.FileJson;
import fileIO.JsonAdd;
import fileIO.JsonReplace;
import objects.Customer;
import org.json.simple.JSONObject;

import javax.swing.*;

/**
 * <h1>CustomerForm</h1>
 * Classe che implementa tutti i comandi per le operazioni
 * sui file .json
 *
 * @author  Giulia Maraventano
 * @version 1.0
 * @since   2021-03-30
 */
public class CustomerForm extends JForm {

    public CustomerForm(String nome_cliente) {

        super(1);

        if (!nome_cliente.equals("")) {
            Customer customer = new Customer(nome_cliente).getCustomer();
            text_nome.setText(customer.getNome());
            text_ragionesociale.setText(customer.getRagioneSociale());
            text_indirizzo.setText(customer.getIndirizzo());
            text_iva.setText(customer.getPartitaIva());
            text_telefono.setText(customer.getTelefono());
        }

        inserisci.addActionListener(e-> {
            if (new ControllaCampi(text_iva.getText()).check()) {
                JSONObject customer = new JSONObject();
                JSONObject customerDetails = new JSONObject();
                customerDetails.put("Nome", text_nome.getText());
                customerDetails.put("RagioneSociale", text_ragionesociale.getText());
                customerDetails.put("Indirizzo", text_indirizzo.getText());
                customerDetails.put("Partita_IVA", text_iva.getText());
                customerDetails.put("Telefono", text_telefono.getText());
                customer.put("Cliente",customerDetails);
                if (!nome_cliente.equals("")) {
                    new JsonReplace(new FileJson("clienti.json", customer,"Cliente",nome_cliente)).execute();
                } else {
                    new JsonAdd(new FileJson("clienti.json", customer,"Cliente",nome_cliente)).execute();
                }
                JOptionPane.showMessageDialog(null, "Cliente Inserito");
                new Home();
                this.setVisible(false);
                dispose();
            }
            else {
                JOptionPane.showMessageDialog(null, "Campo Codice Fiscale/Partita IVA non valido");
            }

        });
        chiudi.addActionListener(e-> { new Home();this.setVisible(false);dispose();});
    }

}
