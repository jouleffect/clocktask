package fileIO;

import org.json.simple.JSONObject;

/**
 * <h1>JsonSave</h1>
 * Classe che implementa l'interfaccia JsonJO,
 * per effettuare l'operazione di salvataggio
 * di un oggetto un file .json
 *
 * @author  Giulia Maraventano
 * @version 1.0
 * @since   2021-03-30
 */

public class JsonSave implements JsonIO {

    private final FileJson fileJson;

    public JsonSave(FileJson fileJson) {
        this.fileJson = fileJson;
    }

    @Override
    public JSONObject execute() {
        fileJson.save();
        return null;
    }
}
