package be.vdab.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import be.vdab.dao.OnderwerpDAO;
import be.vdab.entities.Onderwerp;

@Service
@Transactional(readOnly = false)
class OnderwerpServiceImpl implements OnderwerpService {
	private final OnderwerpDAO onderwerpDAO;

	@Autowired
	OnderwerpServiceImpl(OnderwerpDAO onderwerpDAO) {
		this.onderwerpDAO = onderwerpDAO;
	}

	@Override
	public void createOrUpdate(Onderwerp onderwerp) {
		onderwerp.setId(onderwerpDAO.save(onderwerp).getId());
	}

	@Override
	public void delete(Onderwerp onderwerp) {
		onderwerpDAO.delete(onderwerp);
	}

	@Override
	public Iterable<Onderwerp> findAll() {
		return onderwerpDAO.findAll(new Sort(Direction.ASC, "naam"));
	}

	@Override
	public Onderwerp read(long id) {
		return onderwerpDAO.findOne(id);
	}

	@Override
	public boolean isNaamAlInGebruik(Onderwerp onderwerp) {
		// return onderwerpDAO.findByNaam(onderwerp.getNaam()) != null;
		return onderwerpDAO.countByNaamAndIdNot(onderwerp.getNaam(),
				onderwerp.getId()) != 0;
	}

}
