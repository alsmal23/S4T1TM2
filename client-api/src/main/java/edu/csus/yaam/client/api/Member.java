package edu.csus.yaam.client.api;

import lombok.Data;
import lombok.Getter;

/**
 * Created by paulp on 4/9/2018.
 */

@Data
public class Member
{
	protected final User user;
	//I am using a regular array because the permissions should not be mutable.
	protected final String[] permissions;
	
}
