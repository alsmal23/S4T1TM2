package edu.csus.yaam.client.api;

import lombok.Data;
import lombok.Getter;
import lombok.NonNull;

import java.util.UUID;

/**
 * Created by paulp on 4/10/2018.
 */

public class Task extends TaskStub
{
	@Getter WorkSession[] workSessions;
	
	public Task(@NonNull String name, @NonNull String description, @NonNull UUID parentUUID, @NonNull UUID[] tagUUIDs,
				@NonNull UUID sizeUUID, @NonNull UUID assigneeUUID, @NonNull WorkSession[] workSessions, @NonNull Project associatedProject)
	{
		super(name,description,parentUUID,tagUUIDs,sizeUUID,assigneeUUID,associatedProject);
		this.workSessions = workSessions;
	}
	
}
