package edu.csus.yaam.server.webapi;


import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

/**
 * The JsonBuilder class is a utility class for turning
 * any arbitrary java.sql.ResultSet into an org.json.JSONArray.
 * This arrays string representation is accessible
 * through the JsonBuilder.toString method.
 * Through the JsonBuilder.append method several
 * ResultsSets can be added to the Array
 */


public class JsonBuilder
{

	private JSONArray array;

	public JsonBuilder()
	{

		array = new JSONArray();
	}

	/**
	 *
	 * @param results The results set to add to JSONArray, each row will become one JSONObject
	 * @return returns the JsonBuilder so that append may be chained, jsonObjectreference.append(foo).append(bar);
	 * @throws SQLException
	 */
	public JsonBuilder append(ResultSet results) throws SQLException
	{
		int columnCount = results.getMetaData().getColumnCount();

		while (results.next())
		{
			JSONObject record = new JSONObject();
			for (int i = 1; i <= columnCount; i++)
			{
				switch (results.getMetaData().getColumnType(i))
				{
					/**
					 * all of these types correspond to the Java type String
					 */
					case Types.CHAR:
					case Types.VARCHAR:
					case Types.CLOB:
					case Types.NCHAR:
					case Types.NVARCHAR:
					case Types.LONGNVARCHAR:
						record.put(results.getMetaData().getColumnLabel(i),
								results.getString(i));
						break;
					/**
					 * these types correspond to the Java types, short, int, and long
					 * respectively, However as we only want a string representation
					 * for JSON, we use the largest of those types: long.
					 */
					case Types.INTEGER:
					case Types.TINYINT:
					case Types.SMALLINT:
						record.put(results.getMetaData().getColumnLabel(i), results.getLong(i));
						break;
					/**
					 * these types roughly correspond to the Java types, float and double respectively
					 * however, as we only need a string representation we use the largest of these
					 * types: double.
					 */
					case Types.REAL:
					case Types.DOUBLE:
					case Types.FLOAT:
					case Types.NUMERIC:
					case Types.DECIMAL:
						record.put(results.getMetaData().getColumnLabel(i), results.getDouble(i));
						break;
					/**
					 *
					 */
					case Types.NULL:
						record.put(results.getMetaData().getColumnLabel(i), JSONObject.NULL);
						break;

				}
			}
			array.put(record);
		}
		return this;
	}

	/**
	 * the toString method is used to return the JSONArrays string representation;
	 * //TODO this may not be the best way.
	 * @return
	 */
	public String toString()
	{
		return array.toString();
	}

	/**
	 * this method is to dump the currently stored results sets, it enables
	 * the use of one JsonBuilder reference for serializing several
	 * different results sets, that should not be in one array together.
	 * @return returns the JsonBuilder object
	 */
	public JsonBuilder dump()
	{
		array = new JSONArray();
		return this;
	}


}
