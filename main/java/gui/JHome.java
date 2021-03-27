package gui;

import javax.swing.*;
import java.awt.*;

/**
 * <h1>JHome</h1>
 * Interfaccia grafica grezza, estensione della classe JFrame,
 * contenente gli oggetti grafici statici, come campi di testo,
 * bottoni e labels.
 *
 * @author  Giulia Maraventano
 * @version 1.0
 * @since   2021-03-30
 */

public class JHome extends JFrame {

    protected final JLabel label_nome = new JLabel("Nome");
    protected final JLabel label_indirizzo = new JLabel("Indirizzo");
    protected final JLabel label_professione = new JLabel("Professione");
    protected final JLabel label_piva = new JLabel("P.IVA/Codice Fiscale");
    protected final JLabel label_valore = new JLabel("Valore Orario (Euro)");
    protected final JTextField nome = new JTextField();
    protected final JTextField indirizzo = new JTextField();
    protected final JTextField piva = new JTextField();
    protected final JTextField professione = new JTextField();
    protected final JSpinner valore = new JSpinner();
    protected final JButton modifica_profilo = new JButton("Modifica Profilo");
    protected final JButton nuovo_cliente = new JButton("Nuovo Cliente");
    protected final JButton modifica_cliente = new JButton("Apri Cliente");
    protected final JButton elimina_cliente = new JButton("Elimina Cliente");
    protected final JButton apri_task = new JButton("   Apri Task   ");
    protected final JButton elimina_task = new JButton("   Elimina Task   ");
    protected final JButton nuovo_task = new JButton("Nuovo Task");
    protected final DefaultListModel lista_task = new DefaultListModel();
    protected final JList tasks = new JList(lista_task);
    protected final DefaultListModel lista_clienti = new DefaultListModel();
    protected final JList clienti = new JList(lista_clienti);
    protected final JPanel pannello = new JPanel();

    public JHome() {

        super("ClockTask");
        this.setSize(700, 700);
        this.setLocationRelativeTo(null);
        Container container = this.getContentPane();
        GridLayout layout = new GridLayout(0,1);
        GridLayout layout2 = new GridLayout(0,2);
        pannello.setLayout(layout);
        JPanel freelance_panel = new JPanel();
        freelance_panel.setLayout(layout2);
        freelance_panel.setBackground(Color.ORANGE);
        freelance_panel.setBorder(BorderFactory.createTitledBorder("PROFILO FREELANCE"));
        freelance_panel.add(label_nome);
        freelance_panel.add(nome);
        freelance_panel.add(label_professione);
        freelance_panel.add(professione);
        freelance_panel.add(label_indirizzo);
        freelance_panel.add(indirizzo);
        freelance_panel.add(label_piva);
        freelance_panel.add(piva);
        freelance_panel.add(label_valore);
        freelance_panel.add(valore);
        freelance_panel.add(new JLabel());
        freelance_panel.add(modifica_profilo);
        JPanel customer_panel = new JPanel();
        customer_panel.setLayout(layout2);
        customer_panel.setBorder(BorderFactory.createTitledBorder("RUBRICA CLIENTI - Seleziona il cliente:"));
        customer_panel.setBackground(Color.ORANGE);
        clienti.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        clienti.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        clienti.setLayoutOrientation(JList.VERTICAL);
        clienti.setVisibleRowCount(-1);
        JPanel customer_buttons = new JPanel();
        customer_buttons.setLayout(layout);
        customer_buttons.add(modifica_cliente);
        customer_buttons.add(elimina_cliente);
        customer_panel.add(new JScrollPane(clienti));
        customer_panel.add(new JLabel("Aggiungi nuovo Cliente:"));
        customer_panel.add(customer_buttons);
        customer_panel.add(nuovo_cliente);
        JPanel task_panel = new JPanel();
        task_panel.setLayout(layout2);
        task_panel.setBorder(BorderFactory.createTitledBorder("TASKS - Seleziona un task:"));
        task_panel.setBackground(Color.ORANGE);
        tasks.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        tasks.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        tasks.setLayoutOrientation(JList.VERTICAL);
        tasks.setVisibleRowCount(-1);
        JPanel task_buttons = new JPanel();
        task_buttons.setLayout(layout);
        task_buttons.add(apri_task);
        task_buttons.add(elimina_task);
        task_panel.add(new JScrollPane(tasks));
        task_panel.add(new JLabel("Aggiungi nuovo Task:"));
        task_panel.add(task_buttons);
        task_panel.add(nuovo_task);
        pannello.add(freelance_panel);
        pannello.add(customer_panel);
        pannello.add(task_panel);
        modifica_profilo.setBackground(Color.LIGHT_GRAY);
        nuovo_cliente.setBackground(Color.DARK_GRAY);
        nuovo_cliente.setForeground(Color.WHITE);
        modifica_cliente.setBackground(Color.LIGHT_GRAY);
        elimina_cliente.setBackground(Color.LIGHT_GRAY);
        apri_task.setBackground(Color.LIGHT_GRAY);
        elimina_task.setBackground(Color.LIGHT_GRAY);
        nuovo_task.setBackground(Color.DARK_GRAY);
        nuovo_task.setForeground(Color.WHITE);
        container.add(pannello);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

    }
}
