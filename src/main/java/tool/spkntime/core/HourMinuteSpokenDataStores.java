package tool.spkntime.core;

import java.util.HashMap;
import java.util.Map;

class HourMinuteSpokenDataStores {
	  

	//to be replaced by locales eventually
	final static Map<Integer,String> specialHoursChimes = new HashMap<>(2);
	static {
		specialHoursChimes.put(0, "midnight");
		specialHoursChimes.put(12, "noon");
		
	}
	
	final static Map<Integer,String> hourlyMap = new HashMap<>(12);
	static {
		//o'clock
		hourlyMap.put(1, "one");
		hourlyMap.put(2, "two");
		hourlyMap.put(3, "three");
		hourlyMap.put(4, "four");
		hourlyMap.put(5, "five");
		hourlyMap.put(6, "six");
		hourlyMap.put(7, "seven");
		hourlyMap.put(8, "eight");
		hourlyMap.put(9, "nine");
		hourlyMap.put(10, "ten");
		hourlyMap.put(11, "eleven");
		hourlyMap.put(12, "twelve");
	}
	
	
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
		
		static final Map<Integer,String> tenTwentiesThirtyFortyFiftyMap = new HashMap<>(5);
		static {
			
			tenTwentiesThirtyFortyFiftyMap.put(10, "ten");
			tenTwentiesThirtyFortyFiftyMap.put(20, "twenty");
			tenTwentiesThirtyFortyFiftyMap.put(30, "thirty");
			tenTwentiesThirtyFortyFiftyMap.put(40, "forty");
			tenTwentiesThirtyFortyFiftyMap.put(50, "fifty");
		}
		
		static final Map<Integer,String> specialMinTimes = new HashMap<>(3);
		static {
			
			specialMinTimes.put(15, "quarter");
			specialMinTimes.put(30, "half");
			specialMinTimes.put(45, "quarter");
		}
		
	  
  }
