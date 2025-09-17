package reentrant;

import java.util.concurrent.locks.*;

public class MessageBox {
    private String message;
    Lock lock;
    Condition producerWaitingCondition;
    Condition consumerWaitingCondition;

    public MessageBox() {
        this.lock = new ReentrantLock();
        this.producerWaitingCondition = lock.newCondition();
        this.consumerWaitingCondition = lock.newCondition();
    }

    public void put(String message) {
        lock.lock();
        try {
            while (this.message != null) {
                try {
                    producerWaitingCondition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            this.message = message;
            consumerWaitingCondition.signal();
        } finally {
            lock.unlock();
        }
    }

    public String take() throws InterruptedException {
        lock.lock();
        try {
            while (this.message == null) {
                consumerWaitingCondition.await();
            }
            String res = this.message;
            this.message = null;
            producerWaitingCondition.signal();
            return res;
        } finally {
            lock.unlock();
        }
    }

    public String pull() {
        lock.lock();
        try {
            String res = this.message;
            this.message = null;
            producerWaitingCondition.signal();
            return res;
        } finally {
            lock.unlock();
        }
    }
}
