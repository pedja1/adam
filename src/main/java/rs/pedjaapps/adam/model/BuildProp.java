package rs.pedjaapps.adam.model;

import java.util.HashMap;

/**
 * Created by pedja on 20.10.14. 12.48.
 * This class is part of the adam
 * Copyright © 2014 ${OWNER}
 */
public class BuildProp
{
    private final HashMap<String, String> properties;

    public BuildProp()
    {
        properties = new HashMap<>();
    }

    public String put(String key, String value)
    {
        return properties.put(key, value);
    }

    public boolean containsKey(Object key)
    {
        return properties.containsKey(key);
    }

    public String get(Object key)
    {
        return properties.get(key);
    }

    public int size()
    {
        return properties.size();
    }

    public boolean isEmpty()
    {
        return properties.isEmpty();
    }

    public String remove(Object key)
    {
        return properties.remove(key);
    }

    public String getDeviceName()
    {
        return properties.get("ro.product.manufacturer") + " " + properties.get("ro.product.model");
    }
}
