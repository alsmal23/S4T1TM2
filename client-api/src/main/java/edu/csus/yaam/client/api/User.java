package edu.csus.yaam.client.api;

import lombok.Data;
import lombok.Getter;
import lombok.NonNull;

import java.util.UUID;

/**
 * Created by paulp on 4/8/2018.
 */
@Data public class User
{
	@Getter protected UUID uuid;
	@Getter protected String userName;
	@Getter protected String displayName;
	@Getter protected String SSN; //we need it just trust me :)
	
	public User(@NonNull UUID uuid, @NonNull String userName, @NonNull String displayName, @NonNull String SSN)
	{
		this.uuid = uuid;
		this.userName = userName;
		this.displayName = displayName;
		this.SSN = SSN;
	}
}