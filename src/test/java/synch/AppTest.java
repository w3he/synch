/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package synch;

import org.testng.Assert;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.List;


public class AppTest {
    int threads = 10;
    int N = 1000;

    @Test(priority = 1)
    void scenario1() {
        SynchCounter.count = 0;
        List<Thread>allThreads = new ArrayList<>();
        for (int i = 0; i < threads; i++) {
            Thread t1 = new Thread(App.getRunner(N));
            t1.start();
            allThreads.add(t1);
        }
        waitForResults(allThreads);
        Assert.assertEquals(SynchCounter.getCount(), 0);
    }
    @Test(priority = 2)
    void scenario2() {
        SynchCounter.count = 0;

        List<Thread>allThreads = new ArrayList<>();
        for (int i = 0; i < threads; i++) {
            Thread t2 = new Thread(App.getSynchRunner(N));
            t2.start();
            allThreads.add(t2);
        }
        waitForResults(allThreads);
        Assert.assertEquals(SynchCounter.getCount(), 0);
    }

    @Test(priority = 3)
    void scenario3() {
        SynchCounter.count = 0;
        List<Thread>allThreads = new ArrayList<>();
        threads = threads / 2;
        for (int i = 0; i < threads; i++) {
            Thread t1 = new Thread(App.getRunner(N));
            Thread t2 = new Thread(App.getSynchRunner(N));
            t1.start();
            t2.start();
            allThreads.add(t1);
            allThreads.add(t2);
        }
        waitForResults(allThreads);
        Assert.assertEquals(SynchCounter.getCount(), 0);
    }

    void waitForResults(List<Thread> allThreads) {
        try {
            System.out.println("Wait for all threads to finish: " + allThreads.size());
            for(Thread t : allThreads) {
                t.join();
            }
        } catch (InterruptedException e) {
            System.err.println("We are interrupted. " + e.getLocalizedMessage());
        }

        System.out.println("Final count: " + SynchCounter.getCount());

    }
}