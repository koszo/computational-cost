package com.researchAndDevelopment.Levi;

import java.util.Arrays;
import java.util.Random;
import java.io.*;

public class SortingAlgorithms {

	private static long comparisons;
	private static long assignments;
	private static int storedArray[][];
	private static int averageCase[][];
	private static int storedAverageCase[][];
	private static Data results[] = new Data[100];


	public static void main(String[] args) {

		averageCase = populateArrayRandom(generateArrays());
		storedAverageCase = averageCase;
		solveBubbleSortProblem();
		
		averageCase = storedAverageCase;
		averageCase = populateArrayRandom(generateArrays());
		solveMergeSortProblem();
		
		averageCase = storedAverageCase;
		averageCase = populateArrayRandom(generateArrays());
		solveQuickSortProblem();

	}

	public static void solveQuickSortProblem() {
		storedArray = populateArrayAscending(generateArrays());
		collectDataQuickSort(storedArray);
		fileCreator(results, "QuickSortBestCase1.csv");
		System.out.println(Arrays.deepToString(results));

		collectDataQuickSort(averageCase);
		fileCreator(results, "QuickSortAverageCase1.csv");
		System.out.println(Arrays.deepToString(results));

		storedArray = populateArrayDescending(generateArrays());
		collectDataQuickSort(storedArray);
		fileCreator(results, "QuickSortWorstCase1.csv");
		System.out.println(Arrays.deepToString(results));
	}

	public static void solveMergeSortProblem() {
		storedArray = populateArrayAscending(generateArrays());
		collectDataMergeSort(storedArray);
		fileCreator(results, "MergeSortBestCase1.csv");
		System.out.println(Arrays.deepToString(results));

		collectDataMergeSort(averageCase);
		fileCreator(results, "MergeSortAverageCase1.csv");
		System.out.println(Arrays.deepToString(results));

		storedArray = populateArrayDescending(generateArrays());
		collectDataMergeSort(storedArray);
		fileCreator(results, "MergeSortWorstCase1.csv");
		System.out.println(Arrays.deepToString(results));

	}

	public static void solveBubbleSortProblem() {
		storedArray = populateArrayAscending(generateArrays());
		collectDataBubbleSort(storedArray);
		fileCreator(results, "BubbleSortBestCase1.csv");
		System.out.println(Arrays.deepToString(results));

		collectDataBubbleSort(averageCase);
		fileCreator(results, "BubbleSortAverageCase1.csv");
		System.out.println(Arrays.deepToString(results));

		storedArray = populateArrayDescending(generateArrays());
		collectDataBubbleSort(storedArray);
		fileCreator(results, "BubbleSortWorstCase1.csv");
		System.out.println(Arrays.deepToString(results));

	}



	/**This takes a 2d array as input and then it runs the sorting algorithm on its
	 * sub arrays. It stores the collected information into the results array which is of "Data" data
	 * type. 
	 * 
	 * @param array
	 */
	public static void collectDataBubbleSort(int[][] array) {

		for (int i = 0, j = 100; i < array.length && j <= 10000; i++, j += 100) {
			bubbleSort(array[i]);
			Data data = new Data(j, comparisons, assignments);
			results[i] = data;
			comparisons = 0;
			assignments = 0;

		}

	}

	/**This takes a 2d array as input and then it runs the sorting algorithm on its
	 * sub arrays. It stores the collected information into the results array which is of "Data" data
	 * type.
	 * 
	 * @param array
	 */
	public static void collectDataMergeSort(int[][] array) {

		for (int i = 0, j = 100; i < array.length && j <= 10000; i++, j += 100) {

			mergeSort(array[i], j);
			Data data = new Data(j, comparisons, assignments);
			results[i] = data;
			comparisons = 0;
			assignments = 0;

		}

	}

	/**This takes a 2d array as input and then it runs the sorting algorithm on its
	 * sub arrays. It stores the collected information into the results array which is of "Data" data
	 * type.
	 * 
	 * @param array
	 */
	public static void collectDataQuickSort(int[][] array) {

		for (int i = 0, j = 100; i < array.length && j <= 10000; i++, j += 100) {

			quickSort(array[i], i, j - 1);
			Data data = new Data(j, comparisons, assignments);
			results[i] = data;
			comparisons = 0;
			assignments = 0;

		}

	}

	/**
	 * The first method is quickSort() which takes as parameters the array to be
	 * sorted, the first and the last index. First, we check the indices and
	 * continue only if there are still elements to be sorted.
	 * 
	 * We get the index of the sorted pivot and use it to recursively call
	 * partition() method with the same parameters as the quickSort() method, but
	 * with different indices
	 * 
	 * @param array
	 * @param first element
	 * @param last  element
	 */
	public static void quickSort(int arr[], int begin, int end) {
		
		comparisons++;
		if (begin < end) {
			
			int partitionIndex = partition(arr, begin, end);
			assignments++;

			quickSort(arr, begin, partitionIndex - 1);
			quickSort(arr, partitionIndex + 1, end);
		}
	}

	/**
	 * Let’s continue with the partition() method. For simplicity, this function
	 * takes the last element as the pivot. Then, checks each element and swaps it
	 * before the pivot if its value is smaller.
	 * 
	 * By the end of the partitioning, all elements less then the pivot are on the
	 * left of it and all elements greater then the pivot are on the right of it.
	 * The pivot is at its final sorted position and the function returns this
	 * position
	 */
	private static int partition(int arr[], int begin, int end) {
		int pivot = arr[end];
		assignments++;
		int i = (begin - 1);
		assignments++;

		for (int j = begin; j < end; j++) {
			
			comparisons++;
			if (arr[j] <= pivot) {
				i++;

				int swapTemp = arr[i];
				assignments++;
				arr[i] = arr[j];
				assignments++;
				arr[j] = swapTemp;
				assignments++;
			}
		}

		int swapTemp = arr[i + 1];
		assignments++;
		arr[i + 1] = arr[end];
		assignments++;
		arr[end] = swapTemp;
		assignments++;

		return i + 1;
	}

	/**
	 * It takes as input the array and its length For the implementation, we’ll
	 * write a mergeSort function which takes in the input array and its length as
	 * the parameters. This will be a recursive function so we need the base and the
	 * recursive conditions.
	 * 
	 * The base condition checks if the array length is 1 and it will just return.
	 * For the rest of the cases, the recursive call will be executed.
	 * 
	 * For the recursive case, we get the middle index and create two temporary
	 * arrays l[] and r[]. The mergeSort function is then called recursively for
	 * both the sub-arrays
	 * 
	 * @param array
	 * @param       array's length
	 */
	public static void mergeSort(int[] a, int n) {
		
		comparisons++;
		if (n < 2) {
			return;
		}
		int mid = n / 2;
		assignments++;
		int[] l = new int[mid];
		assignments++;
		int[] r = new int[n - mid];
		assignments++;

		for (int i = 0; i < mid; i++) {
			l[i] = a[i];
			assignments++;
		}
		for (int i = mid; i < n; i++) {
			r[i - mid] = a[i];
			assignments++;
		}
		mergeSort(l, mid);
		mergeSort(r, n - mid);

		merge(a, l, r, mid, n - mid);

	}

	/**
	 * We then call the merge function which takes in the input and both the
	 * sub-arrays and the starting and end indices of both the sub arrays.
	 * 
	 * The merge function compares the elements of both sub-arrays one by one and
	 * places the smaller element into the input array.
	 * 
	 * When we reach the end of one of the sub-arrays, the rest of the elements from
	 * the other array are copied into the input array thereby giving us the final
	 * sorted array
	 */
	public static void merge(int[] a, int[] l, int[] r, int left, int right) {

		int i = 0;
		assignments++;
		int j = 0;
		assignments++;
		int k = 0;
		assignments++;

		while (i < left && j < right) {
			
			if (l[i] <= r[j]) {
				comparisons++;
				a[k++] = l[i++];
				assignments++;
			} else {
				a[k++] = r[j++];
				assignments++;
			}
		}

		while (i < left) {
			a[k++] = l[i++];
			assignments++;
		}

		while (j < right) {
			a[k++] = r[j++];
			assignments++;
		}
	}

	/**
	 * As the method name suggests it is used to sort an array using the BubbleSort
	 * algorithm
	 * 
	 * @param array
	 */
	public static void bubbleSort(int[] array) {

		for (int i = 0; i < array.length -1; i++) {
			
			for (int j = 0; j < array.length-i-1; j++) {
				comparisons++;
				if (array[j] > array[j+1]) {
					assignments++;
					int temp = array[j];
					assignments++;
					array[j] = array[j+1];
					assignments++;
					array[j+1] = temp;
				}
			}

		}

	}

	/**
	 * This method generates a 2d array with 100 sub arrays of varying sizes. The
	 * sub arrays range from a size of 100 up to 10000 the increment used = 100.
	 */
	public static int[][] generateArrays() {

		int[][] array = new int[100][];
		for (int i = 0, j = 100; i < 100 && j <= 10000; i++, j += 100) {

			array[i] = new int[j];

		}
		return array;
	}

	/**
	 * As the method name suggests it is used to populate arrays with random
	 * elements
	 */
	public static int[][] populateArrayRandom(int[][] array) {

		Random random = new Random();

		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < array[i].length; j++) {
				array[i][j] = random.nextInt(array[i].length)+1;
			}
		}

		return array;
	}

	/**
	 * As the method name suggests it is used to populate arrays with ascending
	 * elements
	 */
	public static int[][] populateArrayAscending(int[][] array) {

		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < array[i].length; j++) {
				array[i][j] = j;
			}
		}

		return array;
	}

	/**
	 * As the method name suggests it is used to populate arrays with descending
	 * elements
	 */
	public static int[][] populateArrayDescending(int[][] array) {

		for (int i = 0; i < 100; i++) {
			for (int j = array[i].length - 1, k = 0; j >= 0 && k < array[i].length; j--, k++) {
				array[i][k] = j;
			}
		}

		return array;
	}

	/**
	 * It is used to create a file based upon the "Data" - data type, which has a
	 * modified toString method to suit printing data into .csv file format. It
	 * prints as follows: Nr. of inputs + Nr. of comparisons + Nr. of Assignments +
	 * Total number of operations
	 */
	public static void fileCreator(Data[] array, String fileName) {
		try {

			File file = new File(fileName);

			if (!file.exists()) {
				file.createNewFile();
			}

			PrintWriter pw = new PrintWriter(file);
			pw.println(Arrays.deepToString(array));
			pw.close();
			System.out.println("Done");

		} catch (IOException e) {

			e.printStackTrace();
		}
	}

}
