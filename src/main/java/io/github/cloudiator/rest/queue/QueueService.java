package io.github.cloudiator.rest.queue;


import static com.google.common.base.Preconditions.checkNotNull;

import io.github.cloudiator.rest.model.Queue;
import io.github.cloudiator.rest.model.QueueStatus;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.function.Function;
import javax.annotation.Nullable;
import org.cloudiator.messaging.ResponseException;

public class QueueService {

  /**
   * userId, (llrId, LongRunningTask)
   */
  private final HashMap<String, HashMap<String, QueueItem>> table;

  public QueueService() {
    this.table = new HashMap<String, HashMap<String, QueueItem>>();
  }

  private final static class QueueFactory {

    public static Queue next() {
      final Queue queue = new Queue();
      queue.setId(UUID.randomUUID().toString());
      queue.setStatus(QueueStatus.SCHEDULED);
      return queue;
    }

  }

  public static class QueueItem<T> {

    private final QueueCallback<T> queueCallback;
    private final Queue queue;
    private final String userId;


    public QueueItem(String userId, QueueCallback<T> queueCallback, Queue queue) {
      this.queueCallback = queueCallback;
      this.queue = queue;
      this.userId = userId;
    }

    public QueueCallback<T> getCallback() {
      return queueCallback;
    }

    public Queue getQueue() {
      return queue;
    }

    public String getUserId() {
      return userId;
    }
  }

  private <T> QueueItem<T> putEntry(QueueItem<T> queueItem) {
    if (table.get(queueItem.getUserId()) == null) {
      this.table.put(queueItem.getUserId(), new HashMap<>());
    }
    table.get(queueItem.getUserId()).put(queueItem.getQueue().getId(), queueItem);
    return queueItem;
  }

  public <T> QueueItem<T> queueCallback(String userId, Function<T, String> resolver) {
    final Queue queue = QueueFactory.next();
    final QueueCallback<T> queueCallback = new QueueCallback<>(resolver);
    return putEntry(new QueueItem<T>(userId, queueCallback, queue));
  }

  @Nullable
  public Queue get(String userId, String id) {
    checkNotNull(userId, "userId is null");
    checkNotNull(id, "id is null");
    return this.retrieve(userId, id);
  }

  public Collection<Queue> getAll(String userId) {
    checkNotNull(userId, "userId is null");
    List<Queue> queueList = new LinkedList<>();

    for (Entry<String, QueueItem> entry : table.get(userId).entrySet()) {
      queueList.add(entry.getValue().getQueue());
    }

    return queueList;
  }

  @Nullable
  private Queue retrieve(String userId, String id) {
    if (table.get(userId) == null) {
      return null;
    }

    final QueueItem queueItem = table.get(userId).get(id);

    if (queueItem == null) {
      return null;
    }

    final Queue queue = queueItem.getQueue();
    if (queueItem.getCallback().isDone()) {
      try {
        final String s = queueItem.getCallback().get();
        queue.setLocation(s);
        queue.setStatus(QueueStatus.COMPLETED);
      } catch (InterruptedException e) {
        throw new IllegalStateException(e);
      } catch (ExecutionException e) {
        if (e.getCause() instanceof ResponseException) {
          queue.setStatus(QueueStatus.FAILED);
        } else {
          throw new IllegalStateException(e.getCause());
        }
      }
    } else {
      queue.setStatus(QueueStatus.RUNNING);
    }

    return queue;
  }


}
