package cz.kinovic.yamlMerge.service;

import java.util.List;
import java.util.Map;

/**
 * @author Ondrej Kinovic (ondrej@kinovic.cz)
 * @since 27.10.16.
 */
public class MapMerge {

    //http://stackoverflow.com/questions/25773567/recursive-merge-of-n-level-maps/25774346
    public static Map deepMerge(Map original, Map newMap) {
        for (Object key : newMap.keySet()) {
            if (newMap.get(key) instanceof Map && original.get(key) instanceof Map) {
                Map originalChild = (Map) original.get(key);
                Map newChild = (Map) newMap.get(key);
                original.put(key, deepMerge(originalChild, newChild));
            } else if (newMap.get(key) instanceof List && original.get(key) instanceof List) {
                List originalChild = (List) original.get(key);
                List newChild = (List) newMap.get(key);
                for (Object each : newChild) {
                    if (!originalChild.contains(each)) {
                        originalChild.add(each);
                    }
                }
            } else {
                original.put(key, newMap.get(key));
            }
        }
        return original;
    }
}
