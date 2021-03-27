package gui;

import javax.swing.*;
import java.awt.*;

/**
 * <h1>JTask</h1>
 * Interfaccia grafica grezza, estensione della classe JFrame,
 * contenente gli oggetti grafici statici, come campi di testo,
 * bottoni e labels e un oggetto Timer di tipo JPanel.
 *
 * @author  Giulia Maraventano
 * @version 1.0
 * @since   2021-03-30
 */

public class JTaskPanel extends JFrame {

    protected final JLabel label_task = new JLabel("Nome Task: ");
    protected final JTextField nometask = new JTextField();
    protected final JLabel label_cliente = new JLabel("Cliente: ");
    protected final JLabel label_valore = new JLabel();
    protected final JLabel label_tempo = new JLabel();
    protected final JLabel label_prezzo = new JLabel();
    protected final JTextField nomecliente = new JTextField();
    protected final JTextArea note = new JTextArea();
    protected final JButton modifica = new JButton("Modifica");
    protected final JButton salva = new JButton("Salva");
    protected final JButton storico = new JButton("Storico Note");
    protected final JButton fattura = new JButton("Genera Fattura");
    protected final JButton indietro = new JButton("Indietro");
    protected final MyTimer myTimer = new MyTimer();
    protected final JPanel pannello = new JPanel();


    public JTaskPanel(String nome) {

        this.setSize(800, 800);
        this.setLocationRelativeTo(null);
        nomecliente.setEditable(false);
        label_prezzo.setForeground(Color.RED);
        Container c = this.getContentPane();
        GridLayout layout = new GridLayout(0,2);
        GridLayout layout2 = new GridLayout(0,1);
        pannello.setLayout(layout);
        JPanel task_panel = new JPanel();
        task_panel.setLayout(layout2);
        task_panel.setBorder(BorderFactory.createTitledBorder("Task"));
        task_panel.setBackground(Color.ORANGE);
        task_panel.add(label_task);
        task_panel.add(nometask);
        task_panel.add(label_cliente);
        task_panel.add(nomecliente);
        task_panel.add(modifica);
        task_panel.add(label_valore);
        task_panel.add(label_tempo);
        task_panel.add(label_prezzo);
        JPanel note_panel = new JPanel();
        note_panel.setLayout(new BorderLayout());
        note_panel.setBorder(BorderFactory.createTitledBorder("Note - Inserisci una nota"));
        note.setText("");
        note_panel.add(note);
        JPanel footer_panel = new JPanel();
        footer_panel.setLayout(layout);
        modifica.setBackground(Color.LIGHT_GRAY);
        salva.setBackground(Color.CYAN);
        footer_panel.add(salva);
        storico.setBackground(Color.yellow);
        footer_panel.add(storico);
        fattura.setBackground(Color.green);
        footer_panel.add(fattura);
        indietro.setBackground(Color.LIGHT_GRAY);
        footer_panel.add(indietro);
        pannello.add(task_panel);
        pannello.add(myTimer);
        pannello.add(note_panel);
        pannello.add(footer_panel);
        c.add(pannello);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
