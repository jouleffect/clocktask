package gui;

import fileIO.FileJson;
import fileIO.JsonReadObject;
import objects.Customer;
import objects.Task;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import javax.swing.*;
import java.util.Objects;

/**
 * <h1>TaskForm</h1>
 * Classe di interfaccia per il form di compilazione dei campi del task
 *
 * @author  Giulia Maraventano
 * @version 1.0
 * @since   2021-03-30
 */
public class TaskForm extends JForm {

    public TaskForm() {
        super(2);

        new Customer().listaClienti().forEach(select_customer::addItem);

        inserisci.addActionListener(e-> {
            JSONArray file_customer = new JsonReadObject(new FileJson("clienti.json")).readObject();
            JSONObject customer = (JSONObject) file_customer.stream()
                    .map(o -> ((JSONObject) o).get("Cliente"))
                    .filter(o ->
                            ((JSONObject) o).get("Nome").toString().matches(Objects.requireNonNull(select_customer.getSelectedItem()).toString()))
                    .findFirst()
                    .get();
            new Task(text_nome.getText(),customer.get("Nome").toString()).saveTask(text_nome.getText());
            JOptionPane.showMessageDialog(null, "Task Inserito");
            new Home();
            this.setVisible(false);
            dispose();
        });

        chiudi.addActionListener(e-> { new Home();this.setVisible(false);dispose();});
    }
}
