package be.vdab.dao;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;

import be.vdab.entities.Dag;

public interface DagDAO extends JpaRepository<Dag, Long> {
	Dag findByDatum(Date datum);

}
