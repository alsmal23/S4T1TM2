package edu.csus.yaam.client.api.modeldata;

import edu.csus.yaam.client.api.ClientAPICallback;
import edu.csus.yaam.client.api.ClientAPIUtils;
import edu.csus.yaam.client.api.YaamClientApi;
import lombok.Getter;
import lombok.NonNull;
import org.asynchttpclient.Dsl;
import org.asynchttpclient.Request;
import org.asynchttpclient.RequestBuilder;
import org.asynchttpclient.Response;
import org.json.JSONArray;
import org.json.JSONObject;

import java.time.Instant;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Created by paulp on 4/9/2018.
 */
public class Project
{
	
	@Getter Map<UUID, Pursuit>     pursuits;
	@Getter Map<UUID, Member>      members;
	@Getter Map<UUID, Tag>         tags;
	@Getter Map<UUID, Size>        sizes;
	@Getter String name;
	@Getter String description;
	@Getter UUID   projectUUID;
	@Getter UUID   ownerUUID;
	@Getter YaamClientApi clientApi;
	
	Set<UUID> cachedProjectComponents;
	
	public Project(@NonNull String name, @NonNull String description, @NonNull UUID projectUUID, @NonNull UUID ownerUUID, @NonNull YaamClientApi clientApi)
	{
		this.name = name;
		this.description = description;
		this.projectUUID = projectUUID;
		this.ownerUUID = ownerUUID;
		this.clientApi = clientApi;
		
		//initialize the maps to their original values
		pursuits     = new TreeMap<>();
		members      = new TreeMap<>();
		tags         = new TreeMap<>();
		sizes        = new TreeMap<>();
		
		//cached project components stores all the project components that are cached
		cachedProjectComponents = new TreeSet<>();
	}
	
	//retrieves a list of all pursuits that are "top level" (their direct parent is the Project its self)
	public void retrieveTopLevelPursuits()
	{
		
		
	}
	
	//This is a potentially BLOCKING CALL that retrieves a pursuit from its cache if it is cached or retrieves it from the server BY BLOCKING CALL
	private Pursuit getPursuitByUUID(UUID pursuitUUID)
	{
		boolean cached;
		//determine if the
		synchronized (cachedProjectComponents)
		{
			cached = cachedProjectComponents.contains(pursuitUUID);
		}
		
		if (!cached) downloadPursuit(pursuitUUID);
		
		return pursuits.get(pursuitUUID);
	}
	
	//BLOCKING CALL that downloads a pursuit from the server and adds it to the cache
	private void downloadPursuit(UUID pursuitUUID)
	{
		//build pursuit request
		Request pursuitRequest = Dsl.post(getClientApi().getRemoteHost().getBaseUrl().concat("/pursuit/"+pursuitUUID.toString())).build();
		
		Future<Response> futureResponse = getClientApi().getHttpClient().executeRequest(pursuitRequest);
		
		try
		{
			Response response = futureResponse.get();
			
			//parse JSON object
			//WE NEED TO SPECIFY THE JSON PAYLOAD FORMAT
			JSONObject pursuitJSON = new JSONObject(response.getResponseBody());
			Pursuit resultPursuit;
			
			//get the information about all pursuits
			UUID returnedUUID = UUID.fromString(pursuitJSON.getString("uuid"));
			String name = pursuitJSON.getString("name");
			String description = pursuitJSON.getString("desc");
			UUID parent = UUID.fromString(pursuitJSON.getString("parent"));
			//get the tags that the pursuit has applied to it
			JSONArray tagsArray = pursuitJSON.getJSONArray("tags");
			UUID tagUUIDs[] = ClientAPIUtils.getUUIDsFromJSON(pursuitJSON.getJSONArray("tags"));
			
			//determine if it is a Sprint or Task
			String type = pursuitJSON.getString("type");
			if (type.equals("sprint"))
			{
				//get data specific to sprints
				Instant dueTime = Instant.ofEpochSecond(pursuitJSON.getLong("duetime"));
				UUID taskUUIDs[] = ClientAPIUtils.getUUIDsFromJSON(pursuitJSON.getJSONArray("tasks"));
				
				resultPursuit = new Sprint(returnedUUID,name,description,parent,tagUUIDs,dueTime,taskUUIDs,this);
			}
			else if (type.equals("task"))
			{
				//get data specific to tasks
				UUID size = UUID.fromString(pursuitJSON.getString("size"));
				UUID assignee = UUID.fromString(pursuitJSON.getString("assignedto"));
				//get the work sessions
				JSONArray workSessionsJSON = pursuitJSON.getJSONArray("worksess");
				WorkSession workSessions[] = new WorkSession[workSessionsJSON.length()];
				for (int i = 0; i < workSessions.length; i++)
				{
					//get the work session info
					JSONObject currentWS = workSessionsJSON.getJSONObject(i);
					UUID worker        = UUID.fromString(currentWS.getString("worker"));
					Instant startTime  = Instant.ofEpochSecond(currentWS.getLong("start"));
					Instant endTime    = Instant.ofEpochSecond(currentWS.getLong("end"));
					
					workSessions[i] = new WorkSession(worker,startTime,endTime);
				}
				
				resultPursuit = new Task(returnedUUID,name,description,parent,tagUUIDs,size,assignee,workSessions,this);
			}
			else
			{
				System.err.println("Could not download pursuit: contains invalid type \"" + type + "\"");
				resultPursuit = null;
			}
			
			//remove old copy of Pursuit(if it exists) and add the recently downloaded version
			pursuits.remove(resultPursuit.getPursuitUUID());
			pursuits.put(resultPursuit.getPursuitUUID(),resultPursuit);
			
		}
		catch (InterruptedException | ExecutionException e)
		{
			System.err.println("Could not download pursuit:");
			e.printStackTrace();
			
		}
		
	}
}
