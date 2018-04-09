package edu.csus.yaam.client.api;

import lombok.Data;
import lombok.Getter;
import lombok.NonNull;

import java.util.UUID;

/**
 * Created by paulp on 4/8/2018.
 */

@Data
public class User
{
	protected final UUID uuid;
	protected final String userName;
	protected final String displayName;
	protected final String SSN; //we need it just trust me :)
	
}
