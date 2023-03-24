package utils;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.FileReader;

/**
 * JSONFileParserUtil
 * @author sudosingh
 *
 */
public class JSONFileParser {
	
	private JsonObject json;
	
	/**
	 * 1-arg constructor toget JSON from a file
	 * @param filePath
	 */
	public JSONFileParser(String filePath) {
		try {
			FileReader fileReader = new FileReader(filePath);
			this.json = JsonParser.parseReader(fileReader).getAsJsonObject();
			fileReader.close();
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * Get a particular Key from the JsonObject
	 * @param key The key whose value we need to get
	 * @return
	 */
	public JsonObject getAKey(String key) {
        return json.get(key.replaceAll("\"", "")).getAsJsonObject();
    }
}
