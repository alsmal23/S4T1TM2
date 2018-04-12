package edu.csus.yaam.client.api;

import edu.csus.yaam.client.api.modeldata.Project;
import edu.csus.yaam.client.api.modeldata.User;
import javafx.application.Platform;
import lombok.Getter;
import lombok.NonNull;
import org.asynchttpclient.*;
import org.asynchttpclient.uri.Uri;

import java.util.Map;
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
	@Getter Map<UUID, User> users;
	@Getter Map<UUID, Project> projects;
	
	
	/*
	The Uri parameter host is used only for the host name.
	Callback must not be null.
	 */
	public YaamClientApi(@NonNull Uri host, @NonNull ClientAPICallback clientCallback)
    {
        remoteHost = host;
		this.callback = clientCallback;
		
		users    = new TreeMap<>();
		projects = new TreeMap<>();

		httpClient = Dsl.asyncHttpClient(new DefaultAsyncHttpClientConfig.Builder()
                .build()
        );
	}
	
	public void authenticate(String userName, String password)
	{
		new Thread(() -> {
			//call blocking authentication methods here
			//TODO: Implement authentication with actal server calls.  Currently the documentation RE how to authenticate is incomplete.
			
			
			Platform.runLater(() -> {
				//call the callback for the UI
				callback.authenticationStatusChanged(ServerAuthenticationEvent.AUTHENTICATION_FAILED, null);
			});
		}).start();
	}
}