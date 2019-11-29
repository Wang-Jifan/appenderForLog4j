import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.LinkedList;

import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggingEvent;
import org.junit.Test;

import appender.MeMAppender;
import appender.VelocityLayout;

public class LightTest {

	@Test
	public void appandertest() {

			Logger listlogger = Logger.getLogger("light_test.class");
			MeMAppender app = MeMAppender.getInstance();
		
		    VelocityLayout velocityLayout = new VelocityLayout();
		    app.setmaxsize(5);
		    app.setlayout(velocityLayout);
		    app.setCurrantLogs(new ArrayList<LoggingEvent>());
		    app.setlayout(velocityLayout);
		    listlogger.addAppender(app);		    
		    
		     
		    listlogger.debug("debug");
			listlogger.info("info");
			listlogger.warn("warn");
			listlogger.error("error");
			listlogger.fatal("fatal");
		    
			
		    assertEquals(5, app.getmaxsize());
		    
		    assertEquals("DEBUG", app.getCurrantLogs().get(0).getLevel().toString());
		    assertEquals(0, app.getovernum());
		    
		    listlogger.addAppender(app);
		    
			assertEquals(velocityLayout, app.getlayout());
			assertEquals(velocityLayout.getpattern(), " Name:$c	Level:$p	Message:$m "+System.lineSeparator());
		
			app.printlogs();
	}
	
	
}
