package xmake;



import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.mef.dalgen.codegen.AppScaffoldCodeGenerator;
import org.mef.dalgen.codegen.PresenterScaffoldCodeGenerator;

import unittests.BaseTest;

//********************** CAREFUL!!! ****************************

public class ScaffoldAppTests extends BaseTest
{
	@Test
	public void testEntity() throws Exception
	{
		log("--testEntity--");
		createContext();
		AppScaffoldCodeGenerator gen = new AppScaffoldCodeGenerator(_ctx);
		
		gen.disableFileIO = true;  //***** WATCH OUT!11 ****
		
		String appDir = this.getCurrentDir("..\\..\\samples\\AppTwo");
		String stDir = this.getCurrentDir("src\\org\\mef\\dalgen\\resources\\app");
log(appDir);
log(stDir);
		
		int n = gen.init(appDir, stDir);
		assertEquals(4, n);

		boolean b = false;
//		boolean b = gen.generate(0);
//		b = gen.generate("User");
//		b = gen.generate("Phone");
//		b = gen.generate("Zoo");
		if (b)
		{}
	}

}
