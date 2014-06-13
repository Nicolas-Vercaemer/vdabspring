package be.vdab.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import be.vdab.commons.Common;
import be.vdab.dao.CursistDAO;
import be.vdab.entities.Cursist;
import be.vdab.mail.MailSender;

@Service
@Transactional
class CursistServiceImpl implements CursistService {
	private final CursistDAO cursistDAO;
	private final MailSender mailSender;
	private final Common common;

	@Autowired
	CursistServiceImpl(CursistDAO cursistDAO, MailSender mailSender,
			Common common) {
		this.cursistDAO = cursistDAO;
		this.mailSender = mailSender;
		this.common = common;
	}

	@Override
	@CacheEvict(value = "cursisten", allEntries = true)
	public void create(Cursist cursist) {
		cursist.setId(cursistDAO.save(cursist).getId());
		mailSender.nieuwCursist(cursist, common.getEntityLinks()
				.linkForSingleResource(Cursist.class, cursist.getId()));

	}

	@Override
	@CacheEvict(value = "cursisten", allEntries = true)
	public void update(Cursist cursist) {
		cursistDAO.save(cursist);
	}

	@Override
	public boolean isEmaiInGebruik(Cursist cursist) {
		return cursistDAO.findByEmail(cursist.getEmail()) != null;
	}

	@Override
	@Cacheable("cursisten")
	public Iterable<Cursist> findAll() {
		return cursistDAO.findAllByActief(true, new Sort(Direction.ASC,
				"voornaam"));
	}

	@Override
	public Cursist read(long id) {
		return cursistDAO.findOne(id);
	}

}