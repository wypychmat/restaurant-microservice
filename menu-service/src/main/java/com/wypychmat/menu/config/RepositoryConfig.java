package com.wypychmat.menu.config;

import com.wypychmat.menu.domain.MenuRepository;
import com.wypychmat.menu.repository.InMemoryMenuRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class RepositoryConfig {

    @Bean
    @Profile("memory")
    MenuRepository menuRepository() {
        return new InMemoryMenuRepository();
    }

}
