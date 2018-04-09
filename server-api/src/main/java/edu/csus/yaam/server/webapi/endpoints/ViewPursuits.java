package edu.csus.yaam.server.webapi.endpoints;

import edu.csus.yaam.server.db.SQLiteDatabase;
import edu.csus.yaam.server.webapi.endpoint.APIEndpoint;
import edu.csus.yaam.server.webapi.endpoint.Endpoint;
import edu.csus.yaam.server.webapi.endpoint.EndpointContext;
import edu.csus.yaam.server.webapi.endpoint.RequestMethod;

import java.sql.*;
import java.util.UUID;

import org.json.JSONObject;
import org.json.JSONWriter;
import spark.Request;
import spark.Response;

/**
 * @author Ryan R
 * @date 4/1/2018
 */
@APIEndpoint(route = "/project/{project:UUID}/pursuits", method = RequestMethod.GET)
public class ViewPursuits implements Endpoint
{
	SQLiteDatabase database;

	public ViewPursuits(SQLiteDatabase database) { this.database = database;}

	@Override
	public void handle(Request request, Response response, EndpointContext context)
	{//uuid, project, name, type
		
		String sql = "SELECT * FROM  `Pursuit`";
		UUID project = context.routeArgument("project");

			//this is not working, the ResultSet comes back empty from the database,
			//I inserted

		try(Connection conn = database.getConnection();
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(sql)
			)
		{
//			PreparedStatement statement = database.getConnection().prepareStatement(sql);
//			statement.setString(1, project.toString());
//			ResultSet rs = statement.executeQuery();

			StringBuilder returns = new StringBuilder();
			JSONWriter writer = new JSONWriter(returns);
			returns.append(project.toString()+"\t"+"pre jsonify test "+rs.getWarnings()+" "+rs.toString());

			while (rs.next())
			{
				returns.append("succesful got open result set");
				writer.value(new JSONObject()
						.put("uuid", rs.getString("uuid"))
						.put("project", rs.getString("project"))
						.put("name", rs.getString("name"))
						.put("type", rs.getString("type")));
			}
			response.body(returns.toString());
		} catch (SQLException e)
		{
			response.body(e.toString());
		}//on exception putting exceptions to string into response
	}
}