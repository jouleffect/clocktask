package fileIO;
import org.json.simple.JSONObject;

/**
 * <h1>JsonDelete</h1>
 * Classe che implementa l'interfaccia JsonIO,
 * per effettuare l'operazione di cancellazione
 * di un file .json
 *
 * @author  Giulia Maraventano
 * @version 1.0
 * @since   2021-03-30
 */

public class JsonDelete implements JsonIO {

    private final FileJson fileJson;

    public JsonDelete(FileJson fileJson) {
        this.fileJson = fileJson;
    }

    @Override
    public JSONObject execute() {
        fileJson.delete();
        return null;
    }
}
