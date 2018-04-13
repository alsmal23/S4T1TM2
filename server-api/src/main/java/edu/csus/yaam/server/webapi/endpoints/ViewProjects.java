package edu.csus.yaam.server.webapi.endpoints;

import edu.csus.yaam.server.db.SQLiteDatabase;
import edu.csus.yaam.server.webapi.JsonBuilder;
import edu.csus.yaam.server.webapi.endpoint.APIEndpoint;
import edu.csus.yaam.server.webapi.endpoint.Endpoint;
import edu.csus.yaam.server.webapi.endpoint.EndpointContext;
import edu.csus.yaam.server.webapi.endpoint.RequestMethod;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import spark.Request;
import spark.Response;

@APIEndpoint(route = "/user/projects", method = RequestMethod.GET)
public class ViewProjects implements Endpoint
{

	SQLiteDatabase database;
	PreparedStatement statement;

	public ViewProjects(SQLiteDatabase database) {this.database = database;}

	@Override
	public void handle(Request request, Response response, EndpointContext context)
	{
		UUID user = UUID.fromString(request.headers("Authorize"));
		String sql = "SELECT project_uuid from Permission WHERE user_uuid = ?";
		database.executeSync(connection ->
				{
					statement = connection.prepareStatement(sql);
					statement.setString(1, user.toString());
					ResultSet rs = statement.executeQuery();
					JsonBuilder builder = new JsonBuilder();
					builder.append(rs);
					response.body(builder.toString());
				}
		);
	}
}
