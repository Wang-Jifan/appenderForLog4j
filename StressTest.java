import java.util.*;
import java.io.*;
import org.apache.log4j.*;
import org.apache.log4j.spi.LoggingEvent;
import org.junit.*;

import appender.MeMAppender;
import appender.VelocityLayout;

import org.junit.Test;

public class StressTest {

	private static MeMAppender app = MeMAppender.getInstance();
	private static VelocityLayout vl = new VelocityLayout();
	@Before
	public void init() {
		app = MeMAppender.getInstance();
		app.setmaxsize(1000001);
	}
	@After
	public void clearMeMAppender() {
		app.setCurrantLogs(null);
		app.setLayout(null);
		app.setovernum(0);
		app.close();
	}

	@Test
	public void MeMNotOverLinkedList() {
	   Logger logger = Logger.getLogger("MeMNotOverLinkedList");
	   logger.addAppender(app);
	   app.setCurrantLogs(new LinkedList<LoggingEvent>());
	   
	   app.setlayout(vl);
	   for (int i = 0; i < 600000; i++) {
			logger.info("info");
		}
	 
	}
	@Test
	public void MeMOVerLinkedTest() {
		   Logger logger = Logger.getLogger("MeMNotOverLinkedList");
		   logger.addAppender(app);
		   app.setCurrantLogs(new LinkedList<LoggingEvent>());
		   
		   app.setlayout(vl);
		   for (int i = 0; i < 1300001; i++) {
				logger.info("info");
		   }
	}
	
	@Test
	public void MeMOVerLinkedTestMore() {
		   Logger logger = Logger.getLogger("MeMNotOverLinkedList");
		   logger.addAppender(app);
		   app.setCurrantLogs(new LinkedList<LoggingEvent>());
		   
		   app.setlayout(vl);
		   for (int i = 0; i < 1800001; i++) {
				logger.info("info");
		   }
	}
	
	@Test
	public void MeMArrayListNotOver() {
		Logger logger = Logger.getLogger("MeMNotOverLinkedList");
		   logger.addAppender(app);
		   app.setCurrantLogs(new ArrayList<LoggingEvent>());
		   
		   app.setlayout(vl);
		   for (int i = 0; i <  1000000; i++) {
				logger.info("info");
			}
	}
	
	@Test
	public void MeMArrayListOver() {
		   Logger logger = Logger.getLogger("MeMNotOverLinkedList");
		   logger.addAppender(app);
		   app.setCurrantLogs(new ArrayList<LoggingEvent>());
		   app.setlayout(vl);
		   for (int i = 0; i < 1009000; i++) {
				logger.info("info");
			}
	}
	@Test
	public void ConsoleAppenderTest() {
		Logger logger = Logger.getLogger("ConsoleAppenderTest");		
		ConsoleAppender consoleAppender = new ConsoleAppender(vl);
		logger.addAppender(consoleAppender);
		
		for (int i = 0; i <100000; i++) {
			logger.info("console");
		}
	}
	
	@Test
	public void FileAppenderTest() {
		Logger logger = Logger.getLogger("FileAppenderTest");
		try {
			FileAppender fileAppender = new FileAppender(vl, "src/test/resources/FileAppenderTest.log");
			logger.addAppender(fileAppender);
			
			for (int i = 0; i < 100000; i++) {
				logger.info("file");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@Test
	public void  VLTest() {	  
	   Logger logger = Logger.getLogger("VLTest");
	   logger.addAppender(app);
	   app.setCurrantLogs(new ArrayList<LoggingEvent>());
	   app.setlayout(vl);
	   for (int i = 0; i < 1000000; i++) {
			logger.info("info");
	   }
	}
	@Test
	public void PatternLayoutTest() {
		   Logger logger = Logger.getLogger("PatternLayoutTest");
		   logger.addAppender(app);
		   app.setCurrantLogs(new ArrayList<LoggingEvent>());
		   PatternLayout pl = new PatternLayout();
		   app.setlayout(pl);
		   for (int i = 0; i < 1000000; i++) {
				logger.info("info");
			
		   }
		   
	}
}


