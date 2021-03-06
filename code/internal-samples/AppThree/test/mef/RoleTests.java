package mef;

import static org.junit.Assert.*;

import mef.core.DaoFinder;
import mef.core.Initializer;
import mef.core.MyAuthorizer;
import mef.core.MySubject;
import mef.daos.IAuthRuleDAO;
import mef.daos.IAuthRoleDAO;
import mef.daos.IAuthTicketDAO;
import mef.daos.IAuthSubjectDAO;
import mef.daos.mocks.MockAuthRuleDAO;
import mef.daos.mocks.MockAuthRoleDAO;
import mef.daos.mocks.MockAuthTicketDAO;
import mef.daos.mocks.MockAuthSubjectDAO;
import mef.entities.AuthRule;
import mef.entities.AuthSubject;

import org.junit.Before;
import org.junit.Test;
import org.mef.framework.auth.AuthRole;
import org.mef.framework.auth.AuthTicket;
import org.mef.framework.auth.IAuthSubject;

public class RoleTests extends BaseTest
{
	@Test
	public void test() 
	{
		init();
		IAuthRoleDAO dao = this.getDAO();
		assertNotNull(dao);
		assertNotNull(_ticketDao);
		assertNotNull(_ruleDao);
		
		buildRoles();
	}

	@Test
	public void testAuthRules() 
	{
		init();
		buildRoles();
		buildSubjects();
		buildTickets();
		AuthRole role = _roleDao.find_by_name("Viewer");
		
		AuthSubject subjEntity = _userDao.find_by_name("alice");
		IAuthSubject subj = MySubject.createFrom(subjEntity);
		AuthTicket t = _ticketDao.findById(1L);
		assertNotNull(t);
		
		AuthRule rule = new AuthRule(subjEntity, role, t);
		_ruleDao.save(rule);
		
		MyAuthorizer auth = createAuthorizer();
		assertFalse(auth.isAuth(null, null, null));

		assertTrue(auth.isAuthEx(subj, role, t));
		
		AuthSubject subjEntity2 = _userDao.find_by_name("bob");
		IAuthSubject subj2 = MySubject.createFrom(subjEntity2);
		
		assertFalse(auth.isAuthEx(subj2, role, t));
		
		AuthTicket t2 = _ticketDao.findById(2L);
		assertFalse(auth.isAuthEx(subj, role, t2));
		
		
	}

	@Test
	public void testNullUser() 
	{
		init();
		buildRoles();
		buildSubjects();
		buildTickets();
		AuthRole role = _roleDao.find_by_name("Viewer");
		AuthTicket t = _ticketDao.findById(1L);
		assertNotNull(t);
		
		AuthRule rule = new AuthRule(null, role, t);
		_ruleDao.save(rule);
		assertEquals(1, _ruleDao.size());
		
		MyAuthorizer auth = createAuthorizer();
		assertFalse(auth.isAuth(null, null, null));

		assertTrue(auth.isAuthEx(null, role, t));
		assertFalse(auth.isAuthEx(null, role, null));
		
		AuthSubject subjEntity2 = _userDao.find_by_name("bob");
		IAuthSubject subj2 = MySubject.createFrom(subjEntity2);
		assertFalse(auth.isAuthEx(subj2, role, t));
		
	}
	
	//--- helpers ---
	private void buildRoles()
	{
		AuthRole role = new AuthRole("Viewer");
		_roleDao.save(role);
		role = new AuthRole("Viewer");
		_roleDao.save(role);
		
		assertEquals(2, _roleDao.size());
	}
	
	private void buildSubjects()
	{
		AuthSubject subj = new AuthSubject("alice", 0L);
		_userDao.save(subj);
		subj = new AuthSubject("bob", 0L);
		_userDao.save(subj);
		
		assertEquals(2, _userDao.size());
	}
	
	private void buildTickets()
	{
		AuthTicket t = new AuthTicket();
		_ticketDao.save(t);
		assertEquals(1, _ticketDao.size());
	}
	
	private MockAuthRoleDAO _roleDao;
	private MockAuthTicketDAO _ticketDao;
	private MockAuthRuleDAO _ruleDao;
	private MockAuthSubjectDAO _userDao;
	
	
	@Before
	public void init()
	{
		super.init();
		_roleDao = getDAO();
		_ticketDao = (MockAuthTicketDAO) DaoFinder.getAuthTicketDao();
		_ruleDao = (MockAuthRuleDAO) DaoFinder.getAuthRuleDao();
		_userDao = (MockAuthSubjectDAO) DaoFinder.getAuthSubjectDao();
	}
	
	private MockAuthRoleDAO getDAO()
	{
		MockAuthRoleDAO dal = (MockAuthRoleDAO) DaoFinder.getAuthRoleDao(); 
		return dal;
	}
	
	private MyAuthorizer createAuthorizer()
	{
		MyAuthorizer auth = new MyAuthorizer(_ctx);
		return auth;
	}	

}
