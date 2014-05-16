package be.vdab.dao;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import be.vdab.entities.Cursist;

public interface CursistDAO extends JpaRepository<Cursist, Long> {
	Cursist findByEmail(String email);

	Iterable<Cursist> findAllByActief(boolean actief, Sort sort);
}
