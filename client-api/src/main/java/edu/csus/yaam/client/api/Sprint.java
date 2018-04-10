package edu.csus.yaam.client.api;

import lombok.Data;
import lombok.Getter;
import lombok.NonNull;

import java.time.Instant;
import java.util.UUID;

/**
 * Created by paulp on 4/9/2018.
 */

public class Sprint extends Pursuit
{
	@Getter protected final Instant dueDate;
	@Getter protected final UUID[]  taskUUIDs;
	
	public Sprint(@NonNull String name, @NonNull String description, @NonNull UUID parentUUID, @NonNull UUID[] tagUUIDs,
				  @NonNull Instant dueDate, @NonNull UUID[] taskUUIDs, @NonNull Project associatedProject)
	{
		super(name, description, parentUUID, tagUUIDs, associatedProject);
		this.dueDate     = dueDate;
		this.taskUUIDs   = taskUUIDs;
	}
}
