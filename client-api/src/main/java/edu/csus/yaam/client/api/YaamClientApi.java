package edu.csus.yaam.client.api;

import lombok.NonNull;
import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.DefaultAsyncHttpClientConfig;
import org.asynchttpclient.Dsl;
import org.asynchttpclient.uri.Uri;

/**
 * @author Paul M
 * @date 4/5/2018
 */
public class YaamClientApi
{
    protected Uri remoteHost;
	protected ClientAPICallback callback;

	private final AsyncHttpClient httpClient;

	/*
	The URL parameter host is used only for the host name.
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