package com.aixenhuh.portfolio.core.security;

public interface AuthToken<T> {
    boolean validate();
    T getData();

}
