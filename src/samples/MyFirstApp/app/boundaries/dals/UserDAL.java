package boundaries.dals;

import java.util.List;

import boundaries.Boundary;

import models.UserModel;

import mef.dals.IUserDAL;
import mef.entities.User;

public class UserDAL implements IUserDAL 
{

	@Override
	public void save(User entity) 
	{
		UserModel t = (UserModel)entity.carrier; 
		if (t == null) //not yet known by db? (newly created)
		{
			System.out.println("save-auto-create");
			t = Boundary.convertToUserModel(entity);
		}
		else //touch all (for ebean)
		{
			t.setName(entity.name);
		}
		t.save();
	}

	@Override
	public User findById(long id) 
	{
		UserModel t = UserModel.find.byId(id);
		if (t == null)
		{
			return null;
		}
		t.entity = Boundary.convertFromUserModel(t);
		return t.entity;
	}

	@Override
	public List<User> all() 
	{
		List<UserModel> L = UserModel.all();
		List<User> entityL = Boundary.convertFromUser(L);
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


}
