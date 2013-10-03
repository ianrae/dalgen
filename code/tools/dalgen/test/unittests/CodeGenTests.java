package unittests;

import static org.junit.Assert.*;

import org.junit.Test;
import org.mef.dalgen.codegen.DALUtilsCodeGen;
import org.mef.dalgen.codegen.generators.BoundaryCodeGen;
import org.mef.dalgen.codegen.generators.DAOIntefaceCodeGen;
import org.mef.dalgen.codegen.generators.EntityCodeGen;
import org.mef.dalgen.codegen.generators.KnownDAOsCodeGen;
import org.mef.dalgen.codegen.generators.MockDAOCodeGen;
import org.mef.dalgen.codegen.generators.ModelCodeGen;
import org.mef.dalgen.codegen.generators.RealDAOCodeGen;
import org.mef.dalgen.parser.DalGenXmlParser;
import org.mef.dalgen.parser.EntityDef;

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
		
		String path = this.getTemplateFile("entity.stg");
		String packageName = "org.mef.dalgen.unittests.gen";
		EntityCodeGen gen = new EntityCodeGen(_ctx, path, packageName);
		String code = gen.generate(def);	
		log(code);
		assertEquals(true, 10 < code.length());
		writeFile("Task", code);
	}
	@Test
	public void testModel() throws Exception
	{
		log("--testModel--");
		def.extendModel = true;
		String path = this.getTemplateFile("model.stg");
		String packageName = "org.mef.dalgen.unittests.gen";
		ModelCodeGen gen = new ModelCodeGen(_ctx, path, packageName);
		gen.forUnitTest = true;
		String code = gen.generate(def);	
		log(code);
		assertEquals(true, 10 < code.length());
		
		writeFile("TaskModel", code);
	}
	
	@Test
	public void testIDAL() throws Exception
	{
		log("--testIDAL--");
		def.extendInterface = true;
		String path = this.getTemplateFile("dao_interface.stg");
		String packageName = "org.mef.dalgen.unittests.gen";
		DAOIntefaceCodeGen gen = new DAOIntefaceCodeGen(_ctx, path, packageName);
		String code = gen.generate(def);	
		log(code);
		assertEquals(true, 10 < code.length());
		writeFile("ITaskDAL", code);
	}
	
	@Test
	public void testMockDAL() throws Exception
	{
		log("--testMockDAL--");
		def.extendMock = true;
		String path = this.getTemplateFile("dao_mock.stg");
		String packageName = "org.mef.dalgen.unittests.gen";
		MockDAOCodeGen gen = new MockDAOCodeGen(_ctx, path, packageName);
		String code = gen.generate(def);	
		log(code);
		assertEquals(true, 10 < code.length());
		writeFile("MockTaskDAL", code);
	}
	
	@Test
	public void testRealDAL() throws Exception
	{
		log("--testRealDAL--");
		def.extendReal = true;
		String path = this.getTemplateFile("dao_real.stg");
		String packageName = "boundaries.dals";
		RealDAOCodeGen gen = new RealDAOCodeGen(_ctx, path, packageName);
		String code = gen.generate(def);	
		log(code);
		assertEquals(true, 10 < code.length());
		writeFile("TaskDAL", code);
	}
	
	@Test
	public void testKnownDALS() throws Exception
	{
		log("--testKnownDALS--");
		String path = this.getTemplateFile("dao_all_known.stg");
		String packageName = "mef.gen";
		KnownDAOsCodeGen gen = new KnownDAOsCodeGen(_ctx, path, packageName);
		String code = gen.generate(def);	
		log(code);
		assertEquals(true, 10 < code.length());
		writeFile("TaskDAL", code);
	}
	
	@Test
	public void testBoundary() throws Exception
	{
		log("--testBoundary--");
		String path = this.getPresenterTemplateFile("boundary.stg");
		String packageName = "boundaries";
		BoundaryCodeGen gen = new BoundaryCodeGen(_ctx, path, packageName);
		String code = gen.generate(def);	
		log(code);
		assertEquals(true, 10 < code.length());
		writeFile("TaskBoundary", code);
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

	private void writeFile(String fileName, String code)
	{
//		String outPath = this.getUnitTestDir(String.format("gen\\%s.java", fileName));
//		log(fileName + ": " + outPath);
//		SfxTextWriter w = new SfxTextWriter(outPath, null);
//		w.addLine(code);
//		boolean b = w.writeFile();
//		assertEquals(true, b);
		
	}

}
