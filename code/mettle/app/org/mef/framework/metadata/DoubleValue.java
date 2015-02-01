package org.mef.framework.metadata;


public class DoubleValue extends Value
{
	public DoubleValue(double val)
	{
		super(Value.TYPE_DOUBLE, val);
	}
	
	@Override
	public String toString() 
	{
		Double d = this.getDouble();
		return d.toString();
	}

}
