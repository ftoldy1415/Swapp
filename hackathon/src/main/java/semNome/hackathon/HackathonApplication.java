package semNome.hackathon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class HackathonApplication {

	@Autowired
	private EmailSenderService service;

	public static void main(String[] args) {
		SpringApplication.run(HackathonApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void triggerEmail(){
		service.sendEmail("brunofilipe377@gmail.com", "vai po crlhes", "ola");
	}

}
