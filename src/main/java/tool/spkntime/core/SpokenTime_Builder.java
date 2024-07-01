package tool.spkntime.core;

import java.util.Locale;

public class SpokenTime_Builder {

	Locale spokenLocale;
	
	private SpokenTime_Builder() {
		spokenLocale = Locale.UK;
	}
	private SpokenTime_Builder(Locale locale) {
		spokenLocale = locale;
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
	
	
	public static void main(String[] args) {
		System.out.println(SpokenTime_Builder.build().spokenLocale.getCountry());
		System.out.println(SpokenTime_Builder.build().spokenLocale.getDisplayLanguage());
		System.out.println(SpokenTime_Builder.build().spokenLocale);
		
	}
	
}

