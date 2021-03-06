package edu.csus.yaam.client.api;

import edu.csus.yaam.client.api.modeldata.Project;
import edu.csus.yaam.client.api.modeldata.User;

import java.util.UUID;

/**
 * Created by paulp on 4/8/2018.
 *
 * When the YaamClientApi is constructed a class implementing ClientAPICallback must be passed in to recieve callbacks from requests
 */
public interface ClientAPICallback
{
	void serverStatusChanged(ServerConnectionEvent event, String failureReason);//If the connection is lost then an error description will be passed in failureReason otherwise it will be null
	void authenticationStatusChanged(ServerAuthenticationEvent event, UUID userUUID);//For events other than AUTHENTICATION_SUCESS loggedInAs contains null
	void knownUsersSucessfullyRetrieved(User[] knownUsers);//is called with a list of all the Users that have projects in common with you
	void projectsSucessfullyRetrieved(Project[] projects); //is called with a list of all the projects you are a member of
}
