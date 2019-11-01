package synch_static;

public class SynchCounter {
    static int count;

    public static synchronized void addSynch(int val) {
        add(val);
    }

    public static synchronized void subSynch(int val) {
        sub(val);
    }

    public static void add(int val) {
        count += val;
    }

    public static void sub(int val) {
        count -= val;
    }

    public static int getCount() {
        return count;
    }
}
