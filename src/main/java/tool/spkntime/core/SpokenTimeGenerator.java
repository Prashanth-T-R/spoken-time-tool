package tool.spkntime.core;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 */
public class SpokenTimeGenerator {
	
	//to be replaced by locales eventually
	final static Map<Integer,String> hourlychimeMap = new HashMap<>(12);
	static {
		hourlychimeMap.put(0, "midnight");
		hourlychimeMap.put(12, "noon");
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
	 * 
	 * @param time
	 * @return
	 */
	
	static String speak(LocalTime time){
		String result = handle_hourlychime(time);
		System.out.println(result);
//		if(time.getMinute() % 30) {
//			
//		}
		return result;
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
		System.out.println(time);
		//System.out.println(hourlychimeMap.keySet());
		StringBuilder spokenTime = new StringBuilder("");
		if(time !=null && time.getMinute() == 0) {
			spokenTime.append(hourlychimeMap.get((Integer)time.getHour()));
			if(time.getHour() >= 1 && time.getHour() <=11) {
				spokenTime.append(" " + "o'clock")  ;
			}
		}
		return spokenTime.toString();
	}
	
	public static void main(String[] args) throws Exception {
		
		for(Integer key : hourlychimeMap.keySet()) {
			SpokenTimeGenerator.speak(TimeValidator.validate((key == 0 ?  ("0"+key):key )+":00"));
		}
		
	}
}
