package com.mustafaergan.audit.configuration;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

/**
 * @author : MustafaERGAN
 * Date: 4.10.2018 09:53
 */
public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of("Mustafa ERGAN");
    }

}
