package fileIO;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * <h1>FileJson</h1>
 * Classe che implementa tutti i comandi per le operazioni
 * sui file .json
 *
 * @author  Giulia Maraventano
 * @version 1.0
 * @since   2021-03-30
 */

public class FileJson{

    private final String nome_file;
    private String tag;
    private String target;
    private JSONObject object;

    public FileJson(String nome_file) {
        this.nome_file = nome_file;
    }

    public FileJson(String nome_file, JSONObject object, String tag, String target) {
        this.nome_file = nome_file;
        this.object = object;
        this.tag = tag;
        this.target = target;
    }
    /**
     * Questo &egrave; il metodo che serve per salvare un oggetto in un file .json.
     * @return Nothing.
     */
    public void save() {
        try {
            Files.write(Paths.get(this.nome_file), this.object.toJSONString().getBytes());
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
    /**
     * Questo &egrave; il metodo che serve per cancellare un oggetto in un file .json.
     * @return Nothing.
     */
    public void delete() {
        File file = new File(nome_file);
        file.delete();
    }
    /**
     * Questo &egrave; il metodo che serve per leggere un oggetto in un file .json.
     * @return JSONObject.
     */
    public JSONObject read(){
        JSONParser parser = new JSONParser();
        Object obj = null;
        try {
            obj = parser.parse(new FileReader(this.nome_file));
        } catch (IOException | ParseException exception) {
            exception.printStackTrace();
        }
        return (JSONObject) obj;
    }
    /**
     * Questo &egrave; il metodo che serve per aggiungere un oggetto in un file .json.
     * @return JSONObject.
     */
    public void add() {
        JSONParser jsonParser = new JSONParser();
        try {
            Object obj = jsonParser.parse(new FileReader(this.nome_file));
            JSONArray jsonArray = (JSONArray)obj;
            jsonArray.add(this.object);
            FileWriter file = new FileWriter(this.nome_file);
            file.write(jsonArray.toJSONString());
            file.flush();
            file.close();

        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Questo &egrave; il metodo che serve per sostituire un oggetto in un file .json.
     * @return JSONObject.
     */
    public void replace() {
        JSONParser jsonParser = new JSONParser();
        try {
            Object obj = jsonParser.parse(new FileReader(this.nome_file));
            JSONArray jsonArray = (JSONArray)obj;
            jsonArray.stream().filter(o -> ((JSONObject) o).get(this.tag).toString().contains(this.target))
                    .forEach(o1 -> ((JSONObject) o1).clear());
            jsonArray.add(this.object);
            FileWriter file = new FileWriter(this.nome_file);
            file.write(jsonArray.toJSONString().replaceAll("\\{}","").replaceAll(",\\{","\\{"));
            file.flush();
            file.close();
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Questo &egrave; il metodo che serve per cancellare l'oggetto di un array in un file .json.
     * @return JSONObject.
     */
    public void deleteObject()  {
        JSONParser jsonParser = new JSONParser();
        try {
            Object obj = jsonParser.parse(new FileReader(this.nome_file));
            JSONArray jsonArray = (JSONArray)obj;
            jsonArray.stream().filter(o -> ((JSONObject) o).get(this.tag).toString().contains(this.target))
                    .forEach(o1 -> ((JSONObject) o1).clear());
            FileWriter file = new FileWriter(this.nome_file);
            file.write(jsonArray.toJSONString().replaceAll(",\\{}","").replaceAll("\\{}",""));
            file.flush();
            file.close();
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Questo &egrave; il metodo che serve per leggere l'oggetto di un array in un file .json.
     * @return JSONObject.
     */
    public JSONArray readObject() {
        JSONParser parser = new JSONParser();
        Object obj = null;
        try {
            obj = parser.parse(new FileReader(this.nome_file));
        } catch (IOException | ParseException exception) {
            exception.printStackTrace();
        }
        return (JSONArray) obj;
    }
}
