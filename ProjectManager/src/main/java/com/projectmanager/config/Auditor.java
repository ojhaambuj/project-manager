package com.projectmanager.config;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;

/**
 * 
 * @author ojhaak
 *
 */
public class Auditor implements AuditorAware<String> {

	@Override
	public Optional<String> getCurrentAuditor() {
		return Optional.of("SYSTEM");
	}

}
