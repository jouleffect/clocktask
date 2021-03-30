package gui;

import filePDF.*;
import objects.Customer;
import objects.Freelance;
import objects.Task;
import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;

/**
 * <h1>ClockTaskPanel</h1>
 * Classe dell'interfaccia del ClockTask, che estende la classe JTask,
 * ed implementa la parte funzionale, inserendo le azioni del timer,
 * i valori ai campi e gli action listener sui bottoni.
 *
 * @author  Giulia Maraventano
 * @version 1.0
 * @since   2021-03-30
 */

public class ClockTaskPanel extends JTaskPanel {

    public ClockTaskPanel(Task task) {

        super();

        String timeStamp = new SimpleDateFormat("dd/MM/yyyy HH:mm").format(new Date());
        double valore = new Freelance().getFreelance().getValore();

        String tempo_impiegato = null;
        try {
            tempo_impiegato = new BufferedReader(new FileReader("tasks/"+task.getNome_task()+"_timer.txt")).readLine();
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        nometask.setText(task.getNome_task());
        nomecliente.setText(task.getCustomer());
        label_valore.setText("Valore orario: "+valore+" €");
        label_tempo.setText("Tempo impiegato fin ora: "+tempo_impiegato);


        DecimalFormat centesimi = new DecimalFormat("#.##");

        if (tempo_impiegato != null) {
            myTimer.ore = Integer.parseInt(tempo_impiegato.split(":")[0]);
            myTimer.minuti =  Integer.parseInt(tempo_impiegato.split(":")[1]);
            myTimer.secondi =  Integer.parseInt(tempo_impiegato.split(":")[2]);
            label_prezzo.setText("Costo attuale del task: "+(centesimi.format(valore * myTimer.ore + valore * myTimer.minuti / 60))+" € + IVA");
        }

        modifica.addActionListener(e -> {
            System.out.println(task.getNome_task());
            new Task(task.getNome_task(),nomecliente.getText()).saveTask(nometask.getText());
            JOptionPane.showMessageDialog(null, "Task modificato");
        });

        myTimer.stop.addActionListener(e -> {
            NumberFormat formatter = new DecimalFormat("00");
            String tempo = formatter.format(myTimer.ore)+":"+formatter.format(myTimer.minuti)+":"+formatter.format(myTimer.secondi);
            try {
                Files.write(Paths.get("tasks/"+task.getNome_task()+"_timer.txt"), Collections.singleton(tempo));
            } catch (IOException exception) {
                exception.printStackTrace();
            }
            label_tempo.setText("Ultimo tempo impiegato: "+tempo);
        });

        salva.addActionListener(e -> {
            try {
                if (!note.getText().isEmpty()) {
                    String log = "NOTE (" + timeStamp + ")\n" + note.getText() + "\n________________________________\n";
                    FileWriter f = new FileWriter("tasks/" + task.getNome_task() + "_log.txt", true);
                    f.append(log);
                    f.close();
                }

            } catch (Exception exception) {
                exception.printStackTrace();
            }

            label_prezzo.setText("Costo attuale del task: "+(centesimi.format(valore * myTimer.ore + valore * myTimer.minuti / 60))+" € + IVA");
            note.setText("");
        });
        storico.addActionListener(e -> {
            Note note = new NotePDF("tasks/"+task.getNome_task()+"_log.txt");
            note.create();
            note.open();
        });

        fattura.addActionListener(e -> {
            Freelance freelance = new Freelance().getFreelance();
            Customer customer = new Customer(task.getCustomer()).getCustomer();
            String professionista = freelance.getNome()+"\n"+freelance.getIndirizzo()+"\n"+"P.IVA/CF: "+freelance.getPartitaIva();
            String cliente = customer.getRagioneSociale()+"\n"+customer.getIndirizzo()+"\n"+"P.IVA/CF: "+customer.getPartitaIva();

            Fattura fattura = new FatturaPDF.Builder().with($ -> {
                $.nome_task = task.getNome_task();
                $.valore = freelance.getValore();
                $.timer = myTimer;
                $.professionista = professionista;
                $.cliente = cliente;
            }).build();
            fattura.create();
            fattura.open();
        });
        indietro.addActionListener(e -> {
            if (myTimer.timer.isRunning()) {
                JOptionPane.showMessageDialog(null,"Il tempo sta scorrendo");
            }
            else {
                new Home();
                this.setVisible(false);
                dispose();
            }
        });
    }
}
