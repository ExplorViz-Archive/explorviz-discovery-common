package net.explorviz.discovery.exceptions.agent;

public class AgentNotFoundException extends Exception {

  private static final long serialVersionUID = 1L;

  public AgentNotFoundException(final String message, final Throwable cause) {
    super(message, cause);
  }

}
