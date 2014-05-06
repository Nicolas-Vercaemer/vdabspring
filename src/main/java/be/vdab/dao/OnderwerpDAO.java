package be.vdab.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import be.vdab.entities.Onderwerp;

public interface OnderwerpDAO extends JpaRepository<Onderwerp, Long> {
	Onderwerp findByNaam(String naam);

	long countByNaamAndIdNot(String naam, long id);

}
