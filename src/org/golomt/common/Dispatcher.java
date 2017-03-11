package org.golomt.common ;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.dispatcher.ng.filter.StrutsPrepareAndExecuteFilter ;

/**
 * @author mungunsukh
 *
 */
public class Dispatcher extends StrutsPrepareAndExecuteFilter
{

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain arg2) throws IOException, ServletException
	{
		//Энэ үйлдлийг хийхгүй бол алдаа гарч байна.Why?
		req.getParameterMap() ;

		WrappedRequest wp = new WrappedRequest((HttpServletRequest)req) ;

		if (wp.getHeader("Cache-Control") == null)
		{
			HttpServletResponse wres = (HttpServletResponse)res;
			wres.setHeader( "Pragma", "no-cache" );
			wres.setHeader( "Cache-Control", "no-cache" );
			wres.setDateHeader( "Expires", 0 );

			wp.setNoCache() ;
		}

		// TODO Автоматаар үүссэн код
		super.doFilter(wp, res, arg2);
	}

}