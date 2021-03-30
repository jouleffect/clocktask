package fileIO;

import org.json.simple.JSONObject;
/**
 * <h1>JsonRead</h1>
 * Classe che implementa l'interfaccia JsonJO,
 * per effettuare l'operazione di lettura
 * del contenuto di un file .json
 *
 * @author  Giulia Maraventano
 * @version 1.0
 * @since   2021-03-30
 */
public class JsonRead implements JsonIO{

    private final FileJson fileJson;

    public JsonRead(FileJson fileJson) {
        this.fileJson = fileJson;
    }
    @Override
    public JSONObject execute() {
        return fileJson.read();
    }
}
