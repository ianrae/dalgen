

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.mef.framework.fluent.IQueryActionProcessor;
import org.mef.framework.fluent.QStep;
import org.mef.framework.fluent.Query1;
import org.mef.framework.fluent.QueryAction;
import org.mef.framework.fluent.QueryContext;
import org.mef.framework.sfx.SfxTextReader;

import tools.BaseTest;


public class FluentTests extends BaseTest 
{
	public static class Car
	{

	}
	
	public static class Dao
	{
		public List<Car> dataL;
//		public List<QueryX> queryL = new ArrayList<QueryX>();
		public QueryContext<Car> queryctx = new QueryContext<Car>();

		public Dao()
		{
			queryctx.queryL = new ArrayList<QStep>();
		}

		public Query1<Car> query()
		{
			queryctx.queryL = new ArrayList<QStep>();
			return new Query1<Car>(queryctx);
		}

		public void setActionProcessor(IQueryActionProcessor<Car> proc) 
		{
			queryctx.proc = proc;
		}
	}
	
	
	public static class MyProc<T> implements IQueryActionProcessor<T>
	{
		private void log(String s)
		{
			System.out.println(s);
		}
		@Override
		public void start(List<QueryAction> actionL) 
		{
			log("start");
		}

		@Override
		public T findOne() //exactly one
		{
			log("findOne");
			return null;
		}

		@Override
		public T findAny() //0 or 1
		{
			log("findAny");
			return null;
		}
		@Override
		public List<T> findMany() 
		{
			log("findMany");
			return new ArrayList<T>();
		}
		@Override
		public long findCount() 
		{
			log("findCount");
			return 0L;
		}

		@Override
		public void processAction(int index, QueryAction action) 
		{
			log(String.format(" %d. %s", index, action.action));
		}
	}

	@Test
	public void test() 
	{
		Dao dao = new Dao();
		dao.setActionProcessor(new MyProc());
		Query1 x1 = dao.query();

		assertNotNull(x1);

		Car car = dao.query().findAny();
		assertNull(car);

		List<Car> L = dao.query().findMany();
		assertNotNull(L);

		car = dao.query().where("price").eq("45").findAny();
		assertNull(car);

		car = dao.query().where("price").eq("45").and("size").eq(35.5).findAny();
		assertNull(car);

		String query = dao.query().where("price").eq("45").and("size").eq(35.5).dumpQuery();
		assertEquals(" [price W  ] [e s] [size &  ] [e f]", query);

		log("here's any");
		car = dao.query().orderBy("price").fetch("users").limit(1).findAny();
		assertNull(car);
		log("end");
		
		log("here's count");
		long count = dao.query().where("price").eq("45").findCount();
		assertEquals(0L, count);
	}

	@Test
	public void testProcessor() 
	{
		Dao dao = new Dao();
		dao.setActionProcessor(new MyProc());

		Car car = dao.query().orderBy("price").fetch("users").limit(1).findAny();
		assertNull(car);
	}
	
//	@Test 
	public void testRouteEdit()
	{
		String path = this.getTestFile("routes1.txt");
		
		SfxTextReader r = new SfxTextReader();
		ArrayList<String> L = r.readFile(path);
		assertEquals(24, L.size());
		
		boolean haveSeenGET = false;
		boolean haveSeenErrorC = false;
		int i = 0;
		for(String line : L)
		{
			String s = line.trim();
			if (line.contains("ErrorController"))
			{
				haveSeenErrorC = true;
				break;
			}
			
			if (s.startsWith("GET"))
			{
				haveSeenGET = true;
				break;
			}
			i++;
		}
		
		if (! haveSeenErrorC && haveSeenGET)
		{
			//insert at i
			String s = " ";
			L.add(i + 1, s);
			s = "GET xxx";
			L.add(i + 2, s);
			s = "GET yyy";
			L.add(i + 3, s);
		}
		
		log("result:");
		for(String line : L)
		{
			log(line);
		}
	}
}
