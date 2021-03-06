//THIS FILE HAS BEEN AUTO-GENERATED. DO NOT MODIFY.

package mef.daos.mocks;

import java.util.List;
import java.util.ArrayList;
import mef.entities.*;
import mef.daos.*;
import org.mef.framework.binder.IFormBinder;
import org.codehaus.jackson.map.ObjectMapper;
import mef.gen.*;
import org.mef.framework.entitydb.EntityDB;
import java.util.Date;
import com.avaje.ebean.Page;
import org.mef.framework.auth.AuthRole;
import org.mef.framework.auth.AuthTicket;
public class MockBlogDAO implements IBlogDAO
{
    protected List<Blog> _L = new ArrayList<Blog>();
    protected EntityDB<Blog> _entityDB = new EntityDB<Blog>();

    @Override
    public int size() 
    {
        return _L.size();
    }

    @Override
    public Blog findById(long id) 
    {
    	Blog entity = this.findActualById(id);
    	if (entity != null)
    	{
    		return new Blog(entity); //return copy
        }
        return null; //not found
    }

    protected Blog findActualById(long id) 
    {
        for(Blog entity : _L)
        {
            if (entity.id == id)
            {
                return entity;
            }
        }
        return null; //not found
    }

    @Override
    public List<Blog> all() 
    {
        return _L; //ret copy??!!
    }

    @Override
    public void delete(long id) 
    {
        Blog entity = this.findActualById(id);
        if (entity != null)
        {
            _L.remove(entity);
        }
    }

    @Override
    public void save(Blog entity) 
    {
    	if (entity.id == null)
		{
    		entity.id = new Long(0L);
    	}

    	if (findActualById(entity.id) != null)
    	{
    		throw new RuntimeException(String.format("save: id %d already exists", entity.id));
    	}


        delete(entity.id); //remove existing
        if (entity.id == 0)
        {
        	entity.id = nextAvailIdNumber();
        }

         _L.add(entity);
     }

    private Long nextAvailIdNumber() 
    {
    	long used = 0;
        for(Blog entity : _L)
        {
            if (entity.id > used)
            {
                used = entity.id;
            }
        }
        return used + 1;
	}

	@Override
	public void update(Blog entity) 
	{
		this.delete(entity.id);
		this.save(entity);
	}

    @Override
    public void updateFrom(IFormBinder binder) 
    {
    	Blog entity = (Blog) binder.getObject();
		this.delete(entity.id);
    	save(entity);

    }


	//query
    @Override
    public Blog find_by_name(String val) 
    {
		Blog user = _entityDB.findFirstMatch(_L, "name", val);
		return user;
    }

}
