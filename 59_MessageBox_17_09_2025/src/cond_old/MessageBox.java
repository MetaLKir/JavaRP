package cond_old;

public class MessageBox {
    private String message;
    private Object mutex = new Object();

    public void put(String message) {
        synchronized (this){
            while (this.message != null) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            this.message = message;
            synchronized (mutex) {
                this.notify();
            }

        }

    }

    public String take() throws InterruptedException {
        synchronized (mutex){
            while (this.message == null) {
                this.wait();
            }
            synchronized (this) {
                String res = this.message;
                this.message = null;
                this.notify();
                return res;
            }
        }

    }

    synchronized public String pull() {
        String res = this.message;
        this.message = null;
        this.notifyAll();
        return res;
    }
}
