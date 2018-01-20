package net.explorviz.discovery.services;

import javax.ws.rs.ProcessingException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.UniformInterfaceException;

import net.explorviz.discovery.model.Process;

public class ClientService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ClientService.class);
	private static final int HTTP_STATUS_CREATED = 201;
	private static final String MEDIA_TYPE = "application/vnd.api+json";

	private ClientBuilder clientBuilder = ClientBuilder.newBuilder();

	public <T> void registerProvider(final MessageBodyReader<T> providerReader) {
		this.clientBuilder = clientBuilder.register(providerReader);
	}

	public <T> void registerProviderWriter(final MessageBodyWriter<T> providerWriter) {
		this.clientBuilder = clientBuilder.register(providerWriter);
	}

	public boolean postProcess(final Process process) {
		return doPost(process, "http://localhost:8082/process");
	}

	public <T> boolean doPost(final Entity<?> t, final String url) {
		final Client client = this.clientBuilder.build();
		final WebTarget webTarget = client.target(url);

		Response response;

		try {
			response = webTarget.request(MEDIA_TYPE).post(t);
		} catch (final ProcessingException e) {
			if (LOGGER.isWarnEnabled()) {
				LOGGER.warn(
						"Connection to {} failed, probably not online or wrong IP. Check IP in WEB-INF/classes/explorviz.properties. Error Message: {}",
						url, e.toString());
			}
			return false;
		}

		if (response.getStatus() != HTTP_STATUS_CREATED) {
			if (LOGGER.isWarnEnabled()) {
				LOGGER.warn("Failed : HTTP error code: {}", response.getStatus());
			}
			return false;
		}

		return true;
	}

	private boolean doPost(final Process process, final String url) {
		/*
		 * final Client client = Client.create(); final WebResource webResource =
		 * client.resource(url);
		 *
		 * ClientResponse response;
		 *
		 * try { response = webResource.type(MEDIA_TYPE).post(ClientResponse.class,
		 * process); } catch (UniformInterfaceException | ClientHandlerException e) { if
		 * (LOGGER.isWarnEnabled()) { LOGGER.warn(
		 * "Connection to {} failed, probably not online or wrong IP. Check IP in WEB-INF/classes/explorviz.properties. Error Message: {}"
		 * , url, e.toString()); } return false; }
		 *
		 * if (response.getStatus() != HTTP_STATUS_CREATED) { if
		 * (LOGGER.isWarnEnabled()) { LOGGER.warn("Failed : HTTP error code: {}",
		 * response.getStatus()); } return false; }
		 *
		 * return true;
		 */
		return false;
	}

	public <T> Response doGETRequest(final GenericType<T> t, final String url) {
		final Client client = this.clientBuilder.build();
		final WebTarget webTarget = client.target(url);

		final Response response = null;

		try {
			webTarget.request(MEDIA_TYPE).get(t);
		} catch (UniformInterfaceException | ClientHandlerException e) {
			if (LOGGER.isWarnEnabled()) {
				LOGGER.warn(
						"Connection to {} failed, probably not online or wrong IP. Check IP in WEB-INF/classes/explorviz.properties. Error Message: {}",
						url, e.toString());
			}
		}

		/*
		 * if (response.getStatus() != HTTP_STATUS_CREATED) { if
		 * (LOGGER.isWarnEnabled()) { LOGGER.warn("Failed : HTTP error code: {}",
		 * response.getStatus()); } return false; }
		 */

		return response;
	}

}
