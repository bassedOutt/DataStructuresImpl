import com.murmylo.volodymyr.HashMapImpl;
import com.murmylo.volodymyr.hashmap.HashMap;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HashMapTest {

    @Test
    void putThenGetSingleValue() {
        HashMap<String, Integer> hashMap = new HashMapImpl<>();
        int value = 12;
        String key = "abcd";
        hashMap.put(key, value);
        assertEquals(value, hashMap.get("abcd"));
    }

    @Test
    void whenPut2ValuesWithSameKeyReturnsLatestValue() {
        HashMap<String, Integer> hashMap = new HashMapImpl<>();
        int value1 = 12;
        int value2 = 17;
        String key = "abcd";
        hashMap.put(key, value1);
        hashMap.put(key, value2);
        assertEquals(value2, hashMap.get("abcd"));
    }

    @Test
    void whenPut2ValuesReturnsSingleValueInValues() {
        HashMap<String, Integer> hashMap = new HashMapImpl<>();
        int value1 = 12;
        int value2 = 17;
        String key = "abcd";
        hashMap.put(key, value1);
        hashMap.put(key, value2);
        assertEquals(value2, hashMap.get("abcd"));
        assertEquals(1, hashMap.values().size());
    }

    @Test
    void whenPutDuplicateKeysKeySetReturnsNoDuplicates() {
        HashMap<String, Integer> hashMap = new HashMapImpl<>();
        String key = "abcd";
        hashMap.put(key, 12);
        hashMap.put(key, 15);
        hashMap.put(key, 17);
        hashMap.put("aba", 19);
        assertEquals(2, hashMap.keySet().size());
    }

    @Test
    void shouldBeAbleToStoreNullKey() {
        HashMap<String, Integer> map = new HashMapImpl();
        map.put(null, 12);
        assertEquals(12, map.get(null));
    }
}
