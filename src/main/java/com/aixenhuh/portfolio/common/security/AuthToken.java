package com.aixenhuh.portfolio.common.security;

public interface AuthToken<T> {
    boolean validate();
    T getData();

}
