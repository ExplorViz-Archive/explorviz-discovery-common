package net.explorviz.discovery.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;

import net.explorviz.discovery.model.Process;

public class ClientService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ClientService.class);
	private static final int HTTP_STATUS_CREATED = 201;

	public boolean postProcessList(final byte[] processListPayload) {
		return doPost(processListPayload, "http://localhost:8082/process");
	}

	public boolean postProcess(final byte[] processPayload) {
		return doPost(processPayload, "http://localhost:8082/process");
	}

	public boolean postProcess(final Process process) {
		return doPost(process, "http://localhost:8082/process");
	}

	public boolean doPost(final byte[] payload, final String url) {
		final Client client = Client.create();
		final WebResource webResource = client.resource(url);

		ClientResponse response;

		try {
			response = webResource.type("application/vnd.api+json").post(ClientResponse.class, payload);
		} catch (UniformInterfaceException | ClientHandlerException e) {
			if (LOGGER.isWarnEnabled()) {
				LOGGER.warn(
						"Connection to backend failed, probably not online or wrong IP. Check IP in WEB-INF/classes/explorviz.properties.",
						e.toString());
			}
			return false;
		}

		if (response.getStatus() != HTTP_STATUS_CREATED) {
			if (LOGGER.isWarnEnabled()) {
				LOGGER.warn("Failed : HTTP error code : " + response.getStatus());
			}
			return false;
		}

		return true;
	}

	private boolean doPost(final Process process, final String url) {
		final Client client = Client.create();
		final WebResource webResource = client.resource(url);

		ClientResponse response;

		try {
			response = webResource.type("application/vnd.api+json").post(ClientResponse.class, process);
		} catch (UniformInterfaceException | ClientHandlerException e) {
			if (LOGGER.isWarnEnabled()) {
				LOGGER.warn(
						"Connection to backend failed, probably not online or wrong IP. Check IP in WEB-INF/classes/explorviz.properties.",
						e.toString());
			}
			return false;
		}

		if (response.getStatus() != HTTP_STATUS_CREATED) {
			if (LOGGER.isWarnEnabled()) {
				LOGGER.warn("Failed : HTTP error code : " + response.getStatus());
			}
			return false;
		}

		return true;
	}

}
