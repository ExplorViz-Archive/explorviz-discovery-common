package net.explorviz.discovery.exceptions.procezz;

import net.explorviz.discovery.model.Procezz;

public class ProcezzMonitoringSettingsException extends Exception {

  private static final long serialVersionUID = 1L;

  private Procezz faultyProcezz;

  public ProcezzMonitoringSettingsException(final String message, final Throwable cause,
      final Procezz p) {
    super(message, cause);
    this.faultyProcezz = p;
  }

  public ProcezzMonitoringSettingsException(final String message, final Throwable cause) {
    super(message, cause);
  }

  public Procezz getFaultyProcezz() {
    return faultyProcezz;
  }

}
