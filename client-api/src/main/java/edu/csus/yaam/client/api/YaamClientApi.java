package edu.csus.yaam.client.api;

import edu.csus.yaam.client.api.modeldata.Project;
import edu.csus.yaam.client.api.modeldata.User;
import javafx.application.Platform;
import lombok.Getter;
import lombok.NonNull;
import org.asynchttpclient.*;
import org.asynchttpclient.uri.Uri;
import org.json.JSONObject;

import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author Paul M
 * @date 4/5/2018
 */
public class YaamClientApi
{
	@Getter private Uri remoteHost;
	@Getter private ClientAPICallback callback;
	
	@Getter private final AsyncHttpClient httpClient;
	
	//The users and projects maps store a mapping from the UUID of a user or project to the actual cached user or project
	private Map<UUID, User>    users;
	private Map<UUID, Project> projects;
	
	/*
	The Uri parameter host is used only for the host name.
	Callback must not be null.
	 */
	public YaamClientApi(@NonNull Uri host, @NonNull ClientAPICallback clientCallback)
	{
		remoteHost = new Uri("http", null, host.getHost(), host.getPort(), null, null);
		this.callback = clientCallback;
		
		users = new TreeMap<>();
		projects = new TreeMap<>();
		
		httpClient = Dsl.asyncHttpClient();
	}
	
	public void authenticate(@NonNull String userName, @NonNull String password)
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
				//WE NEED TO SPECIFY THE JSON PAYLOAD FORMAT
				JSONObject responseObject = new JSONObject(response.getResponseBody());
				String userUUIDString = responseObject.getString("uuid");
				UUID userUUID = UUID.fromString(userUUIDString);
				
				//do the callback
				Platform.runLater(() -> {
					callback.authenticationStatusChanged(ServerAuthenticationEvent.AUTHENTICATION_SUCESS, userUUID);
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
	
	//retrieves known Users and sends them to the ClientAPICalback
	public void retrieveKnownUsers()
	{
		//TODO: Implement
		new Thread(() -> {
			//
			//make blocking call that requests all users
			//
			
			//
			//Deserialize the Users
			//
			
			Platform.runLater(() -> {
				//send callback to the UI with actual user data
				callback.knownUsersSucessfullyRetrieved(new User[0]);
			});
		}).start();
		
	}
	
	//retrieves known Projects and sends them to the ClientAPICalback
	public void retrieveKnownProjects()
	{
		//TODO: Implement
		new Thread(() -> {
			//
			//make blocking call that requests all known projects
			//
			
			//
			//Deserialize the projects
			//
			
			Platform.runLater(() -> {
				//send callback to the UI with actual user data
				callback.projectsSucessfullyRetrieved(new Project[0]);
			});
		}).start();
		
	}
}