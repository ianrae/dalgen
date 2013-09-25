package org.mef.dalgen.codegen;

import org.mef.dalgen.parser.EntityDef;
import org.mef.dalgen.parser.FieldDef;
import org.stringtemplate.v4.ST;

import sfx.SfxContext;

public class MockDALCodeGen extends CodeGenBase
{
	public boolean genExtension;
	
	public MockDALCodeGen(SfxContext ctx, String path, String packageName)
	{
		super(ctx, path, packageName);
	}
	
	@Override
	public String generate(EntityDef def)
	{
		String result = genHeader(); 
		ST st = _group.getInstanceOf("classdecl");
		
		String className = "Mock" + def.name + "DAL";
		if (genExtension)
		{
			className += "_GEN";
		}
		st.add("name", className);
		st.add("type", def.name);
		result += st.render(); 
		
		result += genQueries(def);
		st = _group.getInstanceOf("endclassdecl");
		result += st.render(); 
		
		return result;
	}
	
	protected String genQueries(EntityDef def)
	{
		String result = "";
		for(String query : def.queryL)
		{
			ST st = _group.getInstanceOf("querydecl");
			String fieldName = getFieldName(query);
			st.add("type", def.name); //getFieldType(def, fieldName));
			st.add("fieldType", getFieldType(def, fieldName));
			st.add("name", fieldName);
			result = st.render(); 
			result += "\n\n";
		}
		return result;
	}
	
	@Override
	protected String buildField(FieldDef fdef)
	{
		return "";
	}

}