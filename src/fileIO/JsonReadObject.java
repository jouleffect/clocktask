package fileIO;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * <h1>JsonReadObject</h1>
 * Classe che implementa l'interfaccia JsonJO,
 * per effettuare l'operazione di lettura di
 * un oggetto contenuto in un file .json
 *
 * @author  Giulia Maraventano
 * @version 1.0
 * @since   2021-03-30
 */

public class JsonReadObject implements JsonIO {

    private final FileJson fileJson;

    public JsonReadObject(FileJson fileJson) {
        this.fileJson = fileJson;
    }

    public JSONArray readObject() {
        return fileJson.readObject();
    }

    @Override
    public JSONObject execute() {
        return null;
    }
}
