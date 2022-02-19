package semNome.hackathon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(String toEmail, String body, String subject){
        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(toEmail);
        message.setText(body);
        message.setSubject(subject);
        message.setFrom("jpdelgado2001@gmail.com");

        mailSender.send(message);
    }

}
