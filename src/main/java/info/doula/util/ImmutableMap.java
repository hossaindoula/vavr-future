package info.doula.util;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Mohammed Hossain Doula
 *
 * @hossaindoula | @itconquest
 * <p>
 * http://hossaindoula.com
 * <p>
 * https://github.com/hossaindoula
 */
public class ImmutableMap<K, V> extends HashMap<K, V> {

    public static <K, V> Map<K, V> of(K key, V value) {
        return Collections.unmodifiableMap(new HashMap<K, V>() {{ put(key, value);}});
    }

    public static <K, V> Map<K, V> of(K key1, V value1, K key2, V value2) {
        return Collections.unmodifiableMap(new HashMap<K, V>() {{
            put(key1, value1);
            put(key2, value2);
        }});
    }

    public static <K, V> Map<K, V> of(K key1, V value1, K key2, V value2, K key3, V value3) {
        return Collections.unmodifiableMap(new HashMap<K, V>() {{
            put(key1, value1);
            put(key2, value2);
            put(key3, value3);
        }});
    }

    public static <K, V> Map<K, V> of(K key1, V value1, K key2, V value2, K key3, V value3, K key4, V value4) {
        return Collections.unmodifiableMap(new HashMap<K, V>() {{
            put(key1, value1);
            put(key2, value2);
            put(key3, value3);
            put(key4, value4);
        }});
    }

    public static <K, V> Map<K, V> of(K key1, V value1, K key2, V value2, K key3, V value3, K key4, V value4, K key5, V value5) {
        return Collections.unmodifiableMap(new HashMap<K, V>() {{
            put(key1, value1);
            put(key2, value2);
            put(key3, value3);
            put(key4, value4);
            put(key5, value5);
        }});
    }

    public static <K, V> Map<K, V> of(K key1, V value1, K key2, V value2, K key3, V value3, K key4, V value4, K key5, V value5, K key6, V value6) {
        return Collections.unmodifiableMap(new HashMap<K, V>() {{
            put(key1, value1);
            put(key2, value2);
            put(key3, value3);
            put(key4, value4);
            put(key5, value5);
            put(key6, value6);
        }});
    }

    public static <K, V> Map<K, V> of(K key1, V value1, K key2, V value2, K key3, V value3, K key4, V value4,
                                      K key5, V value5, K key6, V value6, K key7, V value7) {
        return Collections.unmodifiableMap(new HashMap<K, V>() {{
            put(key1, value1);
            put(key2, value2);
            put(key3, value3);
            put(key4, value4);
            put(key5, value5);
            put(key6, value6);
            put(key7, value7);
        }});
    }

    public static <K, V> Map<K, V> of(K key1, V value1, K key2, V value2, K key3, V value3, K key4, V value4,
                                      K key5, V value5, K key6, V value6, K key7, V value7, K key8, V value8) {
        return Collections.unmodifiableMap(new HashMap<K, V>() {{
            put(key1, value1);
            put(key2, value2);
            put(key3, value3);
            put(key4, value4);
            put(key5, value5);
            put(key6, value6);
            put(key7, value7);
            put(key8, value8);
        }});
    }

    public static <K, V> Map<K, V> of(K key1, V value1, K key2, V value2, K key3, V value3, K key4, V value4,
                                      K key5, V value5, K key6, V value6, K key7, V value7, K key8, V value8,
                                      K key9, V value9) {
        return Collections.unmodifiableMap(new HashMap<K, V>() {{
            put(key1, value1);
            put(key2, value2);
            put(key3, value3);
            put(key4, value4);
            put(key5, value5);
            put(key6, value6);
            put(key7, value7);
            put(key8, value8);
            put(key9, value9);
        }});
    }

    public static <K, V> Map<K, V> of(K key1, V value1, K key2, V value2, K key3, V value3, K key4, V value4,
                                      K key5, V value5, K key6, V value6, K key7, V value7, K key8, V value8,
                                      K key9, V value9, K key10, V value10) {
        return Collections.unmodifiableMap(new HashMap<K, V>() {{
            put(key1, value1);
            put(key2, value2);
            put(key3, value3);
            put(key4, value4);
            put(key5, value5);
            put(key6, value6);
            put(key7, value7);
            put(key8, value8);
            put(key9, value9);
            put(key10, value10);
        }});
    }
}