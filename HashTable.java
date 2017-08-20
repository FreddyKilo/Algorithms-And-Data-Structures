package com.freddykilo.AlgorithmsAndDataStructures;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class HashTable {
    KeyValuePair[] array;
    int arraySize = 0;
    int elementCount = 0;
    int totalPairs = 0;

    public HashTable() {
        this.array = new KeyValuePair[4];
        arraySize = 4;
    }

    // Comment to run unit test
    public HashTable(int size) {
        this.array = new KeyValuePair[size];
        arraySize = size;
    }

    public void put(String key, int value) {
        KeyValuePair pair = new KeyValuePair(key, value);
        int index = hash(key);
        if (array[index] == null) {
            elementCount++;
            array[index] = pair;
        } else {
            KeyValuePair cursor = array[index];
            while (cursor.next != null) {
                cursor = cursor.next;
            }
            cursor.next = pair;
            cursor.next.size++;
        }
        array[index].size++;
        totalPairs++;
    }

    public int get(String key) {
        int index = hash(key);
        KeyValuePair cursor = array[index];
        while (cursor != null) {
            if (cursor.key.equals(key)) {
                return cursor.value;
            } else cursor = cursor.next;
        }
        return -1;
    }

    public int hash(String key) {
        return Math.abs(key.hashCode()) % arraySize;
    }

    private class KeyValuePair {
        public String key;
        public int value;
        public int size = 0;
        public KeyValuePair next = null;

        public KeyValuePair(String key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    @Test
    public void unitTest() {
        HashTable hashTable = new HashTable();
        hashTable.put("freddy", 40);
        hashTable.put("megan", 36);
        hashTable.put("peeps", 9);
        hashTable.put("lola", 8);
        hashTable.put("lannie", 69);
        hashTable.put("barb", 67);
        hashTable.put("paco", 3);
        assertTrue(hashTable.get("lola") == 8);
    }

}
