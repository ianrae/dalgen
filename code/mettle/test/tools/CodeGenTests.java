package tools;

import static org.junit.Assert.*;

import org.junit.Test;
import org.mef.tools.mgen.codegen.generators.BoundaryCodeGen;
import org.mef.tools.mgen.codegen.generators.CodeGenBase;
import org.mef.tools.mgen.codegen.generators.DAOIntefaceCodeGen;
import org.mef.tools.mgen.codegen.generators.DaoEntityLoaderCodeGen;
import org.mef.tools.mgen.codegen.generators.EntityCodeGen;
import org.mef.tools.mgen.codegen.generators.FormBinderCodeGen;
import org.mef.tools.mgen.codegen.generators.KnownDAOsCodeGen;
import org.mef.tools.mgen.codegen.generators.MockDAOCodeGen;
import org.mef.tools.mgen.codegen.generators.ModelCodeGen;
import org.mef.tools.mgen.codegen.generators.RealDAOCodeGen;
import org.mef.tools.mgen.parser.EntityDef;

import sfx.SfxFileUtils;
import sfx.SfxTextWriter;


public class CodeGenTests extends BaseCodeGenTest
{
	@Test
	public void testEntity() throws Exception
	{
		log("--testEntity--");
		for(EntityDef tmp : def.allEntityTypes)
		{
			log(tmp.name);
		}
		
		EntityCodeGen gen = new EntityCodeGen(_ctx);
		genAndLog(gen, "entity.stg", "org.mef.dalgen.unittests.gen");
	}
	@Test
	public void testModel() throws Exception
	{
		log("--testModel--");
		def.extendModel = true;
		ModelCodeGen gen = new ModelCodeGen(_ctx);
		genAndLog(gen, "model.stg", "org.mef.dalgen.unittests.gen");
		gen.forUnitTest = true;
	}
	
	@Test
	public void testIDAL() throws Exception
	{
		log("--testIDAL--");
		def.extendInterface = true;
		DAOIntefaceCodeGen gen = new DAOIntefaceCodeGen(_ctx);
		genAndLog(gen, "dao_interface.stg", "org.mef.dalgen.unittests.gen");
	}
	
	@Test
	public void testMockDAL() throws Exception
	{
		log("--testMockDAL--");
		def.extendMock = true;
		MockDAOCodeGen gen = new MockDAOCodeGen(_ctx);
		genAndLog(gen, "dao_mock.stg", "org.mef.dalgen.unittests.gen");
	}
	
	@Test
	public void testRealDAL() throws Exception
	{
		log("--testRealDAL--");
		def.extendReal = true;
		RealDAOCodeGen gen = new RealDAOCodeGen(_ctx);
		genAndLog(gen, "dao_real.stg", "boundaries.dals");
	}
	
	@Test
	public void testKnownDALS() throws Exception
	{
		log("--testKnownDALS--");
		String path = this.getTemplateFile("dao_all_known.stg");
		String packageName = "mef.gen";
		KnownDAOsCodeGen gen = new KnownDAOsCodeGen(_ctx);
		genAndLog(gen, "dao_all_known.stg", "mef.gen");
	}
	
	@Test
	public void testBoundary() throws Exception
	{
		log("--testBoundary--");
		BoundaryCodeGen gen = new BoundaryCodeGen(_ctx);
		genPresenterAndLog(gen, "boundary.stg", "boundaries");
	}
	
	@Test
	public void testFormBinder() throws Exception
	{
		log("--testFormBinder--");
		FormBinderCodeGen gen = new FormBinderCodeGen(_ctx);
		genPresenterAndLog(gen, "formbinder.stg", "boundaries.binders");
	}
	
	@Test
	public void testDaoLoader() throws Exception
	{
		log("--testDaoLoader--");
		DaoEntityLoaderCodeGen gen = new DaoEntityLoaderCodeGen(_ctx);
		genAndLog(gen, "dao_entity_loader.stg", "mef.core");
	}
	
	@Test
	public void testPresenterUnitTest() throws Exception
	{
		log("--testPresenterUnitTest--");
		BoundaryCodeGen gen = new BoundaryCodeGen(_ctx);
		genPresenterAndLog(gen, "presenter-unit-test.stg", "mef");
	}
	
//	@Test
//	public void testDALUtils() throws Exception
//	{
//		log("--testDALUtils--");
//		createContext();
//		EntityDef def = readEntityDef();
//		
//		String path = this.getTestFile("dao_utils.stg");
//		String packageName = "org.mef.dalgen.unittests.gen";
//		DALUtilsCodeGen gen = new DALUtilsCodeGen(_ctx, path, packageName);
//		String code = gen.generate(def);	
//		log(code);
//		assertEquals(true, 10 < code.length());
//		writeFile("TaskDALUtils", code);
//	}
	
	//--- helper fns ---
	private void genAndLog(CodeGenBase gen, String templateFile, String packageName)
	{
		String path = this.getTemplateFile(templateFile);
		gen.init(path, packageName);
		String code = gen.generate(def);	
		log(code);
		assertEquals(true, 10 < code.length());
	}
	private void genPresenterAndLog(CodeGenBase gen, String templateFile, String packageName)
	{
		String path = this.getPresenterTemplateFile(templateFile);
		gen.init(path, packageName);
		String code = gen.generate(def);	
		log(code);
		assertEquals(true, 10 < code.length());
		
	}
	
}
