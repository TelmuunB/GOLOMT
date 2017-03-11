package org.golomt.common;

import javax.servlet.http.*;


public class SessionCounter implements HttpSessionListener {
	static private int activeSessions;

	  // deploy this in the WEB-INF/classes so all other servlets can have access to this method
	  public static int getActiveSessions() {
	    return activeSessions;
	  }

	  public void sessionCreated(HttpSessionEvent event) {
		 event.getSession().setAttribute("JSESSIONID", event.getSession().getId()) ;
	     activeSessions++;
	  }

	  public void sessionDestroyed(HttpSessionEvent event) {
	     activeSessions--;
	  }
}
