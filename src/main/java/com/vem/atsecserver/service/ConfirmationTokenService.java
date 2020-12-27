package com.vem.atsecserver.service;

import com.vem.atsecserver.entity.auth.ConfirmationToken;

public interface ConfirmationTokenService {
    ConfirmationToken save(ConfirmationToken confirmationToken);

    ConfirmationToken findByConfirmationToken(String confirmationToken);
}
