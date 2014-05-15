package be.vdab.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import be.vdab.dao.DagDAO;
import be.vdab.entities.Dag;

@Service
@Transactional(readOnly = false)
public class DagServiceImpl implements DagService {
	private final DagDAO dagDAO;

	@Autowired
	DagServiceImpl(DagDAO dagDAO) {
		this.dagDAO = dagDAO;
	}

	@Override
	public Dag findByDatum(Date datum) {
		return dagDAO.findByDatum(datum);
	}

	@Override
	public Dag save(Dag dag) {
		return dagDAO.save(dag);
	}

	@Override
	public Iterable<Dag> findall() {
		return dagDAO.findAll();
	}

}