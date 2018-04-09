package edu.csus.yaam.server.webapi.endpoints;

import edu.csus.yaam.server.db.SQLiteDatabase;
import edu.csus.yaam.server.webapi.endpoint.APIEndpoint;
import edu.csus.yaam.server.webapi.endpoint.Endpoint;
import edu.csus.yaam.server.webapi.endpoint.EndpointContext;
import edu.csus.yaam.server.webapi.endpoint.RequestMethod;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import spark.Request;
import spark.Response;

@APIEndpoint(route = "route", method = RequestMethod.GET)
public class ViewProjects implements Endpoint
{
	SQLiteDatabase database;
	public ViewProjects (SQLiteDatabase database){this.database = database;}

	@Override
	public void handle(Request request, Response response, EndpointContext context)
	{
		String sql = "";//put query here
		try(Connection conn = database.connection();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet rs = statement.executeQuery())
		{

		} catch (SQLException e)
		{
			e.printStackTrace();
		}

	}
}
