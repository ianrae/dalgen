package mef;

import static org.junit.Assert.*;

import java.util.List;

import mef.daos.IUserDAO;
import mef.daos.mocks.MockUserDAO;
import mef.entities.User;
import mef.presenters.UserPresenter;

import org.junit.Before;
import org.junit.Test;

public class UserDAOTests extends BaseTest
{

	@Test
	public void test() 
	{
		buildDAO();
		assertEquals(2, _dal.size());
		
		List<User> L = _dal.search_by_name("aaaa");
		assertEquals(0, L.size());
		L = _dal.search_by_name("bob");
		assertEquals(1, L.size());
	}
	
	
	//---------- helpers ------------
	private void buildDAO()
	{
		User u = new User();
		Long id = 1L;
		u.id = id++;
		u.name = "bob";
		u.phone = null;
		_dal.save(u);
		
		u = new User();
		u.id = id++;
		u.name = "alice";
		u.phone = null;
		_dal.save(u);
	}
	
	private MockUserDAO _dal;
	@Before
	public void init()
	{
		super.init();
		_dal = getDAO();
	}
	
	private MockUserDAO getDAO()
	{
		MockUserDAO dal = (MockUserDAO) _ctx.getServiceLocator().getInstance(IUserDAO.class); 
		return dal;
	}

}
