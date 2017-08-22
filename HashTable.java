package com.freddykilo.AlgorithmsAndDataStructures;

import java.util.NoSuchElementException;
import java.util.UUID;

class HashTable {
    private KeyValuePair[] array;
    private int populatedCount = 0;
    private int totalCount = 0;
    private int maxLinkCount = 0;
    private static final int TABLE_SIZE = 10_000_000;

    public static void main(String[] args) {
        HashTable hashTable = new HashTable(TABLE_SIZE);
        for (int i = 0; i < TABLE_SIZE; i++) {
            hashTable.put(UUID.randomUUID().toString(), i);
            if (i % 100_000 == 0) System.out.print(".");
        }
        System.out.println(hashTable.get("lola"));
    }

    public HashTable(int size) {
        this.array = new KeyValuePair[size];
    }

    private void put(String key, int value) {
        KeyValuePair kvp = new KeyValuePair(key, value);
        int index = hash(key);
        int linkCount = 1;
        if (array[index] == null) {
            array[index] = kvp;
            populatedCount++;
        } else {
            KeyValuePair cursor = array[index];
            while (cursor.next != null) {
                cursor = cursor.next;
                linkCount ++;
            }
            linkCount ++;
            cursor.next = kvp;
        }
        if (linkCount > maxLinkCount) maxLinkCount = linkCount;
        totalCount++;
    }

    private Object get(String key) {
        int index = hash(key);
        KeyValuePair cursor = array[index];
        while (cursor != null) {
            if (cursor.key.equals(key)) {
                return cursor.value;
            } else cursor = cursor.next;
        }
        throw new NoSuchElementException("Element with key '" + key + "' was not found within the HashTable");
    }

    private int hash(String key) {
        return Math.abs(key.hashCode()) % TABLE_SIZE;
    }

    /**
     * This is just a singly linked list node with key and value data
     */
    private class KeyValuePair {
        private String key;
        private Object value;
        private KeyValuePair next = null;

        private KeyValuePair(String key, int value) {
            this.key = key;
            this.value = value;
        }
    }

}
