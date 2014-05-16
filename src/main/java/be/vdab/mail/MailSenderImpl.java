package be.vdab.mail;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.hateoas.LinkBuilder;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import be.vdab.entities.Cursist;

@Component
public class MailSenderImpl implements MailSender {
	private final Logger logger = Logger.getLogger(this.getClass().getName());
	private final JavaMailSender sender;
	private final String webmaster;

	// private final EntityLinks entityLinks;
	@Autowired
	public MailSenderImpl(JavaMailSender sender,
			@Value("${webmaster}") String webmaster) {
		this.sender = sender;
		this.webmaster = webmaster;
		// this.entityLinks = entityLinks;
	}

	@Override
	@Async
	public void nieuwCursist(Cursist cursist, LinkBuilder url) {
		try {
			MimeMessage message = sender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message);
			helper.setTo(cursist.getEmail());
			helper.setSubject("Nieuw cursist");
			helper.setText(
					String.format(
							"Welkom <strong>%s</strong>!<br><br> Klik <a href='%s'>hier</a> om je account te activeren.",
							cursist.getFullname(), url + "/activate"), true);
			sender.send(message);
		} catch (MessagingException ex) {
			logger.log(Level.SEVERE, "kan mail nieuw cursist niet versturen",
					ex);
		}
	}
}