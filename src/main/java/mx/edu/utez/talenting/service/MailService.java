package mx.edu.utez.talenting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {
	
	@Autowired
	private JavaMailSender mailSender;
	
	public void sendMail (String to, String subject, String text) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("talentingUtez@gmail.com");
		message.setTo(to);
		message.setSubject(subject);
		message.setText(text);
		System.out.println("Enviando mail..........");
		mailSender.send(message);
	}
	
	@Bean
	public SimpleMailMessage templateSimpleMessage() {
	    SimpleMailMessage message = new SimpleMailMessage();
	    System.out.println("Entra a la plantilla...........");
	    message.setText(
	      "<H1>Holaaaa</H1>This is the test email template for your email:\n prueba\n");
	    return message;
	}
}
