package com.vem.atsecserver.service;

import com.vem.atsecserver.entity.auth.ConfirmationToken;

/**
 * @author volkanulutas
 * @since 01.01.2021
 */
public interface ConfirmationTokenService {
    ConfirmationToken save(ConfirmationToken confirmationToken);

    ConfirmationToken findByConfirmationToken(String confirmationToken);
}
