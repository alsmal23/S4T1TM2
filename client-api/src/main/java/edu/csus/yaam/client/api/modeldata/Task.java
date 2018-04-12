package edu.csus.yaam.client.api.modeldata;

import lombok.Getter;
import lombok.NonNull;

import java.util.UUID;

/**
 * Created by paulp on 4/10/2018.
 */

public class Task extends Pursuit
{
	@Getter WorkSession[] workSessions;
	@Getter UUID sizeUUID;
	@Getter UUID assigneeUUID;
	
	public Task(@NonNull UUID pursuitUUID, @NonNull String name, @NonNull String description, @NonNull UUID parentUUID, @NonNull UUID[] tagUUIDs,
				@NonNull UUID sizeUUID, @NonNull UUID assigneeUUID, @NonNull WorkSession[] workSessions, @NonNull Project associatedProject)
	{
		super(pursuitUUID, name,description,parentUUID,tagUUIDs,associatedProject);
		this.workSessions = workSessions;
		this.sizeUUID = sizeUUID;
		this.assigneeUUID = assigneeUUID;
	}
	
}
