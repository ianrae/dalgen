package mef.core;

import java.util.HashMap;
import java.util.Map;

import mef.daos.IAuthRoleDAO;
import mef.daos.IAuthRuleDAO;
import mef.daos.IAuthSubjectDAO;
import mef.daos.IAuthTicketDAO;

import org.mef.framework.auth.AuthRole;
import org.mef.framework.auth.AuthRule;
import org.mef.framework.auth.AuthSubject;
import org.mef.framework.auth.AuthTicket;
import org.mef.framework.auth.IAuthorizer;
import org.mef.framework.sfx.SfxBaseObj;
import org.mef.framework.sfx.SfxContext;

public class MyAuthorizer extends SfxBaseObj implements IAuthorizer
{
	private IAuthRoleDAO _roleDao;
	private IAuthTicketDAO _ticketDao;
	private IAuthRuleDAO _ruleDao;
	private IAuthSubjectDAO _subjectDao;
	
	Map<String, AuthRole> allRoles;
	
	public MyAuthorizer(SfxContext ctx)
	{
		super(ctx);
	}
	
	private AuthRole findRole(String roleName)
	{
		if (allRoles == null) //not thread-safe!!
		{
			allRoles = new HashMap<String, AuthRole>();
			for(AuthRole role : _roleDao.all())
			{
				allRoles.put(role.name, role);
			}
		}
		
		return allRoles.get(roleName);
	}
	
	@Override
	public boolean isAuthEx(AuthSubject subj, AuthRole role, AuthTicket ticket) 
	{
		AuthRule rule = _ruleDao.find_by_subject_and_role_and_ticket(subj, role, ticket);
		return (rule != null);
	}

	public void init(IAuthSubjectDAO subjectDao, IAuthRoleDAO roleDao,
			IAuthTicketDAO ticketDao, IAuthRuleDAO ruleDao) 
	{
		this._subjectDao = subjectDao;
		this._roleDao = roleDao;
		this._ticketDao = ticketDao;
		this._ruleDao = ruleDao;
		
	}

	@Override
	public AuthSubject findSubject(String identityId) 
	{
		if (identityId == null || identityId.isEmpty())
		{
			return null;
		}
		AuthSubject subject = _subjectDao.find_by_name(identityId);
		return subject;
	}

	@Override
	public boolean isAuth(AuthSubject subj, String roleName, AuthTicket ticket) 
	{
		AuthRole role = this.findRole(roleName);
		if (role == null)
		{
			return false;
		}
		
		return isAuthEx(subj, role, ticket);
	}
	
}