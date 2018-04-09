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
}
