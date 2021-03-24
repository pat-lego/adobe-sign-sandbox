package io.github.adobe.sign.core.metadata;

import java.util.Map;

public interface SignMetadata {
    
    public Map<String, Object> getMetadata();

    public Object getValue(String key);

    public void put(String key, Object value);

    public void addAll(Map<String, Object> map);
}
