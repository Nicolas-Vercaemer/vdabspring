package be.vdab.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import be.vdab.dao.CursistDAO;
import be.vdab.entities.Cursist;

@Service
@Transactional
class CursistServiceImpl implements CursistService {
	private final CursistDAO cursistDAO;

	@Autowired
	CursistServiceImpl(CursistDAO cursistDAO) {
		this.cursistDAO = cursistDAO;
	}

	@Override
	public void create(Cursist cursist) {
		cursist.setId(cursistDAO.save(cursist).getId());

	}

	@Override
	public boolean isEmaiInGebruik(Cursist cursist) {
		return cursistDAO.findByEmail(cursist.getEmail()) != null;
	}

	@Override
	public Iterable<Cursist> findAll() {
		return cursistDAO.findAll();
	}

	@Override
	public Cursist read(long id) {
		return cursistDAO.findOne(id);
	}

}