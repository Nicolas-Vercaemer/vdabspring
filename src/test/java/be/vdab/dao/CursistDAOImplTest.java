package be.vdab.dao;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import be.vdab.datasource.CreateTestDataSourceBean;
import be.vdab.entities.Cursist;

// enkele imports ...
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { CreateTestDataSourceBean.class, CreateDAOBeans.class, }) // DataSource bean en DAO beans laden in IOC container
@Transactional // omringt elke test met een transactie, na de test rollback
public class CursistDAOImplTest {
@Autowired
private CursistDAO cursistDAO;
@Test
public void create() {
Cursist cursist =new Cursist("TestVoornaam", "TestAchternaam",  new Date(),
new Date(), new Date(), "testemail@email.com");
cursistDAO.save(cursist);
Assert.assertNotEquals(0, cursist.getId()); // id moet autonumber hebben:
}
}