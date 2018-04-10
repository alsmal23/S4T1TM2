package edu.csus.yaam.client.api;

import lombok.Data;
import lombok.Getter;

import java.time.Instant;
import java.util.UUID;

/**
 * Created by paulp on 4/9/2018.
 */

@Data
public class Sprint extends Pursuit
{
	@Getter protected final Instant dueDate;
	@Getter protected final UUID[]  taskUUIDs;
	
	public Sprint(Instant dueDate, String name, UUID parentUUID, UUID[] tagUUIDs, UUID[] taskUUIDs, Project associatedProject)
	{
		this.dueDate    = dueDate;
		this.name       = name;
		this.parentUUID = parentUUID;
		this.tagUUIDs   = tagUUIDs;
		this.taskUUIDs  = taskUUIDs;
		this.associatedProject = associatedProject;
	}
}
