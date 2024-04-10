package org.dimensinfin.eveonline.neocom.infinity.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author Adam Antinoo (adamantinoo.git@gmail.com)
 * @since 0.20.0
 */
@Configuration
@EnableScheduling
@ComponentScan("org.dimensinfin.eveonline.neocom.infinity.config")
public class SchedulerConfig {
}