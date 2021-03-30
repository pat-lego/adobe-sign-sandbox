package io.github.adobe.sign.core.metadata;

import java.util.Map;

public interface SignMetadata {
    
    public Map<String, Object> getMetadata();

    public Object getValue(String key);

    public <T> T getValue(String key, Class<T> value);

    public <T> T getResult(Class<T> value);

    public Object getResult();

    public void put(String key, Object value);

    public void addAll(Map<String, Object> map);
}
