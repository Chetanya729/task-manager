package com.project1.task_manager.config;

import jakarta.annotation.Nonnull;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverterFactory(new ConverterFactory<String, Enum>() {
            @Override
            public <T extends Enum> Converter<String, T> getConverter(@Nonnull Class<T> targetType) {
                return source -> {
                    for (T constant : targetType.getEnumConstants()) {
                        if (constant.name().equalsIgnoreCase(source.trim())) {
                            return constant;
                        }
                    }
                    throw new IllegalArgumentException(
                            "Invalid value '" + source + "' for " + targetType.getSimpleName() +
                                    ". Accepted values: " + java.util.Arrays.toString(targetType.getEnumConstants())
                    );
                };
            }
        });
    }
}