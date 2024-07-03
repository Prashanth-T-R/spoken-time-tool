package tool.spkntime.core;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * purpose:  Just to  parse input to a valid time and validate the time input, capture error message.
 * Entrypoint: @see TimeValidator.validate(String)
 */
class TimeValidator {
	
	private static final Logger log = LoggerFactory.getLogger(TimeValidator.class);
	
    /**
     * purpose : entry point to validate the time
     * @throws Exception with custom error message to the callee 
     */
	static LocalTime validate(final String timeInput) throws Exception {
		LocalTime time = null;
		StringBuilder errorMessage = new StringBuilder();
		
		boolean hasError = true;
		final String standardErrorMessage = "<BR>Invalid time, please enter valid time HH:mm . <BR> HH range(0 to 12) and min (0 to 59) <BR> for 10 past 10 enter - 10:10 "; 
		
		//00:59
		if(timeInput != null && !timeInput.isBlank() ) {
			
			
			try {
				//time = parseTimewith_stdLibary(timeInput);
				time = parseTimewith_custom(timeInput);
				
				if(time.getHour() > 12) {
					errorMessage.append("Hour can't be greater than 12 . ");
					log.warn(errorMessage.toString());
					log.warn(standardErrorMessage);
					throw new Exception();
				}
				if(time.getMinute() > 59) {
					log.warn(errorMessage.toString());
					log.warn(standardErrorMessage);
					throw new Exception();
				}
				
				if(time.getHour() >= 0 && time.getMinute() >= 0  ) {
					hasError = false;
				}
				
			}catch (NumberFormatException e) {
				errorMessage.append(standardErrorMessage);
				log.error(e.getMessage());
				throw new Exception(errorMessage.toString());
			}catch (Exception e) {
				errorMessage.append(standardErrorMessage);
				log.error(e.getMessage());
				throw new Exception(errorMessage.toString());
			}
		}
		
		return time;
	}
	
	
	/**
	 * Purpose : puerly to create a valid time HH:mm 
	 * Couldn't find a time parser in standard libary which fits for our special spoken times midnight, noon scnearios 
	 * should convert 1:00, 00:00,11:55,12:00
	 * @param timeInput
	 * @return
	 */
	private static LocalTime parseTimewith_custom(final String timeInput) throws NumberFormatException{
		LocalTime time = null;
		if(timeInput != null && !timeInput.isBlank()) {
			if(timeInput.contains(":")) {
				String [] tokens = timeInput.split(":");
				int hour = Integer.parseInt(tokens[0]);
				int min =  Integer.parseInt(tokens[1]);
				time = LocalTime.of(hour, min);
			}
		}
		return time;
	}
	
	
	/**
	 * 
	 *   <tr><th scope="row">a</th>       <td>am-pm-of-day</td>                <td>text</td>              <td>PM</td>
	 *   <tr><th scope="row">B</th>       <td>period-of-day</td>               <td>text</td>              <td>in the morning</td>
	 *   <tr><th scope="row">h</th>       <td>clock-hour-of-am-pm (1-12)</td>  <td>number</td>            <td>12</td>
	 *   <tr><th scope="row">K</th>       <td>hour-of-am-pm (0-11)</td>        <td>number</td>            <td>0</td>
	 *   <tr><th scope="row">k</th>       <td>clock-hour-of-day (1-24)</td>    <td>number</td>            <td>24</td>
	 
	 * doesnt go well with HH:mm
	 * @param timeInput
	 * @return
	 * @see DateTimeFormatter
	 */
	@Deprecated
	private static LocalTime parseTimewith_stdLibary(final String timeInput){
		LocalTime time = null;
		DateTimeFormatter formatTime = DateTimeFormatter.ofPattern("HH:mm"/* ,Locale.UK */);
		formatTime = DateTimeFormatter.ISO_LOCAL_TIME;
		time = LocalTime.parse(timeInput,formatTime);
		return time;
	}
	
	public static void main(String[] args) throws Exception {
		test_suite_1();
	}
	
	private static void test_suite_1() throws Exception {
		System.out.println("test_suite_1");
		//validate("01:58");
		//validate("00:58");
		validate("10:58");
		validate("12:58");
		validate("25:58");
		validate("12:61");
		validate("13:58");
		
		
	}
	
	private static void test_suite_2() throws Exception {
		System.out.println("test_suite_2");
		validate("1:58");
		validate("0:58");
		validate("12:58");
		validate("13:58");
		validate("00:58");
	}
	
	
}
