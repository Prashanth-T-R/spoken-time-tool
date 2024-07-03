package tool.spkntime.core;

import java.time.LocalTime;

/**
 * purpose: The primary business logic handler leveraging @see HourMinuteSpokenDataStores to build and generate the British spoken time
 * Entrypoint: @see SpokenTimeGenerator.speak(LocalTime)
 */
public class SpokenTimeGenerator {
	
	/**
	 * This is the entry point to a small decision based time logic call.
	 * Callee is suppose to give a valid localtime
	 * @param time
	 * @return return the spoken time as per the a static-data-store @see HourMinuteSpokenDataStores
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
		
		 * @return a british spoken hourly chime time
		 */
		static String handle_hourlychime( LocalTime time ) {
			StringBuilder spokenTime = new StringBuilder("");
			if(is_zero_minute_hour(time)) {
				if(is_SpecialHours(time)) {
					spokenTime.append(HourMinuteSpokenDataStores.specialHoursChimes.get((Integer)time.getHour()));
				}else {
					spokenTime.append(HourMinuteSpokenDataStores.hourlyMap.get((Integer)time.getHour()));
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
		
		private static boolean is_SpecialHours( LocalTime time ) {
			return time.getHour() == 0 || time.getHour() == 12;
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
					minutes = (HourMinuteSpokenDataStores.specialMinTimes.get(time.getMinute()));
				}else if(isHalfTime(time)) {
					minutes = (HourMinuteSpokenDataStores.specialMinTimes.get(time.getMinute()));
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
			//TODO Need clarification to check if this is applicable in british-spoken saying 00:15 quarter past mignight
			//TODO Need clarification to check if this is applicable in british-spoken saying 00:45 quarter to one 
			//TODO Need clarification to check if this is applicable in british-spoken saying 12:15 quarter past twelve
			//TODO Need clarification to check if this is applicable in british-spoken saying 00:45 quarter to one (12 + 1) 
			if(HourlyChimeHandler.is_SpecialHours(time)) {
				return HourMinuteSpokenDataStores.specialHoursChimes.get((Integer)time.getHour());
			}
			return HourMinuteSpokenDataStores.hourlyMap.get(time.getHour());
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
					minutes = (HourMinuteSpokenDataStores.specialMinTimes.get(time.getMinute()));
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
			//scenario-1
			//TODO Need clarification to check if this is applicable in british-spoken saying 00:45 quarter to one
			
			//scenario-2 two posiblities
			//TODO Need clarification to check if this is applicable in british-spoken saying 12:45 quarter to one (12 + 1) 
			//TODO Need clarification to check if this is applicable in british-spoken saying 12:45 quarter to Midnight (12 + 1) 
			int hour = time.getHour();
			if(HourlyChimeHandler.is_SpecialHours(time)) {
				// when hour is 12 we could advance to 1 or zero, we will assume to advance 1
				hour = hour == 12 ? 0 : hour;
			}
			return HourMinuteSpokenDataStores.hourlyMap.get(hour + 1);
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
	
	private static void test_hourly_chimes() throws Exception {
		for(Integer key : HourMinuteSpokenDataStores.hourlyMap.keySet()) {
			SpokenTimeGenerator.speak(TimeValidator.validate((key )+":00"));
		}
	}
	
	private static void test_halfpast_examples() throws Exception {
		String [] halfpastexamples = new String [] {"6:15","6:16","6:05","6:01","6:29","6:30"};
		for(String  exmaple :halfpastexamples) {
			SpokenTimeGenerator.speak(TimeValidator.validate(exmaple));
		}
	}
	
	private static void test_forwardTo_examples() throws Exception {
		String [] halfforwardTo_examples = new String [] {"6:45","6:46","6:55","6:01","6:31","6:35"};
		for(String  exmaple :halfforwardTo_examples) {
			SpokenTimeGenerator.speak(TimeValidator.validate(exmaple));
		}
	}
	
	
}
