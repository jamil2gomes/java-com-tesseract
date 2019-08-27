package com.lp3.util;

public class TrataString {
	
	
public static String removeCharacterSpecials(String filter) {
		
		StringBuilder stringFilter = new StringBuilder();
		
		for (int count = 0; count < filter.length(); count++) {
			
			if ( (filter.charAt(count) >= 65 && filter.charAt(count) <= 90) ||
				 (filter.charAt(count) >= 48 && filter.charAt(count) <= 57) ) {
					stringFilter.append(filter.charAt(count));
			}
			
		}
		
		return stringFilter.toString();
	}



	
}
