package edu.csus.yaam.client.api;

import lombok.Data;
import lombok.Getter;
import lombok.NonNull;

import java.awt.Color;
import java.util.UUID;

/**
 * Created by paulp on 4/8/2018.
 */

@Data
public class Tag
{
	protected final UUID uuid;
	protected final String label;
	protected final Color tagColor;
	protected final YaamClientApi clientApi;
}
