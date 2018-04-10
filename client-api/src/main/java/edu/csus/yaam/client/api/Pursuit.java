package edu.csus.yaam.client.api;

import lombok.Data;
import lombok.Getter;

import java.util.UUID;

/**
 * Created by paulp on 4/9/2018.
 */

public interface Pursuit
{
	Project associatedProject = null;
	UUID thisUUID = null;
	String name = null;
	UUID parentUUID = null; //Pursuit or Project
	Tag[] tags = null;
}
