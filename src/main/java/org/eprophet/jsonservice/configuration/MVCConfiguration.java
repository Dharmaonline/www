package org.eprophet.jsonservice.configuration;

import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import com.fasterxml.jackson.databind.SerializationFeature;

@Configuration
@EnableWebMvc
public class MVCConfiguration extends WebMvcConfigurerAdapter{
  private MappingJackson2HttpMessageConverter jacksonMessageConverter() {
    Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder()
            .featuresToDisable(SerializationFeature.FAIL_ON_EMPTY_BEANS,
                    SerializationFeature.WRITE_CHAR_ARRAYS_AS_JSON_ARRAYS)
            .featuresToEnable(SerializationFeature.INDENT_OUTPUT);
    // can use this instead of featuresToEnable(...)
    builder.indentOutput(true);
    return new MappingJackson2HttpMessageConverter(builder.build());
}

  @Bean
  public InternalResourceViewResolver resolver() {
      InternalResourceViewResolver vr = new InternalResourceViewResolver();
      vr.setPrefix("/WEB-INF/jsps/");
      vr.setSuffix(".jsp");
      return vr;
  }
@Override
public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
    converters.add(jacksonMessageConverter());
    super.configureMessageConverters(converters);
}

}