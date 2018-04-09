package edu.csus.yaam.client.api;


import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author Paul M
 * @date 4/5/2018
 */
public class YaamClientApi
{
	protected ClientAPICallback callback;
	protected URL remoteHost;
	
	/*
	The URL parameter host is used only for the host name.
	Callback must not be null.
	 */
	public YaamClientApi(String host,  ClientAPICallback clientCallback) throws MalformedURLException
	{
		//make sure that the callback if not null
		if(callback==null) throw new NullPointerException("Client-API callback must not be null");
		this.callback = callback;
		
		remoteHost = new URL("http", host,80 ,"");
	}
}