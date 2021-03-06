


import static org.junit.Assert.*;

import org.junit.Test;
import org.mef.framework.sfx.SfxErrorTracker;
import org.mef.tools.mgen.codegen.DalCodeGenerator;


//********************** DAATA!!! ****************************

public class MGEN_Daos extends BaseTest
{
	@Test
	public void testEntity() throws Exception
	{
		init();
		String appDir = this.getCurrentDir("");
		log(appDir);

		DalCodeGenerator gen = new DalCodeGenerator(_ctx);
		gen.genRealDAO = true;
		
		int n = gen.init(appDir);
		assertEquals(4, n);

		boolean b = false;
		gen.genDaoLoader = true;
		b =gen.generateAll();
		
//		b = gen.generateOnce(); //allKnownDAOs
//		b = gen.generate("User");
//		b = gen.generate("Company");
//		b = gen.generate("Computer");
//		b = gen.generate("Role");
		
		assertTrue(b);
	}
	
	private void init()
	{
		this.createContext();
		_ctx.getServiceLocator().registerSingleton(SfxErrorTracker.class, new SfxErrorTracker(_ctx));
	}

}
