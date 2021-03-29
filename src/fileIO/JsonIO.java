package fileIO;
import org.json.simple.JSONObject;
/**
 * Questa &egrave; un'interfaccia per le operazioni di input/output su file .json
 */
@FunctionalInterface
public interface JsonIO {
    JSONObject execute();
}
