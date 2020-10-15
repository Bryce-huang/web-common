package cn.riris.config;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnMissingBean(name = "orikaConfig")
public class OrikaConfig {

    @Bean
    @ConditionalOnMissingBean(name = "mapperFactory")
    public MapperFactory mapperFactory() {
        return new DefaultMapperFactory.Builder().build();
    }

    @Bean
    @ConditionalOnMissingBean(name = "mapperFacade")
    public MapperFacade mapperFacade() {
        return mapperFactory().getMapperFacade();
    }

}