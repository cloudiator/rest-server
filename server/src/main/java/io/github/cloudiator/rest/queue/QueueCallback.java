package io.github.cloudiator.rest.queue;

import static com.google.common.base.Preconditions.checkState;

import com.google.common.util.concurrent.SettableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import javax.annotation.Nullable;
import org.cloudiator.messages.General.Error;
import org.cloudiator.messaging.ResponseCallback;
import org.cloudiator.messaging.ResponseException;
import org.threeten.bp.OffsetDateTime;

class QueueCallback<T> implements Future<T>, ResponseCallback<T> {

  private final SettableFuture<T> settableFuture;
  private OffsetDateTime end = null;


  public QueueCallback() {
    this.settableFuture = SettableFuture.create();
  }

  @Override
  public boolean cancel(boolean b) {
    throw new UnsupportedOperationException("Canceling of QueueTasks is not supported.");
  }

  @Override
  public boolean isCancelled() {
    throw new UnsupportedOperationException("Canceling of QueueTasks is not supported.");
  }

  @Override
  public boolean isDone() {
    return settableFuture.isDone();
  }

  @Override
  public T get() throws InterruptedException, ExecutionException {
    return settableFuture.get();
  }

  @Override
  public T get(long l, TimeUnit timeUnit)
      throws InterruptedException, ExecutionException, TimeoutException {
    return settableFuture.get(l, timeUnit);
  }

  private void setEnd(OffsetDateTime offsetDateTime) {
    checkState(end == null, "End already set");
    this.end = offsetDateTime;
  }

  @Override
  public void accept(@Nullable T content, @Nullable Error error) {
    if (error != null) {
      settableFuture.setException(new ResponseException(error.getCode(), error.getMessage()));
    }
    if (content != null) {
      settableFuture.set(content);
    }
    setEnd(OffsetDateTime.now());
  }

  @Nullable
  OffsetDateTime end() {
    return end;
  }
}
