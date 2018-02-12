package net.explorviz.discovery.services;

import java.util.List;
import java.util.Map;

import javax.ws.rs.ProcessingException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;

import org.glassfish.jersey.client.HttpUrlConnectorProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.explorviz.discovery.model.Agent;
import net.explorviz.discovery.model.Procezz;

public class ClientService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ClientService.class);
	private static final String MEDIA_TYPE = "application/vnd.api+json";

	private static final String LOGGER_MESSAGE = "Connection to {} failed, probably not online or wrong IP. Check IP in WEB-INF/classes/explorviz.properties. Error Message: {}";

	private final ClientBuilder clientBuilder = ClientBuilder.newBuilder();

	public <T> void registerProviderReader(final MessageBodyReader<T> providerReader) {
		clientBuilder.register(providerReader);
	}

	public <T> void registerProviderWriter(final MessageBodyWriter<T> providerWriter) {
		clientBuilder.register(providerWriter);
	}

	public <T> Response doPOSTRequest(final T t, final String url) throws ProcessingException {
		final Client client = this.clientBuilder.build();
		return client.target(url).request(MEDIA_TYPE).post(Entity.entity(t, MEDIA_TYPE));
	}

	public <T> Response doPOSTProcessListRequest(final List<Procezz> procezzList, final String url) {
		final Client client = this.clientBuilder.build();

		try {
			return client.target(url).request(MEDIA_TYPE).post(Entity.entity(procezzList, MEDIA_TYPE));
		} catch (final ProcessingException e) {
			if (LOGGER.isWarnEnabled()) {
				LOGGER.warn(LOGGER_MESSAGE, url, e.toString());
			}
			return null;
		}
	}

	public <T> T doGETRequest(final Class<T> type, final String url, final Map<String, Object> queryParameter)
			throws ProcessingException, WebApplicationException {
		final Client client = this.clientBuilder.build();

		final GenericType<T> genericType = new GenericType<>(type);

		if (queryParameter == null) {
			return client.target(url).request(MEDIA_TYPE).get(genericType);
		} else {

			WebTarget target = client.target(url);

			for (final Map.Entry<String, Object> queryParam : queryParameter.entrySet()) {
				target = target.queryParam(queryParam.getKey(), queryParam.getValue());
			}

			return target.request(MEDIA_TYPE).get(genericType);
		}

	}

	public String doGETRequest(final String url, final Map<String, Object> queryParameter) {
		final Client client = this.clientBuilder.build();

		Response response;

		try {
			if (queryParameter == null) {
				response = client.target(url).request(MEDIA_TYPE).get();
			} else {

				WebTarget target = client.target(url);

				for (final Map.Entry<String, Object> queryParam : queryParameter.entrySet()) {
					target = target.queryParam(queryParam.getKey(), queryParam.getValue());
				}

				response = target.request(MEDIA_TYPE).get();
			}

		} catch (ProcessingException | WebApplicationException e) {
			if (LOGGER.isWarnEnabled()) {
				LOGGER.warn(LOGGER_MESSAGE, url, e);
			}
			return null;
		}

		return response.readEntity(String.class);
	}

	public String doGETRequest(final String url) {
		final Client client = this.clientBuilder.build();

		Response response = null;

		try {
			response = client.target(url).request(MEDIA_TYPE).get();
		} catch (ProcessingException | WebApplicationException e) {
			if (LOGGER.isWarnEnabled()) {
				LOGGER.warn(LOGGER_MESSAGE, url, e);
			}
			return null;
		}

		if (response.getStatus() != Response.Status.OK.getStatusCode()) {
			LOGGER.warn("GET Request failed: {}", response.getStatusInfo());
		}

		return response.readEntity(String.class);
	}

	public <T> List<Procezz> doGETProcezzListRequest(final String url, final Map<String, Object> queryParameter)
			throws ProcessingException, WebApplicationException {
		final Client client = this.clientBuilder.build();

		final GenericType<List<Procezz>> genericType = new GenericType<List<Procezz>>() {
		};

		if (queryParameter == null) {
			return client.target(url).request(MEDIA_TYPE).get(genericType);
		} else {

			WebTarget target = client.target(url);

			for (final Map.Entry<String, Object> queryParam : queryParameter.entrySet()) {
				target = target.queryParam(queryParam.getKey(), queryParam.getValue());
			}

			return target.request(MEDIA_TYPE).get(genericType);
		}
	}

	public <T> List<Agent> doGETPAgentListRequest(final String url, final Map<String, Object> queryParameter)
			throws ProcessingException, WebApplicationException {
		final Client client = this.clientBuilder.build();

		final GenericType<List<Agent>> genericType = new GenericType<List<Agent>>() {
		};

		if (queryParameter == null) {
			return client.target(url).request(MEDIA_TYPE).get(genericType);
		} else {

			WebTarget target = client.target(url);

			for (final Map.Entry<String, Object> queryParam : queryParameter.entrySet()) {
				target = target.queryParam(queryParam.getKey(), queryParam.getValue());
			}

			return target.request(MEDIA_TYPE).get(genericType);
		}
	}

	public <T> Response doPatch(final T t, final String url) throws ProcessingException {
		return this.clientBuilder.build().target(url).request(MEDIA_TYPE).build("PATCH", Entity.entity(t, MEDIA_TYPE))
				.property(HttpUrlConnectorProvider.SET_METHOD_WORKAROUND, true).invoke();
	}

}
