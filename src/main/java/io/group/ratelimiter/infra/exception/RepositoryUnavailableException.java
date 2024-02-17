package io.group.ratelimiter.infra.exception;

public class RepositoryUnavailableException extends RuntimeException {

    public RepositoryUnavailableException(String message) {
        super(message);
    }

}
