package edu.csus.yaam.client.api.modeldata;

import lombok.Data;

import java.util.UUID;

/**
 * Created by paulp on 4/9/2018.
 */

@Data
public class Member
{
	protected final UUID userUUID;
	//I am using a regular array because the permissions should not be mutable.
	//If the user wants to change another user's permissions they should use the appropriate method in the clientAPI
	protected final String[] permissions;
	protected final Project associatedProject;
	
	public User getUser()
	{
		return associatedProject.getClientApi().getUsers().get(userUUID);
	}
}
