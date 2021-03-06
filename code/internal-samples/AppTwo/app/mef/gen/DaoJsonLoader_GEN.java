//THIS FILE HAS BEEN AUTO-GENERATED. DO NOT MODIFY.

package mef.gen;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.codehaus.jackson.JsonNode;
import mef.entities.*;
import java.util.Date;
import org.mef.framework.loaders.BaseDaoJsonLoader;



public class DaoJsonLoader_GEN extends BaseDaoJsonLoader
{


	public User readUser(JsonNode node)
	{
		User obj = new User();
		JsonNode jj = node.get("id");
		obj.id = jj.asLong();

				jj = node.get("name");
				obj.name = jj.getTextValue();



		return obj;
	}
	public List<User> loadUsers(JsonNode rootNode) 
	{
		List<User> phoneL = new ArrayList<User>();

    	JsonNode msgNode = rootNode.path("User");
		Iterator<JsonNode> ite = msgNode.getElements();

		int i = 0;
		while (ite.hasNext()) {
			JsonNode temp = ite.next();
			User ph = readUser(temp);

			phoneL.add(ph);
			i++;
		}    	

		return phoneL;
	}
	protected User findUserWithId(long id, List<User> phoneL) 
	{
		for (User ph : phoneL)
		{
			if (ph.id == id)
			{
				return ph;
			}
		}
		return null;
	}
	public Company readCompany(JsonNode node)
	{
		Company obj = new Company();
		JsonNode jj = node.get("id");
		obj.id = jj.asLong();

				jj = node.get("name");
				obj.name = jj.getTextValue();



		return obj;
	}
	public List<Company> loadCompanys(JsonNode rootNode) 
	{
		List<Company> phoneL = new ArrayList<Company>();

    	JsonNode msgNode = rootNode.path("Company");
		Iterator<JsonNode> ite = msgNode.getElements();

		int i = 0;
		while (ite.hasNext()) {
			JsonNode temp = ite.next();
			Company ph = readCompany(temp);

			phoneL.add(ph);
			i++;
		}    	

		return phoneL;
	}
	protected Company findCompanyWithId(long id, List<Company> phoneL) 
	{
		for (Company ph : phoneL)
		{
			if (ph.id == id)
			{
				return ph;
			}
		}
		return null;
	}
	public Computer readComputer(JsonNode node)
	{
		Computer obj = new Computer();
		JsonNode jj = node.get("id");
		obj.id = jj.asLong();

				jj = node.get("name");
				obj.name = jj.getTextValue();

				jj = node.get("introduced");
				obj.introduced = readDate(jj, "yyyy-MM-dd");

				jj = node.get("discontinued");
				obj.discontinued = readDate(jj, "yyyy-MM-dd");

				jj = node.get("company");



		return obj;
	}
	public List<Computer> loadComputers(JsonNode rootNode) 
	{
		List<Computer> phoneL = new ArrayList<Computer>();

    	JsonNode msgNode = rootNode.path("Computer");
		Iterator<JsonNode> ite = msgNode.getElements();

		int i = 0;
		while (ite.hasNext()) {
			JsonNode temp = ite.next();
			Computer ph = readComputer(temp);

			phoneL.add(ph);
			i++;
		}    	

		return phoneL;
	}
	protected Computer findComputerWithId(long id, List<Computer> phoneL) 
	{
		for (Computer ph : phoneL)
		{
			if (ph.id == id)
			{
				return ph;
			}
		}
		return null;
	}
	public Role readRole(JsonNode node)
	{
		Role obj = new Role();
		JsonNode jj = node.get("id");
		obj.id = jj.asLong();

				jj = node.get("name");
				obj.name = jj.getTextValue();



		return obj;
	}
	public List<Role> loadRoles(JsonNode rootNode) 
	{
		List<Role> phoneL = new ArrayList<Role>();

    	JsonNode msgNode = rootNode.path("Role");
		Iterator<JsonNode> ite = msgNode.getElements();

		int i = 0;
		while (ite.hasNext()) {
			JsonNode temp = ite.next();
			Role ph = readRole(temp);

			phoneL.add(ph);
			i++;
		}    	

		return phoneL;
	}
	protected Role findRoleWithId(long id, List<Role> phoneL) 
	{
		for (Role ph : phoneL)
		{
			if (ph.id == id)
			{
				return ph;
			}
		}
		return null;
	}
}
