package edu.csus.yaam.client.api;

import lombok.Data;

import java.time.Duration;
import java.time.Instant;
import java.util.UUID;

/**
 * Created by paulp on 4/9/2018.
 */

@Data
public class WorkSession
{
	protected final UUID    workerUUID;
	protected final UUID    taskUUID;
	protected final Instant startTime;
	protected final Instant endTime;
	protected final Project associatedProject;
	
	public Duration getWorkSessionDuration()
	{
		return Duration.between(startTime, endTime);
	}
}
