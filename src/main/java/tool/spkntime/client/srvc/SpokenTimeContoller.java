package tool.spkntime.client.srvc;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tool.spkntime.core.SpokenTime_Builder;

@RestController
public class SpokenTimeContoller {
		
		@GetMapping("/spoken-time")
		public String time(
					@RequestParam(value="time") String timeInput
					) {
			return SpokenTime_Builder.build().spokenTime(timeInput);
		}
}
