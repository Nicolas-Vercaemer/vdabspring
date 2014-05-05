package be.vdab.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import be.vdab.datasource.CreateTestDataSourceBean;
import be.vdab.entities.Onderwerp;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { CreateTestDataSourceBean.class, CreateDAOBeans.class, }) // DataSource bean en DAO beans laden in IOC container
@Transactional // omringt elke test met een transactie, na de test rollback
public class OnderwerpServiceImplTest {
	@Autowired
	private OnderwerpDAO onderwerpDAO;
	@PersistenceContext//Importeer een entitymanager om de cache er van te kunnen leeg maken
	private EntityManager entityManager;
	
	@Test
	public void create(){
		Onderwerp onderwerp = new Onderwerp("testNaam", false);
		onderwerpDAO.save(onderwerp);
		Assert.assertNotEquals(0, onderwerp.getId()); // 
		
	}
	@Test
	public void update(){
		Onderwerp onderwerp = new Onderwerp("testNaam", false);
		onderwerp.setId(onderwerpDAO.save(onderwerp).getId());
		onderwerp.setAfwezig(true);
		onderwerpDAO.saveAndFlush(onderwerp);
		//onderwerpDAO.flush() Voert alle bewerkingen uit. 
		entityManager.clear();//Maakt de cache van de entitymanager leeg. Anders onthoud Spring het object en wordt de database niet aangesproken. 
		Onderwerp onderwerpUitDatabase = onderwerpDAO.findOne(onderwerp.getId());//Nu is Spring Data verplicht naar de database te gaan.
		Assert.assertTrue(onderwerpUitDatabase.isAfwezig());
	}
	@Test
	public void delete(){
		Onderwerp onderwerp = new Onderwerp("testNaam", false);
		onderwerp.setId(onderwerpDAO.save(onderwerp).getId());
		onderwerpDAO.delete(onderwerp);
		Assert.assertFalse(onderwerpDAO.exists(onderwerp.getId()));
		
		
		
	}

}
