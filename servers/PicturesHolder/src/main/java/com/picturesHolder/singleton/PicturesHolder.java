package com.picturesHolder.singleton;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Dimitar
 */
public class PicturesHolder {

    private static PicturesHolder instance;

    private Map<String, String> holder;

    private PicturesHolder() {
        holder = new HashMap<>();
    }

    public static PicturesHolder getInstance() {
        if (instance == null) {
            synchronized (PicturesHolder.class) {
                if (instance == null) {
                    instance = new PicturesHolder();
                }
            }
        }
        return instance;
    }

    public boolean contains(String key) {
        return this.holder.containsKey(key);
    }

    public void put(String key, String value) {
        this.holder.put(key, value);
    }

    public String get(String key) {
        return this.holder.get(key);
    }
    
    public int size(){
        return this.holder.size();
    }

}
