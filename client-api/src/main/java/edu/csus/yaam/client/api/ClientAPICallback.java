package edu.csus.yaam.client.api;

/**
 * Created by paulp on 4/8/2018.
 */
interface ClientAPICallback
{
	void didConnectToServerSucessfully();
	void failedToConnectToServer(String reason);
	void didAuthenticateSucessfully();
	void failedToAuthenticate(String reason);
	void serverConnectionLost();
	void knownUsersSucessfullyRetrieved(User[] knownUsers);//is called with a list of all the Users that have projects in common with you
	void projectsSucessfullyRetrieved(Project[] projects); //is called with a list of all the projects you are a member of
}
