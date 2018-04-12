package edu.csus.yaam.client.api;

import edu.csus.yaam.client.api.modeldata.Project;
import edu.csus.yaam.client.api.modeldata.User;
import javafx.application.Platform;
import lombok.Getter;
import lombok.NonNull;
import org.asynchttpclient.*;
import org.asynchttpclient.uri.Uri;
import org.json.JSONObject;

import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

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
	Map<UUID, User> users;
	Map<UUID, Project> projects;
	
	
	/*
	The Uri parameter host is used only for the host name.
	Callback must not be null.
	 */
	public YaamClientApi(@NonNull Uri host, @NonNull ClientAPICallback clientCallback)
	{
		remoteHost = host;
		this.callback = clientCallback;
		
		users = new TreeMap<>();
		projects = new TreeMap<>();
		
		httpClient = Dsl.asyncHttpClient();
	}
	
	public void authenticate(String userName, String password)
	{
		new Thread(() ->
		{
			//create JSON payload
			//WE NEED TO SPECIFY THE JSON PAYLOAD FORMAT
			JSONObject object = new JSONObject();
			object.append("uname", userName);
			object.append("passwd", password);
			
			//build a request with a JSON payload
			Request authRequest = Dsl.post(remoteHost.getBaseUrl().concat("/auth")).setBody(object.toString()).build();
			
			//get a response
			Future<Response> futureResponse = httpClient.executeRequest(authRequest);
			try
			{
				//blocks unitl the response has been received
				Response response = futureResponse.get();
				
				//get the user's UUID
				JSONObject responseObject = new JSONObject(response.getResponseBody());
				String userUUIDString = responseObject.getString("uuid");
				UUID userUUID = UUID.fromString(userUUIDString);
				
				//get the logged in user
				User loggedInUser = retreieveUserByUUID(userUUID);
				
				//do the callback
				Platform.runLater(() -> {
					callback.authenticationStatusChanged(ServerAuthenticationEvent.AUTHENTICATION_SUCESS, loggedInUser);
				});
				
			}
			catch (InterruptedException | ExecutionException e)
			{
				System.err.println("Could not authenticate:");
				e.printStackTrace();
				Platform.runLater(() -> {
					//call the callback for the UI
					callback.authenticationStatusChanged(ServerAuthenticationEvent.AUTHENTICATION_FAILED, null);
				});
			}
		}).start();
	}
	
	public User retreieveUserByUUID(UUID userUUID)
	{
		//TODO: IMPLEMENT
		return null;
	}
}