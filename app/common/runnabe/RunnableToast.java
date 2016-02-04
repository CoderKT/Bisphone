package app.common.runnabe;

import app.Main;

public class RunnableToast implements Runnable {
    private String f2290a;

    public RunnableToast(String str) {
        this.f2290a = str;
    }

    public void run() {
        Main.m3905a(this.f2290a);
    }
}
