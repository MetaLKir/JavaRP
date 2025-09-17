package cond_default;

public class MessageBoxAppl {
    private static final int N_PRODUCERS = 1;
    private static final int N_CONSUMERS = 10;

    public static void main(String[] args) throws InterruptedException {
        MessageBox box = new MessageBox();
        Producer[] producers = new Producer[N_PRODUCERS];
        Consumer[] consumers = new Consumer[N_CONSUMERS];
        launchProducers(producers, box);
        launchConsumers(consumers, box);
        waitProducers(producers);
        stopConsumers(consumers);
//        Thread.sleep(1000);
    }

    private static void stopConsumers(Consumer[] consumers) {
        for (int i = 0; i < consumers.length; i++) {
            consumers[i].interrupt();
        }
    }

    private static void waitProducers(Producer[] producers) {
        for (int i = 0; i < producers.length; i++) {
            try {
                producers[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    private static void launchConsumers(Consumer[] consumers, MessageBox box) {
        for (int i = 0; i < consumers.length; i++) {
            consumers[i] = new Consumer(box);
//            consumers[i].setDaemon(true);
            consumers[i].start();
        }
    }

    private static void launchProducers(Producer[] producers, MessageBox box) {
        for (int i = 0; i < producers.length; i++) {
            producers[i] = new Producer(box);
            producers[i].start();
        }
    }

}
