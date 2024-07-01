package tool.spkntime.core;

import java.util.HashMap;
import java.util.Map;

class HourMinuteSpokenDataStores {
	  

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
		
	  
  }
