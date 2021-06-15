package com.innocentchdth.zss.config;

import java.util.List;
import java.util.Objects;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper modelMapper(List<PropertyMap> propertyMaps) {
        final ModelMapper modelMapper = new ModelMapper();
        propertyMaps.stream().filter(Objects::nonNull).forEach(modelMapper::addMappings);
        return modelMapper;
    }
}
