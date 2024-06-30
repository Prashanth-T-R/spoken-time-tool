package tool.spkntime.client.srvc;

import java.time.LocalDateTime;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SpokenTimeContoller {
		
		@GetMapping("/spoken-time")
		public String time(
					//String time
					) {
			return ""+LocalDateTime.now();
		}
}
