package be.vdab.services;

import be.vdab.entities.Cursist;

public interface CursistService {
	Iterable<Cursist> findAll();

	Cursist read(long id);

	void create(Cursist cursist);

	void update(Cursist cursist);

	boolean isEmaiInGebruik(Cursist cursist);

}