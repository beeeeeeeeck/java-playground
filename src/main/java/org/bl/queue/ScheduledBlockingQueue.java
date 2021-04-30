package org.bl.queue;

import java.util.concurrent.BlockingQueue;

public interface ScheduledBlockingQueue<T> extends BlockingQueue<T> {
    void setExecutorShutdownThreshold(int threshold);
}
