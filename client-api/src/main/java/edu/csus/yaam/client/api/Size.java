package edu.csus.yaam.client.api;

import lombok.Getter;
import lombok.NonNull;

import java.util.UUID;

/**
 * Created by paulp on 4/8/2018.
 */
public class Size
{
	@Getter protected UUID uuid;
	@Getter protected String label;
	
	public Size(@NonNull UUID uuid, @NonNull String label)
	{
		this.uuid = uuid;
		this.label = label;
	}
}
