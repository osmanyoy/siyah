package com.spring.training.profile;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("live")
@Configuration
public class LiveConfig {

}
