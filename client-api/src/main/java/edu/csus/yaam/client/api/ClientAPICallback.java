package edu.csus.yaam.client.api;

/**
 * Created by paulp on 4/8/2018.
 */
public interface ClientAPICallback
{
	void serverStatusChanged(ServerConnectionEvent event, String failureReason);//If the connection is lost then an error description will be passed in failureReason otherwise it will be null
	void authenticationStatusChanged(ServerAuthenticationEvent event, User loggedInAs);//For events other than AUTHENTICATION_SUCESS loggedInAs contains null
	void knownUsersSucessfullyRetrieved(User[] knownUsers);//is called with a list of all the Users that have projects in common with you
	void projectsSucessfullyRetrieved(Project[] projects); //is called with a list of all the projects you are a member of
}
