package edu.csus.yaam.client.api;

import lombok.Getter;
import lombok.NonNull;

import java.util.TreeMap;
import java.util.UUID;

/**
 * Created by paulp on 4/9/2018.
 */
public class Project
{
	@Getter TreeMap<UUID, Pursuit>     pursuits;
	@Getter TreeMap<UUID, Member>      members;
	@Getter TreeMap<UUID, Tag>         tags;
	@Getter TreeMap<UUID, Size>        sizes;
	@Getter String name;
	@Getter String description;
	@Getter UUID   projectUUID;
	@Getter UUID   ownerUUID;
	@Getter YaamClientApi clientApi;
	
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
	}
	
	
}
