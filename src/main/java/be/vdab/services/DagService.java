package be.vdab.services;

import java.util.Date;

import be.vdab.entities.Dag;

public interface DagService {
	Dag findByDatum(Date datum);
}
