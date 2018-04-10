package edu.csus.yaam.client.api;

import lombok.Data;

import java.time.Duration;
import java.time.Instant;

/**
 * Created by paulp on 4/9/2018.
 */

@Data
public class WorkSession
{
	protected final User worker;
	protected final Instant startTime;
	protected final Instant endTime;
	protected final YaamClientApi clientApi;
	
	public Duration getWorkSessionDuration()
	{
		return Duration.between(startTime, endTime);
	}
}
