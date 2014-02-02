//THIS FILE HAS BEEN AUTO-GENERATED. DO NOT MODIFY.
package mef.gen;


import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.mef.framework.binder.IFormBinder;
import org.mef.framework.fluent.EntityDBQueryProcessor;
import org.mef.framework.fluent.ProcRegistry;
import org.mef.framework.fluent.QStep;
import org.mef.framework.fluent.Query1;
import org.mef.framework.fluent.QueryContext;
import org.mef.framework.sfx.SfxContext;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.Query;

import play.Logger;
import java.util.Date;

import boundaries.Boundary;
import boundaries.daos.*;
import mef.core.MettleInitializer;

import models.UserModel;
import play.db.ebean.Model.Finder;
import mef.gen.*;

import mef.daos.*;
import mef.entities.*;

import com.avaje.ebean.Page;
public class UserDAO_GEN implements IUserDAO 
{
	@Override
	public void init(SfxContext ctx)
	{
//		this.queryctx = new QueryContext<User>(ctx, User.class);
//		
//		ProcRegistry registry = (ProcRegistry) ctx.getServiceLocator().getInstance(ProcRegistry.class);
//		EntityDBQueryProcessor<User> proc = new EntityDBQueryProcessor<User>(ctx, _L);
//		registry.registerDao(User.class, proc);
	}
	
	@Override
	public void save(User entity) 
	{
		UserModel t = (UserModel)entity.cc; 
		if (t == null) //not yet known by db? (newly created)
		{
			System.out.println("save-auto-create");
			t = createModelFromEntity(entity); //create model, set entity, and call all setters
		}
		else //touch all (for ebean), except id
		{
			touchAll(t, entity);
		}
		t.save();
		entity.id = t.getId(); //in case created on
	}

	@Override
	public User findById(long id) 
	{
		UserModel t = (UserModel)UserModel.find.byId(id);
		if (t == null)
		{
			return null;
		}

		t.entity = createEntityFromModel(t); //create entity, set m.cc and t.entity, copy all fields from model to entity
		return t.entity;
	}

	@Override
	public List<User> all() 
	{
		List<UserModel> L = UserModel.all();
		List<User> entityL = createEntityFromModel(L);
		return entityL;
	}

	@Override
	public int size() 
	{
		return UserModel.all().size();
	}

	@Override
	public void delete(long id) 
	{
		UserModel t = UserModel.find.byId(id);
		t.delete();
	}

	//User
	//create model, set entity, and call all setters
	public static UserModel createModelFromEntity(User entity)
	{
		if (entity == null)
		{
			return null;
		}
		UserModel t = new UserModel();
		entity.cc = t;
		t.entity = entity;
		touchAll(t, entity);
		return t;
	}
	//create entity, set m.cc and t.entity, copy all fields from model to entity
	public static User createEntityFromModel(UserModel t)
	{
		if (t == null)
		{
			return null;		
		}

		if (t.entity != null && t.entity.cc != null)
		{
			return t.entity; //already exists
		}
		User entity = new User();
		entity.cc = t;
		entity.id = (t.getId() == null) ? 0 : t.getId();		
		t.entity = entity;
		touchAll(entity, t);
		return entity;
	}
	public static List<User> createEntityFromModel(List<UserModel> L)
	{
		ArrayList<User> entityL = new ArrayList<User>();
		for(UserModel t : L)
		{
			User entity = createEntityFromModel(t);
			if (entity != null) //why??!!
			{
				entityL.add(entity);
			}
		}
		return entityL;
	}


	@Override
	public void updateFrom(IFormBinder binder) 
	{
		UserModel model = (UserModel) binder.getRawObject();
		model.update();
	}


	@Override
	public void update(User entity) 
	{
		UserModel t = (UserModel)entity.cc; 
		if (t == null) //not yet known by db? (newly created)
		{
			t.entity = null; //throw exception
		}
		else //touch all (for ebean), except id
		{
			touchAll(t, entity);
		}
		t.update();
	}

	protected static void touchAll(UserModel t, User entity)
	{
		t.setId(entity.id);
		t.setName(entity.name);
	}

	protected static void touchAll(User entity, UserModel t)
	{
		entity.name = t.getName();
	}

	@Override
	public User find_by_name(String val) 
	{
		UserModel model = UserModel.find.where().eq("name", val).findUnique();
		if (model == null)
		{
			return null;
		}
		User entity = createEntityFromModel(model);
		return entity;
	}

	@Override
	public Query1<User> query() 
	{
//		queryctx.queryL = new ArrayList<QStep>();
//		return new Query1<User>(queryctx);
		return null;
	}

	
}
