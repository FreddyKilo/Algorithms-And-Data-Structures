package com.freddykilo.AlgorithmsAndDataStructures;

public class HashTable {
	KeyValuePair[] array;
	int arraySize = 0;
	int elementCount = 0;
	int totalPairs = 0;
	
	public class KeyValuePair{
		public String key;
		public int value;
		public int size = 0;
		public KeyValuePair next = null;
		
		public KeyValuePair(String key, int value){
			this.key = key;
			this.value = value;
		}
	}

	public HashTable(int size){
		this.array = new KeyValuePair[size];
		arraySize = size;
	}
	
	public void put(String key, int value){
		KeyValuePair pair = new KeyValuePair(key, value);
		int index = hash(key);
		if(array[index] == null){
			elementCount++;
			array[index] = pair;
		}
		else {
			KeyValuePair cursor = array[index];
			while(cursor.next != null){
				cursor = cursor.next;
			}
			cursor.next = pair;
			cursor.next.size++;
		}
		array[index].size++;
		totalPairs++;
	}
	
	public int get(String key){
		int index = hash(key);
		KeyValuePair cursor = array[index];
		while(cursor != null){
			if (cursor.key.equals(key)){
				return cursor.value;
			}
			else cursor = cursor.next;
		}
		return -1;
	}
	
	public int hash(String key){
		return Math.abs(key.hashCode()) % arraySize;
	}

}