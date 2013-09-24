package boundaries.dals;

import java.util.List;

import boundaries.Boundary;

import models.TaskModel;

import mef.dals.ITaskDAL;
import mef.entities.Task;

public class TaskDAL implements ITaskDAL 
{

	@Override
	public void save(Task entity) 
	{
		TaskModel t = (TaskModel)entity.carrier; 
		if (t == null) //not yet known by db? (newly created)
		{
			t = Boundary.convertToTaskModel(entity);
		}
		t.save();
	}

	@Override
	public Task findById(long id) 
	{
		TaskModel t = TaskModel.find.byId(id);
		t.entity = Boundary.convertFromTaskModel(t);
		return t.entity;
	}

	@Override
	public List<Task> all() 
	{
		List<TaskModel> L = TaskModel.all();
		List<Task> entityL = Boundary.convertFromTask(L);
		return entityL;
	}

	@Override
	public int size() 
	{
		return TaskModel.all().size();
	}

	@Override
	public void delete(long id) 
	{
		TaskModel t = TaskModel.find.byId(id);
		t.delete();
	}

	@Override
	public Task find_by_label(String val) 
	{
		// TODO Auto-generated method stub
		return null;
	}

}
