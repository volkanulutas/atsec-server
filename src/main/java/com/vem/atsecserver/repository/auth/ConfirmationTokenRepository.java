package com.vem.atsecserver.repository.auth;

import com.vem.atsecserver.entity.auth.ConfirmationToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author volkanulutas
 * @since 01.01.2021
 */
@Repository
public interface ConfirmationTokenRepository extends CrudRepository<ConfirmationToken, String> {
	ConfirmationToken findByConfirmationToken(String confirmationToken);
}
