package tool.spkntime.core;

import java.time.LocalTime;

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
		}else if(HalfForwardToHandler.isHalfForwardToTime(time)) {
			result = HalfForwardToHandler.handle_halfForwardToTimes(time);
		}
		
		System.out.println(result);
//		if(time.getMinute() % 30) {
//			
//		}
		return result;
	}
	
	class HourlyChimeHandler{
		
		
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
				spokenTime.append(HourMinuteSpokenDataStores.hourlychimeMap.get((Integer)time.getHour()));
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
					minutes = (HourMinuteSpokenDataStores.specialTimes.get(time.getMinute()));
				}else if(isHalfTime(time)) {
					minutes = (HourMinuteSpokenDataStores.specialTimes.get(time.getMinute()));
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
		private static boolean isHalfTime( LocalTime time ) {
			return time.getMinute() == 30 ;
		}
			
		private static boolean isHalfPastTime( LocalTime time ) {
			return time.getMinute() > 0 && time.getMinute() <= 30;
		}
		
		private static String  buildHour( LocalTime time ) {
			return HourMinuteSpokenDataStores.hourlychimeMap.get(time.getHour());
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
				minutes.append(HourMinuteSpokenDataStores.tensMap.get(min));
			} else if(min >= 11 && min <= 20) {
				minutes.append(HourMinuteSpokenDataStores.tenToTwentiesMap.get(min));
			} else if(min >=21 && min <= 30) {
				if( min == 30) {
					minutes.append(HourMinuteSpokenDataStores.tenTwentiesThirtyFortyFiftyMap.get(min));
				}else {
					int tensMin = min % 20;
					int tens = min - tensMin;
					minutes.append(HourMinuteSpokenDataStores.tenTwentiesThirtyFortyFiftyMap.get(tens)+ " ");
					minutes.append(HourMinuteSpokenDataStores.tensMap.get(tensMin) );
				}
			}else {
				//we shouldnt be here
			}
			return minutes.toString();
		}
		
	}
	//end-class
	
	
	class HalfForwardToHandler{
		
		static String handle_halfForwardToTimes( LocalTime time ) {
			StringBuilder halfpast = new StringBuilder("");
			String hour = buildHour(time);
			String minutes = "";
			if(isHalfForwardToTime(time) ) {
				
				if(isQuarterTime(time)) {
					minutes = (HourMinuteSpokenDataStores.specialTimes.get(time.getMinute()));
				}else {
					minutes = buildMinutes(time);
				}
			}
			halfpast.append(minutes);
			halfpast.append(" to " + hour);
			return halfpast.toString();
		}
		
		private static boolean isQuarterTime( LocalTime time ) {
			return time.getMinute() == 45;
		}
			
		private static boolean isHalfForwardToTime( LocalTime time ) {
			return time.getMinute() > 30;
		}
		
		private static String  buildHour( LocalTime time ) {
			return HourMinuteSpokenDataStores.hourlychimeMap.get(time.getHour() + 1);
		}
		
		/**
		 * we are suppose to handle 
		 * @param time
		 * @return
		 */
		private static String  buildMinutes( LocalTime time ) {
			StringBuilder minutes = new StringBuilder();
			final int min = 60 -  time.getMinute();
			if(min <= 10 ) {
				minutes.append(HourMinuteSpokenDataStores.tensMap.get(min));
			} else if(min >= 11 && min <= 20) {
				minutes.append(HourMinuteSpokenDataStores.tenToTwentiesMap.get(min));
			} else if(min >=21 && min <= 30) {
				if( min == 30) {
					minutes.append(HourMinuteSpokenDataStores.tenTwentiesThirtyFortyFiftyMap.get(min));
				}else {
					int tensMin = min % 20;
					int tens = min - tensMin;
					minutes.append(HourMinuteSpokenDataStores.tenTwentiesThirtyFortyFiftyMap.get(tens)+ " ");
					minutes.append(HourMinuteSpokenDataStores.tensMap.get(tensMin) );
				}
			}else {
				//we shouldnt be here
			}
			return minutes.toString();
		}
		
	}
	//end-class
	
	public static void main(String[] args) throws Exception {
		
		test_forwardTo_examples();
		
		test_halfpast_examples();
		
		test_hourly_chimes();
	}
	
	static void test_hourly_chimes() throws Exception {
		for(Integer key : HourMinuteSpokenDataStores.hourlychimeMap.keySet()) {
			SpokenTimeGenerator.speak(TimeValidator.validate((key )+":00"));
		}
	}
	
	static void test_halfpast_examples() throws Exception {
		String [] halfpastexamples = new String [] {"6:15","6:16","6:05","6:01","6:29","6:30"};
		for(String  exmaple :halfpastexamples) {
			SpokenTimeGenerator.speak(TimeValidator.validate(exmaple));
		}
	}
	
	static void test_forwardTo_examples() throws Exception {
		String [] halfforwardTo_examples = new String [] {"6:45","6:46","6:55","6:01","6:31","6:35"};
		for(String  exmaple :halfforwardTo_examples) {
			SpokenTimeGenerator.speak(TimeValidator.validate(exmaple));
		}
	}
	
	
}
