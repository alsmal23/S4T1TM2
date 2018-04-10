package edu.csus.yaam.client.api;

import lombok.NonNull;
import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.DefaultAsyncHttpClientConfig;
import org.asynchttpclient.Dsl;
import org.asynchttpclient.uri.Uri;

import java.util.TreeMap;
import java.util.UUID;

/**
 * @author Paul M
 * @date 4/5/2018
 */
public class YaamClientApi
{
    protected Uri remoteHost;
	protected ClientAPICallback callback;

	private final AsyncHttpClient httpClient;
	
	//I choose TreeMaps to store the users and projects because I want to be able to search for users and projects by UUID and TreeMaps are efficient at both searches and insetions.
	//TreeMaps are my favorite Map
	TreeMap<UUID, User> users;
	
	
	/*
	The Uri parameter host is used only for the host name.
	Callback must not be null.
	 */
	public YaamClientApi(@NonNull Uri host, @NonNull ClientAPICallback clientCallback)
    {
        remoteHost = host;
		this.callback = clientCallback;

		httpClient = Dsl.asyncHttpClient(new DefaultAsyncHttpClientConfig.Builder()
                .build()
        );
	}
}