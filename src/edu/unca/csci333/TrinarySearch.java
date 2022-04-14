package edu.unca.csci333;

import java.util.Random;

/*
 * Kedar McGill
 * 01/20/2022
 * CSCI 333
 * Homework 1 - Trinary Search Tree
 *  Searches for a random number in different integer arrays using trinarySearch
 *  The program looks for a randomized number inside of the array using trinary search tree and if it is not there it outputs -1
	as the index but if it is there it shows the proper index from 0 to whatever the proper index is.
 */
public class TrinarySearch {

	public static void main(String[] args) {
		Random rand = new Random();
		int[] test1 = {1,2,3,4,4,5,6,7,9,10};
		int[] test2 = {11,12,13,14,15,16,17,18,19,20};
		int[] test3 = {4,8,6,9,9};
		int[] test4 = {7,14};
		int[] test5 = {-7,-1,0,1,2,2};
		
		int[][] tests = {test1,test2,test3,test4,test5};
		
		for(int i=0; i < tests.length; i++) {
			System.out.print("Test "+(i+1)+" input: ");
			printArray(tests[i]);
			int max = tests[i][tests[i].length-1];
			int min = tests[i][0];
			int target = rand.nextInt(max-min+1)+min;
			System.out.println("Searching for: " + target);
			int result = trinarySearch(tests[i],0,tests[i].length-1,target);
			System.out.println(target+" is at index "+result+"\n");
		}
	}
	
	/**
	 * Recursive function that searches for a given integer in a sorted array or array 
	 * subsection by dividing the array into three problem sets when possible
	 * @param array Integer array sorted in increasing order
	 * @param lowBound Smallest index in array or sub-array
	 * @param upBound Largest index in array or sub-array
	 * @param target Integer to search for
	 * @return index of target integer, -1 if integer not found
	 */
	public static int trinarySearch(int[] array, int lowBound, int upBound, int target) {
		// Base case
		if((upBound - lowBound) < 4) { // if there aren't enough elements to split into three parts with two bounds search sequentially
			for(int i = lowBound; i <= upBound; i++) {
				if(array[i] == target) {
					return i;
				}
			}
			return -1;	// return -1 if element not found
		}
		
		// Find boundary indices
		int partLength = (upBound - lowBound)/3;
		int bound1 = lowBound + partLength;
		int bound2 = bound1 + partLength + 1;
		
		if(target <= array[bound1]) {
			// Recurse to first section
			int part1 = trinarySearch(array,lowBound,bound1,target);
			return part1;
		}
		if(target <= array[bound2]) {
			// Recurse to second section
			int part2 = trinarySearch(array,bound1+1,bound2,target);
			return part2;
		}
		// recurse to section 3 if target not in 1 or 2
		int part3 = trinarySearch(array,bound2+1,upBound,target);
		return part3;
	}
	
	private static void printArray(int[] array) {
		String print = "{";
		for (int i=0; i<array.length; i++) {
			print += array[i] + ", " ;
		}
		print += array[array.length-1] + "}";
		System.out.println(print);
	}
}
