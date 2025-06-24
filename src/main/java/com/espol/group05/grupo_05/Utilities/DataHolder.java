package com.espol.group05.grupo_05.Utilities;

public class DataHolder {
    private static DataHolder instance = new DataHolder();
    private Object myData;
    private DataHolder() {}
    
    public static DataHolder getInstance() {
        return instance;
    }
    
    public Object getMyData() {
        return myData;
    }
    
    public void setMyData(Object data) {
        this.myData = data;
    }
}
