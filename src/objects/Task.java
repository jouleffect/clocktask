package objects;

import fileIO.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <h1>Task</h1>
 * Classe dell'oggetto Task, contenente gli attributi e
 * i metodi per creare e salvare l'oggetto.
 *
 * @author  Giulia Maraventano
 * @version 1.0
 * @since   2021-03-30
 */

public class Task {

    private String nome_task;
    private String customer;

    public Task(String nome_task, String customer) {
        this.nome_task = nome_task;
        this.customer = customer;
    }

    public Task() {}

    /**
     * Questo metodo serve leggere i dati del task dal file .json
     * conoscendo il nome.
     * @return Task.
     */
    public Task getTask() {
        JSONObject file_task = new JsonRead(new FileJson("tasks/"+nome_task+".json")).execute();
        JSONArray file_customer = new JsonReadObject(new FileJson("clienti.json")).readObject();
        if (file_customer.stream().map(o -> ((JSONObject) o).get("Cliente"))
                .anyMatch(o ->
                        ((JSONObject) o).get("Nome").toString().matches(file_task.get("Cliente").toString()))) {
            JSONObject task = (JSONObject) file_customer.stream()
                    .map(o -> ((JSONObject) o).get("Cliente"))
                    .filter(o ->
                            ((JSONObject) o).get("Nome").toString().matches(file_task.get("Cliente").toString()))
                    .findFirst()
                    .get();
            this.customer = task.get("Nome").toString();
        }
        else {
            this.customer = "";
        }
        return this;
    }

    /**
     * Questo &egrave; il metodo per salvare il Task in un file .json.
     * @return Nothing.
     */
    public void saveTask(String nome) {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Nome",nome_task);
        jsonObject.put("Cliente",customer);
        if (nome_task.equals(nome)) {
            new JsonSave(new FileJson("tasks/"+nome_task+".json", jsonObject,"Nome",nome_task)).execute();
            try {
                FileWriter note = new FileWriter("tasks/"+nome_task+"_log.txt");
                FileWriter timer = new FileWriter("tasks/"+nome_task+"_timer.txt");
                timer.write("00:00:00");
                timer.close();
                note.close();
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        else {
            this.deleteTask();
            File note = new File("tasks/"+nome_task+"_log.txt");
            File timer = new File("tasks/"+nome_task+"_timer.txt");
            note.renameTo(new File("tasks/" + nome + "_log.txt"));
            timer.renameTo(new File("tasks/" + nome + "_timer.txt"));
            new JsonSave(new FileJson("tasks/"+nome+".json", jsonObject,"Nome",nome)).execute();
        }

    }
    /**
     * Questo &egrave; il metodo per eliminare un Task in un file .json.
     * @return Nothing.
     */
    public void deleteTask() {
        new JsonDelete(new FileJson("tasks/"+this.getNome_task()+".json")).execute();
    }

    /**
     * Questo &egrave; il metodo per leggere la lista dei nomi dei Tasks dal file .json.
     * @return List.
     */
    public List<String> listaTasks() {
        List<String> lista = null;
        try {
            lista =  Files.walk(Paths.get("tasks"))
                    .filter(extension -> extension.toString().endsWith(".json"))
                    .map(Path::getFileName)
                    .map(path -> path.getFileName().toString().substring(0, path.getFileName().toString().lastIndexOf('.')))
                    .collect(Collectors.toList());
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return lista;
    }

    public String getNome_task() {
        return nome_task;
    }
    public String getCustomer() {
        return customer;
    }
}
