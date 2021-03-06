//THIS FILE HAS BEEN AUTO-GENERATED. DO NOT MODIFY.

package mef.presenters;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.mef.framework.Logger;
import org.mef.framework.binder.IFormBinder;
import org.mef.framework.commands.CreateCommand;
import org.mef.framework.commands.DeleteCommand;
import org.mef.framework.commands.EditCommand;
import org.mef.framework.commands.IndexCommand;
import org.mef.framework.commands.Command;
import org.mef.framework.commands.NewCommand;
import org.mef.framework.commands.ShowCommand;
import org.mef.framework.commands.UpdateCommand;
import org.mef.framework.presenters.Presenter;
import org.mef.framework.replies.Reply;
import org.mef.framework.sfx.SfxBaseObj;
import org.mef.framework.sfx.SfxContext;

import mef.daos.IUserDAO;
import mef.entities.Computer;
import mef.entities.User;
import mef.presenters.replies.ComputerReply;
import mef.presenters.replies.UserReply;
public class UserPresenter extends Presenter
{
	private IUserDAO _dao;
	private UserReply _reply;

	public UserPresenter(SfxContext ctx)
	{
		super(ctx); 
		_dao = (IUserDAO) getInstance(IUserDAO.class);
	}
	@Override
	protected UserReply createReply()
	{
		_reply = new UserReply();
		return _reply;
	}

	public UserReply onIndexCommand(IndexCommand cmd)
	{
		UserReply reply = createReply(); 
		reply.setDestination(Reply.VIEW_INDEX);
		reply._allL = _dao.all();
		return reply;
	}

	public UserReply onNewCommand(NewCommand cmd)
	{
		UserReply reply = createReply();
		reply.setDestination(Reply.VIEW_NEW);
		
		reply._entity = new User();
		reply._entity.name = "defaultname";
		return reply; 
	}

	public UserReply onCreateCommand(CreateCommand cmd)
	{
		UserReply reply = createReply();
		reply.setDestination(Reply.VIEW_NEW);
		
		IFormBinder binder = cmd.getFormBinder();
		if (! binder.bind())
		{
			reply.setFlashFail("binding failed!");
			Logger.info("BINDING failed");
			reply._entity = (User) binder.getObject();
			return reply;
		}
		else
		{
			User entity = (User) binder.getObject();
			if (entity == null)
			{
				reply.setFailed(true);
			}
			else
			{
				_dao.save(entity);
				Logger.info("saved new");
				reply.setFlashSuccess("created user " + entity.name);
				reply.setDestination(Reply.FORWARD_INDEX);
			}
			return reply;
		}
	}

	public UserReply onEditCommand(EditCommand cmd)
	{
		UserReply reply = createReply();
		reply.setDestination(Reply.VIEW_EDIT);
		
		User user = _dao.findById(cmd.id);
		if (user == null)
		{
			reply.setDestination(Reply.FORWARD_NOT_FOUND);
			return reply;
		}
		else
		{
			reply._entity = user;
			return reply;
		}
	}
	public UserReply onUpdateCommand(UpdateCommand cmd)
	{
		UserReply reply = createReply();
		reply.setDestination(Reply.VIEW_EDIT);
		IFormBinder binder = cmd.getFormBinder();
		if (! binder.bind())
		{
			reply.setFlashFail("binding failed!");
			reply._entity = (User) binder.getObject();
			if (reply._entity == null)
			{
				Logger.info("failbinding null entity!");
				reply._entity = _dao.findById(cmd.id); //fix better later!!
			}
			return reply;
		}
		else
		{
			//ensure id is a valid id
			User user = _dao.findById(cmd.id);
			if (user == null)
			{
				reply.setDestination(Reply.FORWARD_NOT_FOUND);
				return reply;
			}
			_dao.updateFrom(binder);
			Logger.info("saved update ");
			reply.setDestination(Reply.FORWARD_INDEX);
			return reply;
		}
	}


	public UserReply onDeleteCommand(DeleteCommand cmd)
	{
		UserReply reply = createReply();
		reply.setDestination(Reply.FORWARD_INDEX);
		User t = _dao.findById(cmd.id);
		if (t == null)
		{
			reply.setDestination(Reply.FORWARD_NOT_FOUND);
			reply.setFlashFail("could not find user");
		}
		else
		{
			_dao.delete(cmd.id);
		}
		return reply;
	}

	public UserReply onShowCommand(ShowCommand cmd)
	{
		UserReply reply = createReply();
		reply.setDestination(Reply.VIEW_SHOW);
		User t = _dao.findById(cmd.id);
		if (t == null)
		{
			reply.setDestination(Reply.FORWARD_NOT_FOUND);
			reply.setFlashFail("could not find user");
		}
		else
		{
			reply._entity = t;
		}
		return reply;
	}





}
