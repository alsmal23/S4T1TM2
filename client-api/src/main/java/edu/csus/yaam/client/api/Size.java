package edu.csus.yaam.client.api;

import lombok.Data;
import lombok.Getter;
import lombok.NonNull;

import java.util.UUID;

/**
 * Created by paulp on 4/8/2018.
 */

@Data
public class Size
{
	protected final UUID uuid;
	protected final String label;
	protected final Project associatedProject;
	
}
