//THIS FILE HAS BEEN AUTO-GENERATED. DO NOT MODIFY.

package mef.gen;

import java.util.ArrayList;
import java.util.List;
import org.mef.framework.dao.IDAO;
import mef.daos.*;
import mef.daos.mocks.*;
import boundaries.daos.*;
import org.mef.framework.sfx.SfxContext;
import java.util.Date;
import org.mef.framework.auth.AuthRole;
import org.mef.framework.auth.AuthTicket;

public class AllKnownDAOs_GEN  
{
public List<IDAO> registerDAOs(SfxContext ctx, boolean createMocks)
{
	ArrayList<IDAO> L = new ArrayList<IDAO>();
    if (createMocks)
{
	IUserDAO dal = new MockUserDAO();
	ctx.getServiceLocator().registerSingleton(IUserDAO.class, dal);
	L.add(dal);
}
else
{
	IUserDAO dal = new UserDAO();
	ctx.getServiceLocator().registerSingleton(IUserDAO.class, dal);
	L.add(dal);
}	if (createMocks)
{
	IBlogDAO dal = new MockBlogDAO();
	ctx.getServiceLocator().registerSingleton(IBlogDAO.class, dal);
	L.add(dal);
}
else
{
	IBlogDAO dal = new BlogDAO();
	ctx.getServiceLocator().registerSingleton(IBlogDAO.class, dal);
	L.add(dal);
}	if (createMocks)
{
	IAuthSubjectDAO dal = new MockAuthSubjectDAO();
	ctx.getServiceLocator().registerSingleton(IAuthSubjectDAO.class, dal);
	L.add(dal);
}
else
{
	IAuthSubjectDAO dal = new AuthSubjectDAO();
	ctx.getServiceLocator().registerSingleton(IAuthSubjectDAO.class, dal);
	L.add(dal);
}	if (createMocks)
{
	IAuthRoleDAO dal = new MockAuthRoleDAO();
	ctx.getServiceLocator().registerSingleton(IAuthRoleDAO.class, dal);
	L.add(dal);
}
else
{
	IAuthRoleDAO dal = new AuthRoleDAO();
	ctx.getServiceLocator().registerSingleton(IAuthRoleDAO.class, dal);
	L.add(dal);
}	if (createMocks)
{
	IAuthTicketDAO dal = new MockAuthTicketDAO();
	ctx.getServiceLocator().registerSingleton(IAuthTicketDAO.class, dal);
	L.add(dal);
}
else
{
	IAuthTicketDAO dal = new AuthTicketDAO();
	ctx.getServiceLocator().registerSingleton(IAuthTicketDAO.class, dal);
	L.add(dal);
}	if (createMocks)
{
	IAuthRuleDAO dal = new MockAuthRuleDAO();
	ctx.getServiceLocator().registerSingleton(IAuthRuleDAO.class, dal);
	L.add(dal);
}
else
{
	IAuthRuleDAO dal = new AuthRuleDAO();
	ctx.getServiceLocator().registerSingleton(IAuthRuleDAO.class, dal);
	L.add(dal);
}	
	return L;
}
}
