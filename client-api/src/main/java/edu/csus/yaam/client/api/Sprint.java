package edu.csus.yaam.client.api;

import lombok.Data;
import lombok.Getter;

import java.time.Instant;
import java.util.UUID;

/**
 * Created by paulp on 4/9/2018.
 */

@Data
public class Sprint implements Pursuit
{
	@Getter protected final Instant dueDate;
	@Getter protected final UUID[]  taskUUIDs;
}
