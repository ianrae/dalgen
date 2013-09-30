package mef;

import java.io.File;

import mef.core.Initializer;
import mef.dals.mocks.MockPhoneDAL;
import mef.dals.mocks.MockTaskDAL;
import mef.dals.mocks.MockUserDAL;

import org.apache.commons.io.FilenameUtils;
import org.mef.framework.sfx.SfxContext;


public class BaseTest 
{
	protected SfxContext _ctx;
	
	public void init()
	{
		_ctx = Initializer.createContext(new MockTaskDAL(), new MockUserDAL(), new MockPhoneDAL());
	}

	
	protected void log(String s)
	{
		System.out.println(s);
	}
	
	protected String getTestFile(String filepath)
	{
		String currentDir = new File(".").getAbsolutePath();
		String path = FilenameUtils.concat(currentDir, "test\\testfiles");
		path = FilenameUtils.concat(path, filepath);
		return path;
	}

}
