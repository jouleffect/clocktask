package fileIO;

import org.json.simple.JSONObject;

/**
 * <h1>JsonAdd</h1>
 * Classe che implementa l'interfaccia JsonIO,
 * per effettuare l'operazione di aggiunta di un array
 * in un file .json
 *
 * @author  Giulia Maraventano
 * @version 1.0
 * @since   2021-03-30
 */
public class JsonAdd implements JsonIO {

    private final FileJson fileJson;

    public JsonAdd(FileJson fileJson) {
        this.fileJson = fileJson;
    }

    @Override
    public JSONObject execute() {
        fileJson.add();
        return null;
    }
}
