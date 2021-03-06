package org.mef.tools.mgen.codegen.phase;

import java.util.List;

import org.mef.framework.sfx.SfxContext;
import org.mef.tools.mgen.codegen.CodeGenerator;
import org.mef.tools.mgen.codegen.ICodeGenerator;
import org.mef.tools.mgen.codegen.generators.CodeGenBase;
import org.mef.tools.mgen.parser.EntityDef;

public class DaoGenerator extends CodeGenerator implements ICodeGenerator
{
	private String filename;  //no path
	private String baseDir;   //location of source (StringTemplate file)
	private String packageName;
	private String relPath;
	private EntityDef def;
	private CodeGenBase gen;
	private List<String> extraImportsL;
	private boolean isExtended;
	private boolean isParentOfExtended;
	private boolean userCanModifyFlag;

	public DaoGenerator(SfxContext ctx, CodeGenBase gen, String baseDir, String filename, 
			EntityDef def, String packageName, String relPath, List<String> extraImportsL,
			boolean isExtended, boolean isParentOfExtended, boolean userCanModifyFlag) 
	{
		super(ctx);
		this.filename = filename;
		this.baseDir = baseDir;
		this.packageName = packageName;
		this.relPath = relPath;
		this.def = def;
		this.gen = gen;
		this.extraImportsL = extraImportsL;
		this.isExtended = isExtended;
		this.isParentOfExtended = isParentOfExtended;
		this.userCanModifyFlag = userCanModifyFlag;
	}

	@Override
	public String name() 
	{
		return "dao";
	}

	@Override
	public void initialize(String appDir) throws Exception
	{
		init(appDir);
		String path = getResourceOrFilePath(baseDir, filename);
	}
	@Override
	public boolean run() throws Exception 
	{
		String path = getResourceOrFilePath(baseDir, filename);

		gen.setExtended(isExtended); //propogate to codegen
		gen.setIsParentOfExtended(isParentOfExtended);
		gen.setUserCanModifyFlag(userCanModifyFlag);
		
		gen.init(path, packageName);
		if (! def.enabled)
		{
			log(def.name + " disabled -- no files generated.");
			return true; //do nothing
		}

		if (this.extraImportsL.size() > 0)
		{
			gen.extraImportsL = this.extraImportsL;
		}

		String originalRelPath = relPath;

		String code = gen.generate(def);	

		if (gen.isExtended())
		{
			relPath = "app\\mef\\gen";			
		}
		//log(code);
		String className = gen.getClassName(def);	
		boolean b = writeFile(appDir, relPath, className + ".java", code);
		if (!b)
		{
			return false;
		}

		//			//if _GEN and parent class doesn't exist
		//			if (className.endsWith("_GEN"))
		//			{
		//				className = className.replace("_GEN", "");
		//				String path = this.pathCombine(appDir, originalRelPath);
		//				path = this.pathCombine(path, className + ".java");
		//				File f = new File(path);
		//				if (! f.exists())
		//				{
		////					this.log("FFFFF");
		//					_needParentClass = true;
		//				}
		//			}

		return true;
	}

	@Override
	public boolean generateAll() throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean generate(String name) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}
}