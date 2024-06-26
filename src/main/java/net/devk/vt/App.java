package net.devk.vt;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentSkipListSet;

public class App {

    private static void block(long millis) {
        try {
            System.out.println("Thread " + Thread.currentThread() + " will be blocked for " + millis + "ms");
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    public static void main(String[] args) {

        var threads = new ArrayList<Thread>();
        var names = new ConcurrentSkipListSet<String>();
        for (var i = 0; i < 1000; i++) {
            var first = i == 0;
            var thread = Thread.ofVirtual().unstarted(() -> {
                System.out.println("Starting Thread : " + Thread.currentThread());

                if (first) {
                    names.add(Thread.currentThread().toString());
                }
                block(100);

                if (first) {
                    names.add(Thread.currentThread().toString());
                }
                block(100);

                if (first) {
                    names.add(Thread.currentThread().toString());
                }
                block(100);

                if (first) {
                    names.add(Thread.currentThread().toString());
                }
                block(100);
            });
            threads.add(thread);

        }

        threads.forEach(Thread::start);

        for (var t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        System.out.println("Participated Threads: " + names);


    }
}
