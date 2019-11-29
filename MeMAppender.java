package appender;


import javax.management.*;

import org.apache.commons.collections.ListUtils;
import org.apache.log4j.*;
import org.apache.log4j.spi.LoggingEvent;


import java.util.*;
import java.io.*;
import java.lang.management.ManagementFactory;

public class MeMAppender extends AppenderSkeleton implements MeMAppenderMBean{
	public LoggingEvent event;
	public long maxsize;
	public Layout layout;
	public long overnum=0;
	public static java.util.List<LoggingEvent> list;
    private static MeMAppender instance = new MeMAppender();
	
    
    
    
    public static void main(String[] args) throws Exception { 
         
            MBeanServer mbs = ManagementFactory.getPlatformMBeanServer(); 
            ObjectName name = new ObjectName("src.main.java.apeender:type=MeMAppender"); 
            MeMAppender mbean = new MeMAppender(); 
            mbs.registerMBean(mbean, name); 
      
        } 
    
    
	public static MeMAppender getInstance(){
	      return instance;
	   }
	
	public void close() {
		// TODO Auto-generated method stub
	
	}

	
	public List<LoggingEvent> getCurrantLogs(){
		return Collections.unmodifiableList(this.list);
	}
	public void setCurrantLogs(List<LoggingEvent> loglist){
		this.list = loglist;
	}
	
	
	public void setCurrantLogs(){
		this.list = new ArrayList<LoggingEvent>();
	}
	
	public void printlogs() {
		java.util.List printlist = eventToString();
//		printlist.clear();
		for (int i =0;i<printlist.size();i++) {
			System.out.println(printlist.get(i));
		}
		this.overnum = 0;
	}
	public List<String> eventToString(){
		List<String> stringList = new ArrayList<String>();
		for(int i =0 ;i<list.size();i++) {
			stringList.add(this.layout.format(list.get(i)));
		}
		return Collections.unmodifiableList(stringList);
	}
	
	
	
	public boolean requiresLayout() {
		// TODO Auto-generated method stub
		return true;
	}
	
	@Override
	protected void append(LoggingEvent event) {
		
		this.event = event;
		if (list.size()<maxsize) {
			list.add(event);
		}
		else {
			list.remove(0);
			overnum++;
			list.add(event);
			
		}
		getCurrantLogs();
	}
	public void setovernum(long overnum) {
		this.overnum = overnum;
	}
	public long getovernum() {
		return this.overnum;
	}
	
	public void setmaxsize(long maxsize) {
		this.maxsize = maxsize;
	}
	public long getmaxsize() {
		return this.maxsize;
	}
	public void setlayout(Layout layout) {
		this.layout = layout;
	}
	public Layout getlayout() {
		return this.layout;
	}

	public long getDiscardedLogCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void setDiscardedLogCount(long discardedLognumber) {
		// TODO Auto-generated method stub
		
	}

	public List<LoggingEvent> getLogList() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setLogList(List<LoggingEvent> loglist) {
		// TODO Auto-generated method stub
		
	}

	public void setLogList() {
		// TODO Auto-generated method stub
		
	}
}
