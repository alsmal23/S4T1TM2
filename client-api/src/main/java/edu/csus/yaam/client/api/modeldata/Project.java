package edu.csus.yaam.client.api.modeldata;

import edu.csus.yaam.client.api.YaamClientApi;
import lombok.Getter;
import lombok.NonNull;

import java.util.*;

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
	@Getter
	YaamClientApi clientApi;
	
	Set<UUID> cachedProjectComponents;
	
	public Project(@NonNull String name, @NonNull String description, @NonNull UUID projectUUID, @NonNull UUID ownerUUID, @NonNull YaamClientApi clientApi)
	{
		this.name = name;
		this.description = description;
		this.projectUUID = projectUUID;
		this.ownerUUID = ownerUUID;
		this.clientApi = clientApi;
		
		//initalize the maps to their original values
		pursuits     = new TreeMap<>();
		members      = new TreeMap<>();
		tags         = new TreeMap<>();
		sizes        = new TreeMap<>();
		
		//cached project components stores all the project components that are cached
		cachedProjectComponents = new TreeSet<>();
	}
	
	//retrieves a list of all pusuits that are "top level" (their direct parent is the Project its self)
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
		//TODO: download and cache the Pursuit associated with the passed pursuitUUID
		
	}
}
