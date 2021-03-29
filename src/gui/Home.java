package gui;

import objects.*;

import javax.swing.*;

/**
 * <h1>Home</h1>
 * Interfaccia grafica funizonale, estensione della classe JHome.
 * Legge i dati dai file .json e inizializza gli oggetti relativi,
 * inserendo i valori all'interno dei campi e gli ascoltatori
 * dei bottoni.
 *
 * @author  Giulia Maraventano
 * @version 1.0
 * @since   2021-03-30
 */

public class Home extends JHome {

    public final Freelance freelance = new Freelance().getFreelance();
    public final Customer customer = new Customer();
    public final Task task = new Task();

    public Home() {

        nome.setText(freelance.getNome());
        professione.setText(freelance.getProfessione());
        indirizzo.setText(freelance.getIndirizzo());
        piva.setText(freelance.getPartitaIva());
        valore.setValue(freelance.getValore());
        customer.listaClienti().forEach(lista_clienti::addElement);
        task.listaTasks().forEach(lista_task::addElement);

        modifica_profilo.addActionListener(e -> {
            if (new ControllaCampi(piva.getText()).check()) {
                new Freelance.Builder()
                        .with($ -> {
                            $.nome = nome.getText();
                            $.professione = professione.getText();
                            $.indirizzo = indirizzo.getText();
                            $.partitaIva = piva.getText();
                            $.valore = Double.parseDouble(String.valueOf(valore.getValue()));
                        }).build().salvaFreelance();
                JOptionPane.showMessageDialog(null, "Freelance modificato");
            }
            else {
                JOptionPane.showMessageDialog(null, "Campo Codice Fiscale/Partita IVA non valido");
            }
        });
        nuovo_cliente.addActionListener(e -> {
            new CustomerForm("");
            this.setVisible(false);
            dispose();
        });
        modifica_cliente.addActionListener(e -> {
            if (clienti.getSelectedValue() != null) {
                new CustomerForm(clienti.getSelectedValue().toString());
                this.setVisible(false);
                dispose();
            }
            else {
                JOptionPane.showMessageDialog(null,"Non hai selezionato alcun Cliente");
            }
        });

        elimina_cliente.addActionListener(e -> {
            if (clienti.getSelectedValue() != null) {
                int input = JOptionPane.showConfirmDialog(null,"ATTENZIONE! Se elimini un cliente a cui hai associato un task, " +
                        "non potrai piu' svolgere il task! Sei sicuro?", "",JOptionPane.YES_NO_CANCEL_OPTION);
                if (input == 0) {
                    new Customer(clienti.getSelectedValue().toString()).deleteCustomer();
                    JOptionPane.showMessageDialog(null, "Cliente cancellato");
                    this.setVisible(false);
                    new Home();
                }
            }
            else {
                JOptionPane.showMessageDialog(null,"Non hai selezionato alcun Cliente");
            }
        });
        apri_task.addActionListener(e -> {
            if (tasks.getSelectedValue() != null) {
                new ClockTaskPanel(new Task(tasks.getSelectedValue().toString(),null).getTask());
                this.setVisible(false);
                dispose();
            }
            else {
                JOptionPane.showMessageDialog(null,"Non hai selezionato alcun Task");
            }
        });
        nuovo_task.addActionListener(e -> {
            new TaskForm();
            this.setVisible(false);
            dispose();
        });
        elimina_task.addActionListener(e -> {
            if (tasks.getSelectedValue() != null) {
                int input = JOptionPane.showConfirmDialog(null,"Sei sicuro?", "",JOptionPane.YES_NO_CANCEL_OPTION);
                if (input == 0) {
                    new Task(tasks.getSelectedValue().toString(),null).deleteTask();
                    JOptionPane.showMessageDialog(null,"Task cancellato");
                    this.setVisible(false);
                    new Home();
                }
            }
            else {
                JOptionPane.showMessageDialog(null,"Non hai selezionato alcun Task");
            }

        });

    }

}
