package com.model.threads;

public class ObjectWithTimer {
    private int seconds;
    private boolean extra;
    private Thread timer;
    private Function function;
    private Object value;
    private Action afterTime;
    public ObjectWithTimer(Function function, int seconds) {
        this.seconds = seconds;
        this.function = function;
        this.value = function.function();
    }
    public ObjectWithTimer(Function function, int seconds, Action afterTime) {
        this(function, seconds);
        this.afterTime = afterTime;
    }
    public Object getValue() {
        if(timer == null)
            timer();
        else
            extra = true;
        return value == null ? value = function.function() : value;
    }
    private void timer() {
        extra = true;
        timer = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (extra) {
                        extra = false;
                        Thread.sleep(seconds * 1000);
                    }
                    if (afterTime != null)
                        afterTime.action();
                    value = null;
                    timer = null;
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        timer.start();
    }
}
