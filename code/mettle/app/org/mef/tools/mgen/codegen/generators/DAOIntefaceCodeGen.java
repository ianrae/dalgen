package org.mef.tools.mgen.codegen.generators;

import java.util.ArrayList;
import java.util.List;

import org.mef.framework.sfx.SfxContext;
import org.mef.tools.mgen.parser.EntityDef;
import org.mef.tools.mgen.parser.FieldDef;
import org.stringtemplate.v4.ST;


public class DAOIntefaceCodeGen extends CodeGenBase
	{
		public DAOIntefaceCodeGen(SfxContext ctx)
		{
			super(ctx);
		}
		
		@Override
		public String generate(EntityDef def)
		{
			this.isExtended = def.shouldExtend(EntityDef.DAL_INTERFACE);
			
			String result = genHeader(); 
			
			ST st = _group.getInstanceOf("classdecl");
			st.add("type", def.name);
			st.add("name", getClassName(def));
			result += st.render(); 
			
			result += genQueries(def);
			result += genMethods(def);
			
			st = _group.getInstanceOf("endclassdecl");
			result += st.render(); 
			
			return result;
		}
		

		@Override
		public String getClassName(EntityDef def)
		{
			String s = "I" + uppify(def.name) + "DAO";
			return makeClassName(s, def.shouldExtend(EntityDef.DAL_INTERFACE));
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
				st.add("fullName", query);
				result = st.render(); 
				result += "\n\n";
			}
			return result;
		}
		
		@Override
		protected String buildField(EntityDef def, FieldDef fdef) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public boolean isExtended() 
		{
			return isExtended;
		}
	}