package net.explorviz.discovery.exceptions.mapper;

public class ResponseUtil {

	public static final int HTTP_STATUS_UNPROCESSABLE_ENTITY = 422;

	public static final String ERROR_INTERNAL_AGENT = "An internal agent error occured.";
	public static final String ERROR_NO_AGENT_TITLE = "Unknown agent.";
	public static final String ERROR_NO_AGENT_DETAIL = "The request contained an unknown agent. This shouldn't happen if you are using the frontend";

	public static final String ERROR_NO_AGENT_CONNECTION_TITLE = "No connection to agent.";
	public static final String ERROR_NO_AGENT_CONNECTION_DETAIL = "Backend couldn't reach agent, probably offline. Re-registration of agent is possible if it is restarted.";

	public static final String ERROR_AGENT_FLAG_DETAIL = "Agent couldn't inject necessary information into the execution command.";

	public static final String ERROR_NO_AGENT_CONNECTION_TIMEOUT = "Backend couldn't reach agent, because if Timeout or no connection. Re-registration of agent is possible if it is restarted.";

}
