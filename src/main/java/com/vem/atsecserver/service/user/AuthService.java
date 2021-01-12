package com.vem.atsecserver.service.user;

/**
 * @author volkanulutas
 * @since 01.01.2021
 */
public interface AuthService {
    Boolean changeUserPassword(String token, String password);
}
