package net.explorviz.discovery.exceptions.mapper.procezz;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.explorviz.discovery.exceptions.mapper.ResponseUtil;
import net.explorviz.discovery.exceptions.procezz.ProcezzMonitoringSettingsException;
import net.explorviz.discovery.model.helper.ErrorObjectHelper;

public class ProcezzMonitoringSettingsMapper implements ExceptionMapper<ProcezzMonitoringSettingsException> {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProcezzMonitoringSettingsMapper.class);

	@Override
	public Response toResponse(final ProcezzMonitoringSettingsException exception) {

		LOGGER.error("Error occured while patching procezz. Error: {}", exception.toString());

		if (exception.getFaultyProcezz() != null) {
			exception.getFaultyProcezz().setErrorOccured(true);
			exception.getFaultyProcezz().setErrorMessage(exception.toString());
		}

		final byte[] serializedErrorArray = ErrorObjectHelper.getInstance().createSerializedErrorArray(
				ResponseUtil.HTTP_STATUS_UNPROCESSABLE_ENTITY, ResponseUtil.ERROR_INTERNAL_AGENT,
				exception.getMessage());

		// final Error error = new Error();
		// error.setCode("422");
		// error.setTitle(ResponseUtil.ERROR_INTERNAL_TITLE);
		// error.setDetail(exception.toString());

		// final String errorObject =
		// ErrorObjectHelper.getInstance().createErrorObjectString(422,
		// ResponseUtil.ERROR_INTERNAL_TITLE, exception.toString());

		// final String test = "{\"errors\":[{\"code\":\"422\"}]}";

		return Response.status(422).entity(serializedErrorArray).build();
	}

}
