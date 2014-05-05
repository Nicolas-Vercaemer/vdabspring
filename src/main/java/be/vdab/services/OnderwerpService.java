package be.vdab.services;

import be.vdab.entities.Onderwerp;

public interface OnderwerpService {
	void createOrUpdate(Onderwerp onderwerp);
	void delete(Onderwerp onderwerp);
	Iterable<Onderwerp> findAll();
	Onderwerp read(long id);	

}
