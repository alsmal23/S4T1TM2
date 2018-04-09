package edu.csus.yaam.client.api;

import lombok.Data;
import lombok.Getter;

import java.util.UUID;

/**
 * Created by paulp on 4/8/2018.
 */
@Data public class User
{
	@Getter protected UUID userID;
	@Getter protected String userName;
	@Getter protected String displayName;
	@Getter protected String SSN; //we need it just trust me :)
	
	public User(UUID userID, String userName, String displayName, String SSN)
	{
		this.userID = userID;
		this.userName = userName;
		this.displayName = displayName;
		this.SSN = SSN;
	}
}
