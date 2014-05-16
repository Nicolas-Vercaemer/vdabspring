package be.vdab.mail;

import org.springframework.hateoas.LinkBuilder;

import be.vdab.entities.Cursist;

public interface MailSender {
	void nieuwCursist(Cursist cursist, LinkBuilder linkBuilder);
}