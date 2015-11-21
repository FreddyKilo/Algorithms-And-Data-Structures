package com.freddykilo.AlgorithmsAndDataStructures;

public class InsertionSort {

	public static void main(String[] args) {
		int[] array = new int[]{6,5,4,3,2,1};
		sort(array);

	}
	
	public static void sort(int[] array){
		int j;
		for(int i=1; i < array.length; i++){
			int key = array[i];
			j = i-1;
			while(j >= 0 && key < array[j]){
				array[j+1] = array[j];
				j--;
			}
			array[j+1] = key;
		}
	}
}
