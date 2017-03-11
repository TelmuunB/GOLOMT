package org.golomt.common ;

import java.io.UnsupportedEncodingException;

import java.util.Collections;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * @author mungunsukh
 *
 */
public class WrappedRequest extends HttpServletRequestWrapper
{
    private Map<String, String[]> allParameters = null;

    private static final String ANSI = "ÔÖÓÆÝÍÃØ¯ÇÊÚÉÛÁªÀÕÐÎËÄÏß×¨ÑÌÈÒÜÂÞÅÙôöóæýíãø¿çêúéûáºàõðîëäïÿ÷¸ñìèòüâþåù" ;
    private static final String UTF8 = "ФЦУЖЭНГШҮЗКЪЙЫБӨАХРОЛДПЯЧЁСМИТЬВЮЕЩфцужэнгшүзкъйыбөахролдпячёсмитьвюещ" ;


    private boolean nocache = false ;

    private String charCode = "UTF-8" ;
    /**
     * Create a new request wrapper that will merge additional parameters into
     * the request object without prematurely reading parameters from the
     * original request.
     *
     * @param request
     * @param additionalParams
     */
    public WrappedRequest(final HttpServletRequest request)
    {
        super(request);

        try
        {
        	if (this.getCharacterEncoding() == null)
        	{
        		charCode = "iso-8859-1" ;
        	}
        	this.setCharacterEncoding("UTF-8") ;
        }
        catch(UnsupportedEncodingException e)
        {

        }
    }

    @Override
    public String getParameter(final String name)
    {
        String[] strings = getParameterMap().get(name);
        if (strings != null)
        {
            return strings[0];
        }
        return null;
    }

    @Override
    public Map<String, String[]> getParameterMap()
    {
        if (allParameters == null)
        {
            allParameters = new TreeMap<String, String[]>();

    		Iterator iter = super.getParameterMap().keySet().iterator() ;

    		while(iter.hasNext())
    		{
    			String key = (String)iter.next() ;
    			String[] str = super.getParameterValues(key) ;

    			try
    			{
    				if (str != null)
    				{
    					for(int i=0;i<str.length;i++)
    					{
    						str[i] = new String(str[i].getBytes(charCode), "UTF-8") ;

    						String hstr = "" ;

    						for(int j=0;j<str[i].length();j++)
    						{
    							int index = ANSI.indexOf(str[i].charAt(j)) ;
    							if (index >= 0)
    							{
    								hstr = hstr + UTF8.charAt(index) ;
    							}
    							else
    							{
    								hstr = hstr + str[i].charAt(j) ;
    							}
    						}

    						str[i] = hstr ;
    					}
    				}

    			}
    			catch(Exception e)
    			{

    			}

    			allParameters.put(key, str) ;
    		}


            //allParameters.putAll(modifiableParameters);
        }

        return Collections.unmodifiableMap(allParameters);
    }

	/**
	 * @param c
	 * @return
	 */
	public static char convert(char c)
	{
		int index = ANSI.indexOf(c) ;
		if (index >= 0)
		{
			return UTF8.charAt(index) ;
		}
		else
		{
			return c ;
		}
	}

	@Override
    public Enumeration<String> getParameterNames()
    {
        return Collections.enumeration(getParameterMap().keySet());
    }

    @Override
    public String[] getParameterValues(final String name)
    {
        return getParameterMap().get(name);
    }

    public void setNoCache()
    {
    	nocache = true ;
    }

	@Override
	public String getHeader(String arg0) {

		if(nocache && arg0 != null)
		{
			if (arg0.equals("Cache-Control") || arg0.equals("Pragma"))
			{
				return "no-cache" ;
			}
		}
		// TODO 自動生成されたメソッド・スタブ
		return super.getHeader(arg0);
	}
}