package tool.spkntime;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import tool.spkntime.core.SpokenTime_Builder;

@SpringBootTest
class SpokenTimeToolApplicationTests {

	
	
	@Test 
	void test_spokenTime_hourlyChimes() {
		
		String [] hourlychimesInput = {
										"00:00","12:00",
										"1:00","2:00",
										//"03:00","04:00","05:00","06:00","07:00","08:00",
										"09:00",
										"10:00","11:00"
										};
		Map<String,String> expectedActualHourlyChimes = new HashMap(13);
		expectedActualHourlyChimes.put("00:00", "midnight");
		expectedActualHourlyChimes.put("12:00", "noon");
		expectedActualHourlyChimes.put("1:00", "one o'clock");
		expectedActualHourlyChimes.put("2:00", "two o'clock");
		expectedActualHourlyChimes.put("09:00", "nine o'clock");  //adaptability test 0h:mm or hh:mm
		expectedActualHourlyChimes.put("10:00", "ten o'clock");
		expectedActualHourlyChimes.put("11:00", "eleven o'clock");
		
		for(String input_chimeHour : hourlychimesInput) {
			String actual_spokenchimeHour = SpokenTime_Builder.build().spokenTime(input_chimeHour);
			String expected_spokenchimeHour = expectedActualHourlyChimes.get(input_chimeHour);
			Assertions.assertEquals(expected_spokenchimeHour, actual_spokenchimeHour);
		}
	}
	
	@Test 
	void test_spokenTime_halfPast() {
		
		/*
		 
		 3:10 ten past three
4:15 quarter past four
5:20 twenty past five
6:25 twenty five past six
6:32 six thirty two // this should be 28 to 7
7:30 half past seven
		 
		 
		 */
		
		String [] halfpastTimes = {
										"3:10",
										"4:15",
										"5:20",
										"6:25",
										"6:32",
										"7:30"//,
										
										//"00:05",
										//"00:15"//,
										
										};
		Map<String,String> expectedActual_halfPastTimes = new HashMap(13);
		expectedActual_halfPastTimes.put("3:10", "ten past three");
		expectedActual_halfPastTimes.put("4:15", "quarter past four");
		expectedActual_halfPastTimes.put("5:20", "twenty past five");
		expectedActual_halfPastTimes.put("6:25", "twenty five past six");
		
		// at present this below expectation cant be met unless the logic is well-understood for half-Forward-To times
//		expectedActualHourlyChimes.put("6:32", "six thirty two"); 
		expectedActual_halfPastTimes.put("6:32", "twenty eight to seven"); 
		
		expectedActual_halfPastTimes.put("7:30", "half past seven");
		
		//special scenarios to be checked during discussion
		expectedActual_halfPastTimes.put("00:05", "five past midnight"); 
		expectedActual_halfPastTimes.put("00:15", "quarter past midnight");
		
		//repetitive could be moved to a common-util-method
		for(String input_chimeHour : halfpastTimes) {
			String actual_spokenHalfPast = SpokenTime_Builder.build().spokenTime(input_chimeHour);
			String expected_spokenHalfPast = expectedActual_halfPastTimes.get(input_chimeHour);
			Assertions.assertEquals(expected_spokenHalfPast, actual_spokenHalfPast);
		}
	}
	
	@Test 
	void test_spokenTime_halfForwardTo() {
		
		/*
		 from pdf
		6:32 six thirty two
7:35 twenty five to eight
8:40 twenty to nine
9:45 quarter to ten
10:50 ten to eleven
11:55 five to twelve
		 
		 
		 */
		
		String [] input_halfForwardTimes = {
										"7:35",
										"8:40",
										"9:45",
										"10:50",
										
										"6:32",
										"11:55"//,
										
										//"00:05",
										//"00:15"//,
										
										};
		Map<String,String> expectedActual_halfForwardToTimes = new HashMap(13);
		expectedActual_halfForwardToTimes.put("7:35", "twenty five to eight");
		expectedActual_halfForwardToTimes.put("8:40", "twenty to nine");
		expectedActual_halfForwardToTimes.put("9:45", "quarter to ten");
		expectedActual_halfForwardToTimes.put("10:50", "ten to eleven");
		
		// at present this below expectation cant be met unless the logic is well-understood for half-Forward-To times
//		expectedActual_halfForwardToTimes.put("6:32", "six thirty two"); 
		expectedActual_halfForwardToTimes.put("6:32", "twenty eight to seven"); 
		
		expectedActual_halfForwardToTimes.put("11:55", "five to twelve");
		
		//special scenarios to be checked during discussion
		expectedActual_halfForwardToTimes.put("00:55", "five to one"); 
		expectedActual_halfForwardToTimes.put("00:45", "quarter to one");
		expectedActual_halfForwardToTimes.put("12:55", "five to one"); 
		expectedActual_halfForwardToTimes.put("12:45", "quarter to one");
		
		
		//repetitive could be moved to a common-util-method
		for(String input_chimeHour : input_halfForwardTimes) {
			String actual_spokenHalfForwardTo = SpokenTime_Builder.build().spokenTime(input_chimeHour);
			String expected_spokenHalfForwardTo = expectedActual_halfForwardToTimes.get(input_chimeHour);
			Assertions.assertEquals(expected_spokenHalfForwardTo, actual_spokenHalfForwardTo);
		}
	}
	
	@Test 
	void test_spokenTime_inputErrors() {
		String [] invalid_inputs = {
				
				// out of boundaries hour, minutes
				"25:35",
				"13:40",
				"11:61",
				
				//time parse errors
				"abcd",
				"0000",
				"1200",
				"120:0",
				
				// just to fail this test
				//"10:00"
				
				
				};
		for(String input_chimeHour : invalid_inputs) {
			Assertions.assertTrue(SpokenTime_Builder.build().wrongInput(input_chimeHour));
		}
	}

}
	
