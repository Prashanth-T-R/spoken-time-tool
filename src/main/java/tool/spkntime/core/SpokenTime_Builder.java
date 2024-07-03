package tool.spkntime.core;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SpokenTime_Builder {

	Locale spokenLocale;
	private static final Logger log = LoggerFactory.getLogger(SpokenTime_Builder.class);
	
	private SpokenTime_Builder() {
		spokenLocale = Locale.UK;
		log.info("default locale set to British " + spokenLocale.getDisplayCountry());
	}
	private SpokenTime_Builder(Locale locale) {
		spokenLocale = locale;
		log.info("locale set  " + spokenLocale.getDisplayCountry());
	}

	public static SpokenTime_Builder build() {
		return new SpokenTime_Builder();
	}

	public static SpokenTime_Builder build(Locale locale) {
		return new SpokenTime_Builder(locale);
	}
	
	public Locale locale() {
		return spokenLocale;
	}
	
	
	public String spokenLocale() {
		return locale().getDisplayCountry();
	}
	
	public String spokenTime(String timeInput) {
		String spokenTime = "";
		try {
			LocalTime time = TimeValidator.validate(timeInput);
			spokenTime = SpokenTimeGenerator.speak(time);
		} catch (Exception e) {
			log.error(e.getMessage());
			spokenTime = e.getMessage();
		}
		return spokenTime;
	}
	
	
	public static void main(String[] args) {
		SpokenTime_Builder.build().spokenTime("00:58");
		//playLocalTime();
	}
	
	static void playLocalTime() {
		
		System.out.println(SpokenTime_Builder.build().spokenLocale.getCountry());
		System.out.println(SpokenTime_Builder.build().spokenLocale.getDisplayLanguage());
		System.out.println(SpokenTime_Builder.build().spokenLocale);
		
		
		
		System.out.println(LocalTime.now());
		
		System.out.println(LocalTime.MAX);
		System.out.println(LocalTime.MIDNIGHT);
		System.out.println(LocalTime.NOON);
		System.out.println(LocalTime.parse("00:61", DateTimeFormatter.ISO_LOCAL_TIME));
	}
}

