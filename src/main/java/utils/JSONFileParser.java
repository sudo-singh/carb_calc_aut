package utils;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.FileReader;


public class JSONFileParser {
	
	private JsonObject json;
	
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
	
	public JsonObject getAKey(String key) {
        return json.get(key.replaceAll("\"", "")).getAsJsonObject();
    }
}
