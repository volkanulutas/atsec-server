package com.vem.atsecserver.service.user;

public interface AuthService {
    Boolean changeUserPassword(String token, String password);
}
