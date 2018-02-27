package net.explorviz.discovery.exceptions.mapper;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.explorviz.discovery.exceptions.GenericNoConnectionException;
import net.explorviz.discovery.model.helper.ErrorObjectHelper;

public class GenericNoConnectionMapper implements ExceptionMapper<GenericNoConnectionException> {

	private static final Logger LOGGER = LoggerFactory.getLogger(GenericNoConnectionMapper.class);

	@Override
	public Response toResponse(final GenericNoConnectionException exception) {

		LOGGER.error("Error occured, no Connection. Error: {}", exception.getMessage());

		final byte[] errorObject = ErrorObjectHelper.getInstance().createSerializedErrorArray(
				Response.Status.SERVICE_UNAVAILABLE.getStatusCode(), ResponseUtil.ERROR_NO_CONNECTION_TITLE,
				exception.getMessage());
		return Response.status(422).entity(errorObject).build();
	}

}
