package org.mef.tools.mgen.codegen.generators;


	import org.mef.framework.sfx.SfxContext;
import org.mef.tools.mgen.parser.EntityDef;
import org.mef.tools.mgen.parser.FieldDef;
import org.stringtemplate.v4.ST;


	public class FormBinderCodeGen extends CodeGenBase
		{
			public FormBinderCodeGen(SfxContext ctx)
			{
				super(ctx);
			}
			
			@Override
			public String generate(EntityDef def)
			{
				String result = genHeader(def.name); 
				
				ST st = _group.getInstanceOf("classdecl");
				st.add("type", def.name);
				st.add("name", getClassName(def));
				result += st.render(); 
				
				result += genFields(def);
				
				st = _group.getInstanceOf("endclassdecl");
				result += st.render(); 
				
				return result;
			}
			
			@Override
			public String getClassName(EntityDef def)
			{
				return  def.name + "FormBinder";
			}
			
			
			@Override
			protected String buildField(EntityDef def, FieldDef fdef)
			{
				return "";
			}
		}