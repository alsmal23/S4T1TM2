package edu.csus.yaam.client.api;

import lombok.Getter;
import lombok.NonNull;

import java.awt.Color;
import java.util.UUID;

/**
 * Created by paulp on 4/8/2018.
 */
public class Tag
{
	@Getter protected UUID uuid;
	@Getter protected String label;
	@Getter protected Color tagColor;
	
	public Tag(@NonNull UUID uuid, @NonNull String label, @NonNull Color tagColor)
	{
		this.uuid = uuid;
		this.label = label;
		this.tagColor = tagColor;
	}
}
