package com.example.delicious.Self_class;

import android.app.Application;

import java.util.List;

public class Controls extends Application {
    private static int lock;
    private static int lock2;
    private static String root;
    public Controls() {
    }

    public static String getRoot() {
        return root;
    }

    public static void setRoot(String root) {
        Controls.root = root;
    }

    public static int getLock() {
        return lock;
    }

    public static void setLock(int lock) {
        Controls.lock = lock;
    }

    public static int getLock2() {
        return lock2;
    }

    public static void setLock2(int lock2) {
        Controls.lock2 = lock2;
    }
}
