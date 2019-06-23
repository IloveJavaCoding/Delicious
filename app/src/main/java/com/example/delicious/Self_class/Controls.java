package com.example.delicious.Self_class;

import android.app.Application;

import java.util.List;

public class Controls extends Application {
    private static int lock;
    private static List<String> order;

    public static List<String> getOrder() {
        return order;
    }

    public static void setOrder(List<String> order) {
        Controls.order = order;
    }

    public Controls() {
    }

    public static int getLock() {
        return lock;
    }

    public static void setLock(int lock) {
        Controls.lock = lock;
    }
}
