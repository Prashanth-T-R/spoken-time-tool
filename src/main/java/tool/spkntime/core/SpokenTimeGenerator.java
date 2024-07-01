package tool.spkntime.core;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 */
public class SpokenTimeGenerator {
	
	/**
	 * 
	 * @param time
	 * @return
	 */
	
	static String speak(LocalTime time){
		String result = "";
		if(HourlyChimeHandler.is_zero_minute_hour(time)) {
			result = HourlyChimeHandler.handle_hourlychime(time);
		}else if(HalfPastHandler.isHalfPastTime(time)) {
			result = HalfPastHandler.handle_halfpastTimes(time);
		}
		
		System.out.println(result);
//		if(time.getMinute() % 30) {
//			
//		}
		return result;
	}
	
	class HourlyChimeHandler{
		
		//to be replaced by locales eventually
		final static Map<Integer,String> hourlychimeMap = new HashMap<>(12);
		static {
			//specials
			hourlychimeMap.put(0, "midnight");
			hourlychimeMap.put(12, "noon");
			
			//o'clock
			hourlychimeMap.put(1, "one");
			hourlychimeMap.put(2, "two");
			hourlychimeMap.put(3, "three");
			hourlychimeMap.put(4, "four");
			hourlychimeMap.put(5, "five");
			hourlychimeMap.put(6, "six");
			hourlychimeMap.put(7, "seven");
			hourlychimeMap.put(8, "eight");
			hourlychimeMap.put(9, "nine");
			hourlychimeMap.put(10, "ten");
			hourlychimeMap.put(11, "eleven");
		}
		
		
		/**
		 * ### buc-1-hourly chime variants
	- buc-1.1 
		1:00 one o'clock
		2:00 two o'clock
		.
		.
		.
		11:00 eleven o'clock
		
		buc-1.2
			00:00 midnight
		buc-1.3
			12:00 noon
		buc-1.4
		 * @return
		 */
		static String handle_hourlychime( LocalTime time ) {
			StringBuilder spokenTime = new StringBuilder("");
			if(is_zero_minute_hour(time)) {
				spokenTime.append(hourlychimeMap.get((Integer)time.getHour()));
				if(is_oclock_hour(time)) {
					spokenTime.append(" " + "o'clock")  ;
				}
			}
			return spokenTime.toString();
		}
		
		private static boolean is_zero_minute_hour( LocalTime time ) {
			return time !=null && time.getMinute() == 0;
		}
		
		private static boolean is_oclock_hour( LocalTime time ) {
			//inclusive 1 to 11
			return time.getHour() >= 1 && time.getHour() <=11;
		}
		
	}
	
	
	class HalfPastHandler{
		static final Map<Integer,String> tensMap = new HashMap<>(10);
		static {
			//o'clock
			tensMap.put(1, "one");
			tensMap.put(2, "two");
			tensMap.put(3, "three");
			tensMap.put(4, "four");
			tensMap.put(5, "five");
			tensMap.put(6, "six");
			tensMap.put(7, "seven");
			tensMap.put(8, "eight");
			tensMap.put(9, "nine");
			tensMap.put(10, "ten");
					
		}
		
		static final Map<Integer,String> tenToTwentiesMap = new HashMap<>(10);
		static {
			tenToTwentiesMap.put(11, "eleven");
			tenToTwentiesMap.put(12, "twelve");
			
			tenToTwentiesMap.put(13, "thirteen");
			tenToTwentiesMap.put(14, "fourteen");
			tenToTwentiesMap.put(15, "fifteen");
			tenToTwentiesMap.put(16, "sixteen");
			tenToTwentiesMap.put(17, "seventeen");
			tenToTwentiesMap.put(18, "eighteen");
			tenToTwentiesMap.put(19, "nineteen");
			
			tenToTwentiesMap.put(20, "twenty");
		}
		
		static final Map<Integer,String> tenTwentiesThirtyFortyFiftyMap = new HashMap<>(10);
		static {
			
			tenTwentiesThirtyFortyFiftyMap.put(10, "ten");
			tenTwentiesThirtyFortyFiftyMap.put(20, "twenty");
			tenTwentiesThirtyFortyFiftyMap.put(30, "thirty");
			tenTwentiesThirtyFortyFiftyMap.put(40, "forty");
			tenTwentiesThirtyFortyFiftyMap.put(50, "fifty");
		}
		
		static final Map<Integer,String> specialTimes = new HashMap<>(10);
		static {
			
			specialTimes.put(15, "quarter");
			specialTimes.put(45, "quarter");
		}
		
		/**
		 * https://en.wikipedia.org/wiki/Date_and_time_notation_in_the_United_Kingdom
		 * 
		 * @return
		 */
		static String handle_halfpastTimes( LocalTime time ) {
			StringBuilder halfpast = new StringBuilder("");
			String hour = buildHour(time);
			String minutes = "";
			if(isHalfPastTime(time) ) {
				
				if(isQuarterTime(time)) {
					minutes = (specialTimes.get(time.getMinute()));
				}else {
					minutes = buildMinutes(time);
				}
			}
			halfpast.append(minutes);
			halfpast.append(" past " + hour);
			return halfpast.toString();
		}
		
		private static boolean isQuarterTime( LocalTime time ) {
			return time.getMinute() == 15 || time.getMinute() == 45;
		}
			
		private static boolean isHalfPastTime( LocalTime time ) {
			return time.getMinute() > 0 && time.getMinute() <= 30;
		}
		
		private static String  buildHour( LocalTime time ) {
			return HourlyChimeHandler.hourlychimeMap.get(time.getHour());
		}
		
		/**
		 * we are suppose to handle 
		 * @param time
		 * @return
		 */
		private static String  buildMinutes( LocalTime time ) {
			StringBuilder minutes = new StringBuilder();
			final int min = time.getMinute();
			if(min <= 10 ) {
				minutes.append(tensMap.get(min));
			} else if(min >= 11 && min <= 20) {
				minutes.append(tenToTwentiesMap.get(min));
			} else if(min >=21 && min <= 30) {
				if( min == 30) {
					minutes.append(tenTwentiesThirtyFortyFiftyMap.get(min));
				}else {
					int tensMin = min % 20;
					int tens = min - tensMin;
					minutes.append(tenTwentiesThirtyFortyFiftyMap.get(tens)+ " ");
					minutes.append(tensMap.get(tensMin) );
				}
			}else {
				//we shouldnt be here
			}
			return minutes.toString();
		}
		
	}
	//end-class
	
	
	public static void main(String[] args) throws Exception {
		test_halfpast_examples();
		
		
	}
	
	static void test_hourly_chimes() throws Exception {
		for(Integer key : HourlyChimeHandler.hourlychimeMap.keySet()) {
			SpokenTimeGenerator.speak(TimeValidator.validate((key )+":00"));
		}
	}
	
	static void test_halfpast_examples() throws Exception {
		String [] halfpastexamples = new String [] {"6:15","6:16","6:05","6:01","6:29","6:30"};
		for(String  exmaple :halfpastexamples) {
			SpokenTimeGenerator.speak(TimeValidator.validate(exmaple));
		}
	}
	
}
