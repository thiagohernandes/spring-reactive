package com.reactive.callable.executor;

@FunctionalInterface
public interface Callable<V> {
    V call() throws Exception;
}
