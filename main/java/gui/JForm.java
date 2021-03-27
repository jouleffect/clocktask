package gui;

import javax.swing.*;
import java.awt.*;

public class JForm extends JFrame {

    protected final JLabel label_nome = new JLabel("Nome Titolare");
    protected final JLabel label_ragionesociale = new JLabel("Ragione Sociale");
    protected final JLabel label_indirizzo = new JLabel("Indirizzo");
    protected final JLabel label_iva = new JLabel("P.IVA o Codice Fiscale");
    protected final JLabel label_telefono = new JLabel("Telefono");
    protected final JLabel label_cliente = new JLabel("Cliente: ");
    protected final JComboBox select_customer = new JComboBox();
    protected final JTextField text_nome = new JTextField(10);
    protected final JTextField text_ragionesociale = new JTextField(10);
    protected final JTextField text_indirizzo = new JTextField(10);
    protected final JTextField text_iva = new JTextField(10);
    protected final JTextField text_telefono = new JTextField(10);
    protected final JButton inserisci = new JButton("Inserisci");
    protected final JButton chiudi = new JButton("Indietro");
    protected final JPanel pannello = new JPanel();


    public JForm(int id_form) {

        Container c = this.getContentPane();
        pannello.setBackground(Color.ORANGE);
        pannello.add(label_nome);
        pannello.add(text_nome);

        if (id_form == 1) {
            this.setSize(400, 600);
            pannello.setLayout(new GridLayout(0,1));
            pannello.add(label_ragionesociale);
            pannello.add(text_ragionesociale);
            pannello.add(label_indirizzo);
            pannello.add(text_indirizzo);
            pannello.add(label_iva);
            pannello.add(text_iva);
            pannello.add(label_telefono);
            pannello.add(text_telefono);
        }
        else if (id_form == 2) {
            this.setSize(500, 200);
            pannello.setLayout(new GridLayout(0,2));
            pannello.add(label_nome);
            pannello.add(text_nome);
            pannello.add(label_cliente);
            pannello.add(select_customer);
        }

        inserisci.setBackground(Color.LIGHT_GRAY);
        chiudi.setBackground(Color.LIGHT_GRAY);
        pannello.add(inserisci);
        pannello.add(chiudi);
        c.add(pannello);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
