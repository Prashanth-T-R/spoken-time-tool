package tool.spkntime.core;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class TimeValidator {
	
	private static final Logger log = LoggerFactory.getLogger(TimeValidator.class);
	
    /**
     
    
 *   <tr><th scope="row">a</th>       <td>am-pm-of-day</td>                <td>text</td>              <td>PM</td>
 *   <tr><th scope="row">B</th>       <td>period-of-day</td>               <td>text</td>              <td>in the morning</td>
 *   <tr><th scope="row">h</th>       <td>clock-hour-of-am-pm (1-12)</td>  <td>number</td>            <td>12</td>
 *   <tr><th scope="row">K</th>       <td>hour-of-am-pm (0-11)</td>        <td>number</td>            <td>0</td>
 *   <tr><th scope="row">k</th>       <td>clock-hour-of-day (1-24)</td>    <td>number</td>            <td>24</td>
 *
 *   <tr><th scope="row">H</th>       <td>hour-of-day (0-23)</td>          <td>number</td>            <td>0</td>
 *   <tr><th scope="row">m</th>       <td>minute-of-hour</td>              <td>number</td>            <td>30</td>
     * @throws Exception 
     */
	static LocalTime validate(String timeInput) throws Exception {
		LocalTime time = null;
		StringBuilder errorMessage = new StringBuilder();
		
		boolean hasError = true;
		final String standardErrorMessage = "Invalid time, please enter valid time HH:mm \n HH range(0 to 12) and min (0 to 59) "; 
		
		//00:59
		if(timeInput != null && !timeInput.isBlank() ) {
			DateTimeFormatter formatTime = DateTimeFormatter.ofPattern("HH:mm"/* ,Locale.UK */);
			
			try {
				time = LocalTime.parse(timeInput,formatTime);
				
				if(time.getHour() > 12) {
					errorMessage.append("Hour can't be greater than 12. ");
					throw new Exception();
				}
				if(time.getMinute() > 59) {
					errorMessage.append("Minute can't be greater than 59. ");
					log.info("Minute can't be greater than 59. ");
				}
				
				if(time.getHour() >= 0 && time.getMinute() >= 0  ) {
					hasError = false;
				}
				
			}catch (DateTimeParseException e) {
				errorMessage.append(standardErrorMessage);
				log.error(e.getMessage());
				throw new Exception(errorMessage.toString());
			}catch (Exception e) {
				errorMessage.append(standardErrorMessage);
				log.error(e.getMessage());
				throw new Exception(errorMessage.toString());
			}finally {
				if(hasError) {
					
				}
			}
			
			 //System.out.println(time);
			//00:01 to 12:00 
			// hour ranges from 00 to 12
			// min ranges from 00 to 59
		}
		
		return time;
	}
	
	public static void main(String[] args) throws Exception {
		test_suite_1();
	}
	
	static void test_suite_1() throws Exception {
		System.out.println("test_suite_1");
		//validate("01:58");
		//validate("00:58");
		validate("10:58");
		validate("12:58");
		validate("25:58");
		validate("12:61");
		validate("13:58");
		
		
	}
	
	static void test_suite_2() throws Exception {
		System.out.println("test_suite_2");
		validate("1:58");
		validate("0:58");
		validate("12:58");
		validate("13:58");
		validate("00:58");
	}
	
	
}
