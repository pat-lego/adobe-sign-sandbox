package io.github.adobe.sign.core.actions;

import java.util.Map;

public interface SignActionMetadata {
    
    public Map<String, Object> getMetadata();

    public Object getValue(String key);

    public void put(String key, Object value);

    public void addAll(Map<String, Object> map);
}
