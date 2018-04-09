package edu.csus.yaam.client.api;


import lombok.NonNull;

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
	public YaamClientApi(@NonNull String host, @NonNull ClientAPICallback clientCallback) throws MalformedURLException
	{
		this.callback = callback;
		remoteHost = new URL("http", host,80 ,"");
		
	}
}