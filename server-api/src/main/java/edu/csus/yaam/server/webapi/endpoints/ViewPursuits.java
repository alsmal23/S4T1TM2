package edu.csus.yaam.server.webapi.endpoints;

import edu.csus.yaam.server.db.SQLiteDatabase;
import edu.csus.yaam.server.webapi.endpoint.APIEndpoint;
import edu.csus.yaam.server.webapi.endpoint.Endpoint;
import edu.csus.yaam.server.webapi.endpoint.EndpointContext;
import edu.csus.yaam.server.webapi.endpoint.RequestMethod;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.UUID;
import org.json.JSONArray;
import org.json.JSONObject;
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

	/**
	 * table definition for convenience (Does this violate DRY? probably..)
	 * `Pursuit` (
	 * `uuid` CHAR(36),
	 * `project` CHAR(36) NOT NULL,
	 * `name` TEXT NOT NULL,
	 * `type` TEXT NOT NULL CHECK(`type` = 'TASK' OR `type` = 'SPRINT'),
	 * <p>
	 * PRIMARY KEY(`uuid`),
	 * FOREIGN KEY(`project`) REFERENCES `Project`(`uuid`)
	 */
	@Override
	public void handle(Request request, Response response, EndpointContext context)
	{
		UUID project = context.routeArgument("project");

		//uuid, project, name, type
		String sql = "SELECT * FROM  Pursuit WHERE project_uuid = ?";

		database.executeSync(connection ->
		{
			try (PreparedStatement statement = connection.prepareStatement(sql)) {
				statement.setString(1, project.toString());
				ResultSet rs = statement.executeQuery();

				JSONArray array = new JSONArray();
				while (rs.next()) {
					array.put(new JSONObject()
							.put("uuid", rs.getString("uuid"))
							.put("project", rs.getString("project_uuid"))
							.put("name", rs.getString("name"))
							.put("type", rs.getString("type")));
				}
				response.body(array.toString());
			}
		});
	}
}