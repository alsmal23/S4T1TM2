package edu.csus.yaam.client.api;

import lombok.Data;
import lombok.Getter;

import java.util.UUID;

/**
 * Created by paulp on 4/9/2018.
 */

abstract class Pursuit
{
	Project associatedProject;
	String  name;
	UUID    parentUUID; //Pursuit or Project
	UUID[]  tagUUIDs;
}
