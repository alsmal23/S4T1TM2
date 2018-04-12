package edu.csus.yaam.client.api;

import org.json.JSONArray;

import java.util.UUID;

/**
 * Created by paulp on 4/11/2018.
 * Utilities that the client API uses
 */
public class ClientAPIUtils
{
	public static UUID[] getUUIDsFromJSON(JSONArray UUIDarray)
	{
		UUID uuids[] = new UUID[UUIDarray.length()];
		for (int i= 0; i<uuids.length;i++)
		{
			uuids[i] = UUID.fromString(UUIDarray.getString(i));
		}
		return uuids;
	}
}
