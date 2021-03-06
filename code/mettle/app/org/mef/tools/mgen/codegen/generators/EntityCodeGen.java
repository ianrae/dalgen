package org.mef.tools.mgen.codegen.generators;

import java.util.ArrayList;
import java.util.List;

import org.mef.framework.sfx.SfxContext;
import org.mef.tools.mgen.parser.EntityDef;
import org.mef.tools.mgen.parser.FieldDef;
import org.stringtemplate.v4.ST;


public class EntityCodeGen extends CodeGenBase
	{
		public EntityCodeGen(SfxContext ctx)
		{
			super(ctx);
		}
		
		@Override
		public String generate(EntityDef def)
		{
//			this.isExtended = def.shouldExtend(EntityDef.ENTITY);
			String result = genHeader(); 
			
			ST st = _group.getInstanceOf("classdecl");
			st.add("type", def.name);
			st.add("name", getClassName(def));
			st.add("mname", def.name + "Model");
			st.add("args", buildArgList(def));
			st.add("inits", buildCtorInitsList(def, false));
			st.add("copyinits", buildCtorInitsList(def, true));
			st.add("isParentOfExtended", this.isParentOfExtended);
			
			result += st.render(); 
			
			result += genFields(def);
			result += genIdStuff(def);
			
			st = _group.getInstanceOf("endclassdecl");
			result += st.render(); 
			
			return result;
		}
		
		private Object buildCtorInitsList(EntityDef def, boolean isCopyCtor) 
		{
			ArrayList<String> L = new ArrayList<String>();
			for(FieldDef fdef : def.fieldL)
			{
				if (fdef.name.equals("id"))
				{}
				else if (! fdef.isReadOnly)
				{
					boolean isEntity = isFieldAModel(def, fdef);
					if (isEntity)
					{
						String uname = this.uppify(fdef.name);
						String s = String.format("this.set%sModel(%s);", uname, fdef.name);
						if (isCopyCtor)
						{
							s = String.format("this.set%sModel(entity.get%sModel());", uname, uname);
						}
						L.add(s);
					}
					else
					{
						String uname = this.uppify(fdef.name);
						String s = String.format("this.set%s(%s);", uname, fdef.name);
						if (isCopyCtor)
						{
							s = String.format("this.set%s(entity.get%s());", uname, uname);
						}
						L.add(s);
					}
				}
			}
			return L;
		}

		private List<String> buildArgList(EntityDef def)
		{
			ArrayList<String> L = new ArrayList<String>();
			for(FieldDef fdef : def.fieldL)
			{
				if (fdef.name.equals("id"))
				{}
				else if (! fdef.isReadOnly)
				{
					String s = String.format("%s %s", fdef.typeName, fdef.name);
					L.add(s);
				}
			}
			return L;
		}
		
		@Override
		public String getClassName(EntityDef def)
		{
			return this.makeClassName(def.name); //, def.shouldExtend(EntityDef.ENTITY));
		}
		
		
		@Override
		protected String buildField(EntityDef def, FieldDef fdef)
		{
			ST st = _group.getInstanceOf("fielddecl");
			String result = "";
			st.add("type", fdef.typeName);
			st.add("name", fdef.name);
			st.add("upperName", uppify(fdef.name));
			st.add("readonly", fdef.isReadOnly);
			
			//hack
			boolean isEntity = isFieldAModel(def, fdef);
			if (isEntity)
			{
				this.log("ISENTITY! " + fdef.typeName);
			}
			
			st.add("isEntity", isEntity);
			result = st.render(); 

			String s = "";
			result = s + result;
			return result;
		}
		
		protected boolean isFieldAModel(EntityDef def, FieldDef fdef)
		{
			//hack
			boolean isEntity = this.isEntity(def, fdef.typeName);
			if (fdef.typeName.endsWith("Model"))
			{
				isEntity = true; //later strip off Model and check isEntity
				return true;
			}
			return false;
		}
		
		protected String genIdStuff(EntityDef def)
		{
			if (this.isParentOfExtended)
			{
				return ""; //want empty class
			}
			
			String result = "";
			for(FieldDef fdef : def.fieldL)
			{
				if (! fdef.name.equals("id")) //!!later use IHasId
				{
					continue;
				}
				
				ST st = _group.getInstanceOf("idstuff");
				st.add("type", fdef.typeName);
				st.add("name", fdef.name);
				st.add("upperName", uppify(fdef.name));
				st.add("className", def.name);
				
				result = st.render(); 
				result += "\n\n";
			}
			return result;
		}		
	}