package edu.csus.yaam.client.api;

import lombok.Data;
import lombok.Getter;
import lombok.NonNull;

import java.util.UUID;

/**
 * Created by paulp on 4/10/2018.
 */

public class Task extends Pursuit
{
	@Getter UUID sizeUUID;
	@Getter UUID assigneeUUID;
	@Getter WorkSession[] workSessions;
	
	public Task(@NonNull String name, @NonNull String description, @NonNull UUID parentUUID, @NonNull UUID[] tagUUIDs,
				@NonNull UUID sizeUUID, @NonNull UUID assigneeUUID, @NonNull WorkSession[] workSessions, @NonNull Project associatedProject)
	{
		super(name, description, parentUUID, tagUUIDs, associatedProject);
		this.sizeUUID = sizeUUID;
		this.assigneeUUID = assigneeUUID;
		this.workSessions = workSessions;
	}
	
}
