package com.freddykilo.AlgorithmsAndDataStructures;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MergeSort {

	static Random random = new Random();
	
	public static void main(String[] args) {
		List<Integer> randomList = new ArrayList<>();
		List<Integer> emptyList = new ArrayList<>();
		
		int listSize = 16;
		int i = 0;
		while (randomList.size() < listSize) {
			int element = random.nextInt(listSize)+1;
			if (!randomList.contains(element)) {
				randomList.add(i, element);
				emptyList.add(0);
				i++;
			}
		}
		System.out.println(randomList);
		System.out.println(mergeSort(randomList, emptyList, 0, randomList.size()));
	}
	
	public static List<Integer> mergeSort(List<Integer> listA, List<Integer> listB, int iBegin, int iEnd) {
		
		if (iEnd - iBegin < 2) {
			return null;
		}
		int iMiddle = (iEnd + iBegin) / 2;
		mergeSort(listA, listB, iBegin, iMiddle);
		mergeSort(listA, listB, iMiddle, iEnd);
		merge(listA, listB, iBegin, iMiddle, iEnd);
		copyArray(listA, listB, iBegin, iEnd);
		return listA;
	}
	
	public static void merge(List<Integer> listA, List<Integer> listB, int iBegin, int iMiddle, int iEnd) {
		int firstHalfIndex = iBegin;
		int secondHalfIndex = iMiddle;
		for (int j = iBegin; j < iEnd; j++) {
			if (firstHalfIndex < iMiddle && (secondHalfIndex >= iEnd || listA.get(firstHalfIndex) <= listA.get(secondHalfIndex))) {
				listB.set(j, listA.get(firstHalfIndex));
				firstHalfIndex = firstHalfIndex + 1;
			} else {
				listB.set(j, listA.get(secondHalfIndex));
				secondHalfIndex = secondHalfIndex + 1;
			}
		}
	}
	
	public static void copyArray(List<Integer> listA, List<Integer> listB, int iBegin, int iEnd) {
		for(int i = iBegin; i < iEnd; i++) {
			listA.set(i, listB.get(i));
		}
	}
}
