//THIS FILE HAS BEEN AUTO-GENERATED. DO NOT MODIFY.

package mef.gen;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import mef.entities.*;
import mef.daos.*;
import mef.gen.*;

import java.util.Date;



public class EntityLoaderSaver_GEN 
{


	public static long saveOrUpdate(User obj, User existing, IUserDAO dao)
	{
		if (existing != null)
		{
			obj.id = existing.id;
			//copy everything 
						existing.name = obj.name;
			

			dao.update(existing); //inserts or updates 
		}
		else
		{
			obj.id = 0L;
			dao.save(obj); //inserts or updates 
		}
		return obj.id;
	}
}
