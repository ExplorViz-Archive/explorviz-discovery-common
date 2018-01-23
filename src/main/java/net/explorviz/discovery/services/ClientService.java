package net.explorviz.discovery.services;

import javax.ws.rs.ProcessingException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.explorviz.discovery.model.Process;

public class ClientService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ClientService.class);
	private static final String MEDIA_TYPE = "application/vnd.api+json";

	private static final String LOGGER_MESSAGE = "Connection to {} failed, probably not online or wrong IP. Check IP in WEB-INF/classes/explorviz.properties. Error Message: {}";

	private final ClientBuilder clientBuilder = ClientBuilder.newBuilder();

	public <T> void registerProvider(final MessageBodyReader<T> providerReader) {
		clientBuilder.register(providerReader);
	}

	public <T> void registerProviderWriter(final MessageBodyWriter<T> providerWriter) {
		clientBuilder.register(providerWriter);
	}

	public boolean postProcess(final Process process) {
		return doPost(process, "http://localhost:8082/process");
	}

	public <T> boolean doPost(final T t, final String url) {
		final Client client = this.clientBuilder.build();

		Response response;

		try {
			response = client.target(url).request(MEDIA_TYPE).post(Entity.entity(t, MEDIA_TYPE));
		} catch (final ProcessingException e) {
			if (LOGGER.isWarnEnabled()) {
				LOGGER.warn(LOGGER_MESSAGE, url, e.toString());
			}
			return false;
		}

		if (response.getStatus() != Response.Status.CREATED.getStatusCode()) {
			if (LOGGER.isWarnEnabled()) {
				LOGGER.warn("Failed : HTTP error code: {}", response.getStatus());
			}
			return false;
		}

		return true;
	}

	public <T> boolean doPost(final String payload, final String url) {
		final Client client = this.clientBuilder.build();

		Response response;

		try {
			response = client.target(url).request(MEDIA_TYPE).post(Entity.text(payload));
		} catch (final ProcessingException e) {
			if (LOGGER.isWarnEnabled()) {
				LOGGER.warn(LOGGER_MESSAGE, url, e.toString());
			}
			return false;
		}

		if (response.getStatus() != Response.Status.CREATED.getStatusCode()) {
			if (LOGGER.isWarnEnabled()) {
				LOGGER.warn("Failed : HTTP error code: {}", response.getStatus());
			}
			return false;
		}

		return true;
	}

	public <T> T doGETRequest(final Class<T> type, final String url) {
		final Client client = this.clientBuilder.build();

		try {
			final GenericType<T> genericType = new GenericType<>(type);

			return client.target(url).request(MEDIA_TYPE).get(genericType);

		} catch (ProcessingException | WebApplicationException e) {
			if (LOGGER.isWarnEnabled()) {
				LOGGER.warn(LOGGER_MESSAGE, url, e);
				LOGGER.warn("Stacktrace", e);
			}
		}

		return null;
	}

	public String doGETRequest(final String url) {
		final Client client = this.clientBuilder.build();

		Response response = null;

		try {
			response = client.target(url).request(MEDIA_TYPE).get();
		} catch (ProcessingException | WebApplicationException e) {
			if (LOGGER.isWarnEnabled()) {
				LOGGER.warn(LOGGER_MESSAGE, url, e);
				LOGGER.warn("Stacktrace", e);
			}
			return null;
		}

		if (response.getStatus() != Response.Status.OK.getStatusCode()) {
			LOGGER.warn("GET Request failed: {}", response.getStatusInfo());
		}

		return response.readEntity(String.class);
	}

	public <T> String doPatchRequest(final byte[] payload, final String url) {
		final Client client = this.clientBuilder.build();

		Response response = null;

		try {
			response = client.target(url).request(MEDIA_TYPE).post(Entity.text(payload));
		} catch (final ProcessingException e) {
			if (LOGGER.isWarnEnabled()) {
				LOGGER.warn(LOGGER_MESSAGE, url, e.toString());
			}
			return null;
		}

		if (response.getStatus() != Response.Status.CREATED.getStatusCode() && LOGGER.isWarnEnabled()) {
			LOGGER.warn("Failed : HTTP error code: {}", response.getStatus());
		}

		return response.readEntity(String.class);
	}

}
