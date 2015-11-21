package com.freddykilo.AlgorithmsAndDataStructures;

public class HashFunction {

	public static void main(String[] args) {
		
		String s = "hello world";
		System.out.println(hashIt(s, 24));
	}
	
	/**
	 * Create a hash value of a String
	 * @param string
	 * @return an n-digit hex String
	 */
	public static String hashIt(String string, int hashCodeLength){
		String[] strArray = new String[hashCodeLength/2];
		char[] charArray = string.toCharArray();
		int minLayers = 101;
		int maxIteration = (string.length() < strArray.length) ? strArray.length*2 * minLayers : string.length() * minLayers;
		for(int i = 0; i<maxIteration; i++){
			int sIndex = i%strArray.length;
			int cIndex = i%charArray.length;
			if(strArray[sIndex] == null){
				strArray[sIndex] = getHexValue(charArray[cIndex]);
			} else {
				strArray[sIndex] = getHexValue((char)((getIntValue(strArray[sIndex]) + charArray[cIndex] + i) % 256));
			}
		}
		StringBuilder hashCode = new StringBuilder();
		for(String s : strArray){
			hashCode.append(s);
		}
		return hashCode.toString();
	}
		
	public static String getHexValue(char c){
		int charInt = c;
		String s = Integer.toHexString(charInt);
		if(s.length() == 1) return "0" + s;
		else return s;
	}
	
	public static int getIntValue(String s){
		return Integer.parseInt(s, 16);
	}

}
