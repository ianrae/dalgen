package org.mef.framework.metadata.validate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mef.framework.metadata.Value;



public class ValContext
{
	private int failCount;
    private Map<String,List<ValidationErrorSpec>> mapErrors;
	
	public ValContext()
	{
		mapErrors = new HashMap<String,List<ValidationErrorSpec>>();
	}
	
	public void validate(Value val)
	{
		ValidationErrors errors = new ValidationErrors();
		errors.map = mapErrors;
		errors.itemName = val.getItemName();
		if (! val.validate(errors))
		{
			failCount++;
		}
	}
	
	public int getFailCount()
	{
		return failCount;
	}
	
    public Map<String,List<ValidationErrorSpec>> getErrors()
    {
    	return mapErrors;
    }
}