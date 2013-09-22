package org.mef.dalgen.codegen;

import org.mef.dalgen.parser.EntityDef;
import org.mef.dalgen.parser.FieldDef;
import org.stringtemplate.v4.ST;

import sfx.SfxContext;

public class DALIntefaceCodeGen extends CodeGenBase
	{
		public DALIntefaceCodeGen(SfxContext ctx, String path)
		{
			super(ctx, path);
		}
		
		public String generate(EntityDef def)
		{
			ST st = _group.getInstanceOf("classdecl");
			st.add("type", def.name);
			st.add("bigName", uppify(def.name));
			String result = st.render(); 
			
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
				st.add("type", getFieldType(def, fieldName));
				st.add("name", fieldName);
				st.add("fullName", query);
				result = st.render(); 
				result += "\n\n";
			}
			return result;
		}

		@Override
		protected String buildField(FieldDef fdef) {
			// TODO Auto-generated method stub
			return null;
		}
	}