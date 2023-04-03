import com.murmylo.volodymyr.HashMapImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HashMapTest {

    @Test
    void putThenGetSingleValue() {
        HashMapImpl<String, Integer> hashMap = new HashMapImpl<>();
        int value = 12;
        String key = "abcd";
        hashMap.put(key, value);
        assertEquals(value, hashMap.get("abcd"));
    }

    @Test
    void whenPut2ValuesWithSameKeyReturnsLatestValue() {
        HashMapImpl<String, Integer> hashMap = new HashMapImpl<>();
        int value1 = 12;
        int value2 = 17;
        String key = "abcd";
        hashMap.put(key, value1);
        hashMap.put(key, value2);
        assertEquals(value2, hashMap.get("abcd"));
    }

    @Test
    void whenPut2ValuesReturnsSingleValueInValues() {
        HashMapImpl<String, Integer> hashMap = new HashMapImpl<>();
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
        HashMapImpl<String, Integer> hashMap = new HashMapImpl<>();
        String key = "abcd";
        hashMap.put(key, 12);
        hashMap.put(key, 15);
        hashMap.put(key, 17);
        hashMap.put("aba", 19);
        assertEquals(2, hashMap.keySet().size());
    }
}
