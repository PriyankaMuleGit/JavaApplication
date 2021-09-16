import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {

	public boolean isValid(String s) {
		Pattern p = Pattern.compile("^\\d{10}$");
		Matcher m = p.matcher(s);
		return (m.matches());
	}

	public boolean checkCuisine(String s) {
		if (s.equalsIgnoreCase("Indian") || s.equalsIgnoreCase("Chinese") || s.equalsIgnoreCase("Italian")
				|| s.equalsIgnoreCase("Mexican")) {
			return true;
		} else
			return false;
	}

	public boolean isValidTime(String time) {
		String regex = "([01]?[0-9]|2[0-3]):[0-5][0-9]";
		Pattern p = Pattern.compile(regex);
		if (time == null) {
			return false;
		}
		Matcher m = p.matcher(time);
		return m.matches();
	}
}
