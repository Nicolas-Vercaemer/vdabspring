package be.vdab.services;

import be.vdab.entities.Cursist;

public interface CursistService {
	void create(Cursist cursist);
	boolean isEmaiInGebruik(Cursist cursist);

}
