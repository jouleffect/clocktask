package fileIO;

import org.json.simple.JSONObject;

/**
 * <h1>JsonDeleteObject</h1>
 * Classe che implementa l'interfaccia JsonJO,
 * per effettuare l'operazione di cancellazione di un
 * oggetto di un file .json
 *
 * @author  Giulia Maraventano
 * @version 1.0
 * @since   2021-03-30
 */

public class JsonDeleteObject implements JsonIO {

    private final FileJson fileJson;

    public JsonDeleteObject(FileJson fileJson) {
        this.fileJson = fileJson;
    }

    @Override
    public JSONObject execute() {
        fileJson.deleteObject();
        return null;
    }
}
