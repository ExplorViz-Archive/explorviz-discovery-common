package net.explorviz.discovery.exceptions.mapper;

public class ResponseUtil {

	public static final int HTTP_STATUS_UNPROCESSABLE_ENTITY = 422;

	public static final String ERROR_INTERNAL_AGENT = "An internal agent error occured.";
	public static final String ERROR_NO_AGENT_TITLE = "Unknown agent.";
	public static final String ERROR_NO_AGENT_DETAIL = "The request contained an unknown agent. This shouldn't happen if you are using the frontend.";

	public static final String ERROR_NO_AGENT_CONNECTION_TITLE = "No connection to agent.";
	public static final String ERROR_NO_AGENT_CONNECTION_DETAIL = "Backend couldn't reach agent, probably offline. Re-registration of agent is possible if it is restarted.";

	public static final String ERROR_AGENT_FLAG_DETAIL = "Agent couldn't inject necessary information into the execution command. Validate User- (if not declared: OS) execution command";

	public static final String ERROR_NO_AGENT_CONNECTION_TIMEOUT = "Backend couldn't reach agent, because if Timeout or no connection. Re-registration of agent is possible if it is restarted.";

	public static final String ERROR_PROCEZZ_START = "Agent couldn't start Procezz. Check User- (if not declared: OS) Exec CMD.";
	public static final String ERROR_PROCEZZ_START_NOT_FOUND = "Agent started Procezz, but couldn't find it in new procezzList from OS. Check User- (if not declared: OS) Exec CMD. The execution command might be wrong";

	public static final String ERROR_PROCEZZ_STOP = "Agent couldn't stop Procezz.";

	public static final String ERROR_PROCEZZ_FLAG_NOT_FOUND = "Agent couldn't find a procezz for the passed agent flag.";
	public static final String ERROR_PROCEZZ_ID_NOT_FOUND = "Agent couldn't find a procezz for the passed internal Id. This should not happen";

}
