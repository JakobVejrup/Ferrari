package com.data.interfaces;
// anders
public interface Data {
    public Object create(Object parameter);
    public Object read(Object parameter);
    public Object readAll(Object parameter);
    public Object update(Object parameter);
    public boolean delete(Object parameter);
    
    public static double round4(double value) {
        long factor = (long) Math.pow(10, 4);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }



}
