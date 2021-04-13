package com.reactive.callable.executor;

import java.util.concurrent.Executor;
import java.util.concurrent.Future;

public interface ExecutorService extends Executor {

    // Submits a value-returning task for execution and returns a Future representing the pending results of the task.
    <T> Future<T> submit(Callable<T> task);

    // Submits a Runnable task for execution and returns a Future representing that task.
    Future<?> submit(Runnable task);

    // Submits a Runnable task for execution and returns a Future representing that task.
    <T> Future<T> submit(Runnable task, T result);
}
