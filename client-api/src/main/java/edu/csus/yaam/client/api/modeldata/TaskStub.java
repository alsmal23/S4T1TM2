package edu.csus.yaam.client.api.modeldata;

import lombok.Getter;
import lombok.NonNull;

import java.util.UUID;

/**
 * Created by paulp on 4/10/2018.
 * TaskStub stores a task WITHOUT its WorkSessions.
 * The YaamClientAPI contains a method to load all the Sprints and TaskStubs.
 * Another method can be used to load the full Task including the WorkSessions.
 * This is done so that the client doesn't waste bandwidth by loading the work history of all tasks.
 */
public class TaskStub extends Pursuit
{
	
	@Getter UUID sizeUUID;
	@Getter UUID assigneeUUID;
	
	public TaskStub(@NonNull String name, @NonNull String description, @NonNull UUID parentUUID, @NonNull UUID[] tagUUIDs,
					@NonNull UUID sizeUUID, @NonNull UUID assigneeUUID, @NonNull Project associatedProject)
	{
		super(name,description,parentUUID,tagUUIDs,associatedProject);
		this.sizeUUID = sizeUUID;
		this.assigneeUUID = assigneeUUID;
	}
}
