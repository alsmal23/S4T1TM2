package edu.csus.yaam.client.api.modeldata;

import lombok.Data;
import lombok.Getter;

import java.util.UUID;

/**
 * Created by paulp on 4/9/2018.
 */

@Data
abstract class Pursuit
{
	@Getter protected final UUID    pursuitUUID;
	@Getter protected final String  name;
	@Getter protected final String  description;
	@Getter protected final UUID    parentUUID; //Pursuit or Project
	@Getter protected final UUID[]  tagUUIDs;
	@Getter protected final Project associatedProject;
}
