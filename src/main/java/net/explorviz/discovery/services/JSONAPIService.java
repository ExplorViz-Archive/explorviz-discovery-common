package net.explorviz.discovery.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.jasminb.jsonapi.JSONAPIDocument;
import com.github.jasminb.jsonapi.ResourceConverter;
import com.github.jasminb.jsonapi.exceptions.DocumentSerializationException;

import net.explorviz.discovery.factories.ResourceConverterFactory;

// This class contains logic for manual parsing of data to JSONAPI payload
// This is necessary for the ClientService, since Jersey / HK2 sometimes injects the
// wrong provider (backend)
public final class JSONAPIService {

	private static final Logger LOGGER = LoggerFactory.getLogger(JSONAPIService.class);

	private static final ResourceConverter CONVERTER;

	static {
		CONVERTER = new ResourceConverterFactory().provide();
	}

	private JSONAPIService() {
		// do not instantiate, since util class

	}

	private static JSONAPIDocument<List<?>> objectsToJSONAPIDoc(final List<?> list) {
		return new JSONAPIDocument<>(list);
	}

	private static <T> JSONAPIDocument<?> objectToJSONAPIDoc(final T p) {
		return new JSONAPIDocument<>(p);
	}

	private static byte[] apiDocumentListToByte(final JSONAPIDocument<List<?>> apiDocument) {
		try {
			return CONVERTER.writeDocumentCollection(apiDocument);
		} catch (final DocumentSerializationException e) {
			LOGGER.error("Error when parsing list to byte: ", e);
			// TODO return error infos
			// https://github.com/jasminb/jsonapi-converter/blob/develop/src/main/java/com/github/jasminb/jsonapi/JSONAPIDocument.java#L67
			return new byte[1];
		}
	}

	private static byte[] apiDocumentToByte(final JSONAPIDocument<?> apiDocument) {
		try {
			return CONVERTER.writeDocument(apiDocument);
		} catch (final DocumentSerializationException e) {
			if (LOGGER.isErrorEnabled()) {
				LOGGER.error("Error when parsing object to byte: " + e);
			}
			// TODO return error infos
			// https://github.com/jasminb/jsonapi-converter/blob/develop/src/main/java/com/github/jasminb/jsonapi/JSONAPIDocument.java#L67
			return new byte[1];
		}
	}

	public static byte[] listToByteArray(final List<?> list) {
		return apiDocumentListToByte(objectsToJSONAPIDoc(list));
	}

	public static <T> byte[] objectToByteArray(final T t) {
		return apiDocumentToByte(objectToJSONAPIDoc(t));
	}

	public static List<?> byteArrayToList(final String typeName, final byte[] jsonPayload) {
		final Class<?> type = TypeService.typeMap.get(typeName);
		return CONVERTER.readDocumentCollection(jsonPayload, type).get();
	}

	public static Object byteArrayToObject(final String typeName, final byte[] jsonPayload) {
		final Class<?> type = TypeService.typeMap.get(typeName);
		return CONVERTER.readDocument(jsonPayload, type).get();
	}

}
