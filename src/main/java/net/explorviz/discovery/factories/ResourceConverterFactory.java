package net.explorviz.discovery.factories;

import org.glassfish.hk2.api.Factory;

import com.github.jasminb.jsonapi.ResourceConverter;
import com.github.jasminb.jsonapi.SerializationFeature;

import net.explorviz.discovery.services.TypeService;

public class ResourceConverterFactory implements Factory<ResourceConverter> {
	private final ResourceConverter converter;

	public ResourceConverterFactory() {
		final ResourceConverter resourceConverter = new ResourceConverter();

		TypeService.typeMap.forEach((name, classType) -> {
			resourceConverter.registerType(classType);
		});

		this.converter = resourceConverter;
		this.converter.enableSerializationOption(SerializationFeature.INCLUDE_RELATIONSHIP_ATTRIBUTES);
	}

	@Override
	public void dispose(final ResourceConverter arg0) {
		// Nothing to dispose
	}

	@Override
	public ResourceConverter provide() {
		return this.converter;
	}
}
