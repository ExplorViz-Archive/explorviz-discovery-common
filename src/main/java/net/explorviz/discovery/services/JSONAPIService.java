package net.explorviz.discovery.services;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.jasminb.jsonapi.JSONAPIDocument;
import com.github.jasminb.jsonapi.ResourceConverter;
import com.github.jasminb.jsonapi.exceptions.DocumentSerializationException;

// This class contains logic for manual parsing of data to JSONAPI payload
// This is necessary for the ClientService, since Providers can not be used automatically
public class JSONAPIService {

	private static final Logger LOGGER = LoggerFactory.getLogger(JSONAPIService.class);

	private final ResourceConverter converter;

	@Inject
	public JSONAPIService(final ResourceConverter converter) {
		this.converter = converter;
	}

	private JSONAPIDocument<List<?>> objectsToJSONAPIDoc(final List<?> list) {
		return new JSONAPIDocument<>(list);
	}

	private <T> JSONAPIDocument<?> objectToJSONAPIDoc(final T p) {
		return new JSONAPIDocument<>(p);
	}

	private byte[] apiDocumentListToByte(final JSONAPIDocument<List<?>> apiDocument) {
		try {
			return this.converter.writeDocumentCollection(apiDocument);
		} catch (final DocumentSerializationException e) {
			LOGGER.error("Error when parsing list to byte: ", e);
			// TODO return error infos
			// https://github.com/jasminb/jsonapi-converter/blob/develop/src/main/java/com/github/jasminb/jsonapi/JSONAPIDocument.java#L67
			return new byte[1];
		}
	}

	private byte[] apiDocumentToByte(final JSONAPIDocument<?> apiDocument) {
		try {
			return this.converter.writeDocument(apiDocument);
		} catch (final DocumentSerializationException e) {
			if (LOGGER.isErrorEnabled()) {
				LOGGER.error("Error when parsing object to byte: " + e);
			}
			// TODO return error infos
			// https://github.com/jasminb/jsonapi-converter/blob/develop/src/main/java/com/github/jasminb/jsonapi/JSONAPIDocument.java#L67
			return new byte[1];
		}
	}

	public byte[] listToByteArray(final List<?> list) {
		return apiDocumentListToByte(objectsToJSONAPIDoc(list));
	}

	public <T> byte[] objectToByteArray(final T t) {
		return apiDocumentToByte(objectToJSONAPIDoc(t));
	}

}
