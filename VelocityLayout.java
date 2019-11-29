package appender;

import java.awt.List;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Date;

import org.apache.log4j.PatternLayout;
import org.apache.log4j.spi.LoggingEvent;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
//import org.apache.velocity.tools.generic.DateTool;



public class VelocityLayout extends PatternLayout {
	String pattern;
	public final static String defaultpattern =" Name:$c	Level:$p	Message:$m ";
	VelocityEngine velocity = new VelocityEngine();
	VelocityContext velocityContext = new VelocityContext();
	public static java.util.List list;
	
	public void setpattern(String pattern) {
		this.pattern = pattern;
	}
	public String getpattern() {
		return this.pattern;
	}
	public VelocityLayout() {
		
		this.pattern =defaultpattern+System.lineSeparator();
	}
	public VelocityLayout(String pattern) {
		this.pattern = pattern+System.lineSeparator();
				
	}
	@Override
	public String format(LoggingEvent event) {

		velocity.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
		velocity.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
		velocity.init();

		StringWriter sw = new StringWriter();
		

		VelocityContext velocityContext = new VelocityContext();
		this.list = MeMAppender.list;
//		for (int i =0 ;i<list.size();i++) {
			velocityContext.put("c", event.getLoggerName());
			velocityContext.put("d", event.toString());
			velocityContext.put("m", event.getMessage());
			velocityContext.put("p", event.getLevel().toString());
			velocityContext.put("t", event.getThreadName());
			velocityContext.put("n", System.lineSeparator());
			
			try {
				Velocity.evaluate(velocityContext, sw, "layout", pattern);
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			
		
		
//		System.out.println(sw.toString());
		return sw.toString();
	}

}
