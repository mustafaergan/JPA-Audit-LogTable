package com.mustafaergan.audit.domain;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author : MustafaERGAN
 * Date: 4.10.2018 09:53
 */
public interface UserRepository extends JpaRepository<User, Long> {
}
