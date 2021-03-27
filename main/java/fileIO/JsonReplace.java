package fileIO;

import org.json.simple.JSONObject;

/**
 * <h1>JsonReplace</h1>
 * Classe che implementa l'interfaccia JsonJO,
 * per effettuare l'operazione di sostituzione di
 * un elemento di un array di un file .json
 *
 * @author  Giulia Maraventano
 * @version 1.0
 * @since   2021-03-30
 */

public class JsonReplace implements JsonIO {

    private final FileJson fileJson;

    public JsonReplace(FileJson fileJson) {
        this.fileJson = fileJson;
    }

    @Override
    public JSONObject execute() {
        fileJson.replace();
        return null;
    }
}
