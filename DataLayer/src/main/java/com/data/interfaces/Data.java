package com.data.interfaces;

//crud interface, could be seperated for each ServiceType
public interface Data {
    public Object create(Object parameter);
    public Object read(Object parameter);
    public Object readAll(Object parameter);
    public Object update(Object parameter);
    public boolean delete(Object parameter);
    
}
