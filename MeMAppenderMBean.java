package appender;

import java.util.List;

import org.apache.log4j.spi.LoggingEvent;

public interface MeMAppenderMBean {
	
	public long getovernum() ;
	
	public void setovernum(long overnum);
	
	public List<LoggingEvent> getCurrantLogs();
	
	public void setCurrantLogs(List<LoggingEvent> loglist);

	public void setCurrantLogs();
}
