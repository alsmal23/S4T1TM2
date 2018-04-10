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
	protected final Instant startTime;
	protected final Instant endTime;
	
	public Duration getWorkSessionDuration()
	{
		return Duration.between(startTime, endTime);
	}
	
}
