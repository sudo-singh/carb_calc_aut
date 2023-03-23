package utils;

public class ThisResources {
	public static final String BASE_URL;
	public static final String CARBOHYDRATE_CALCULATOR;
	public static final String DIR_PATH;
	public static final String INVALID_AGE_ERROR;
	
	static {
		try {
			PropertyUtil.getInstance().load("config.properties");
			BASE_URL = PropertyUtil.getInstance().getValue("BASE_URL");
			CARBOHYDRATE_CALCULATOR = PropertyUtil.getInstance().getValue("CARBOHYDRATE_CALCULATOR");
			DIR_PATH = PropertyUtil.getInstance().getValue("DIR_PATH");
			INVALID_AGE_ERROR = PropertyUtil.getInstance().getValue("INVALID_AGE_ERROR");
		} catch (Throwable e) {
			e.printStackTrace();
			throw new RuntimeException("Check Properties File", e);
		}
	}
}
