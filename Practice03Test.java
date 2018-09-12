import java.util.Random;


public class Practice03Test {

	protected int count = 0;
	protected double [] arr;
	/* Added to be called by findMinRec */
	int lower = 0;
	int upper = 0;

	/**
	 * Constructor
	 */
	public Practice03Test (String [] args) {
		try {
			count = Integer.parseInt(args[0]);
		} catch (Exception e) {
			System.out.println("Defaulting array size to 20.");
			count = 20;
		}
		arr = new double[count];
		generate_array();
	}


	/**
	 * print_array: prints the array of doubles... formatted so it fits
	 * ... on many small screens.
	 */
	public void print_array() {
		System.out.println("------------------------------------");
		System.out.println("Array contains the values:");
		for (int i = 0; i < arr.length; i++) {
			System.out.printf("%.2f ", arr[i]);
			if (i > 0 && i % 9 == 0)
				System.out.println();
		}
		System.out.println("\n------------------------------------");
	}


	/**
	 * Fills the array with random double instances.
	 */
	public void generate_array() {
		Random rand = new Random();
		double min = 1.0;
		double max = 100.0;

		for (int i = 0; i < arr.length; i++) {
			arr[i] = min + rand.nextDouble() * (max-min);
		}
	}


	public int find_min_iterative() {
		/* Set min as first index in array to start */
		int min = 0;
		/* Start at next number in array to compare */
		for(int i = 1; i < arr.length; i++){
			if(arr[i] < arr[min]){
				/* If number in array is less than min value, set index as new min */
				min = i;
			}
		}
		return min;
	}

	public int find_min_recursive() {
		/* lower still = 0, upper is length of array */
		upper = arr.length-1;
		return findMinRec(lower, upper);
	}

	public int findMinRec (int lower, int upper){
		int min = 0;
		int lowerMin = 0;
		int upperMin = 0;
		/* Bisect array */
		int mid = (lower+upper)/2;

		/* Base case */
		if(lower == upper){
			/* If array is empty, lower is default min index */
			return lower;
		}

		/* Else, split and search recursively like binary search */
		/* Get min on lower side */
		lowerMin = findMinRec(lower, mid);

		/* Get min on upper side*/
		upperMin = findMinRec(mid+1, upper);

		/* Get lowest min between two sides of array */
		if(arr[lowerMin] < arr[upperMin]){
			min = lowerMin;
		} else {
			min = upperMin;
		}
		return min;
	}


	/**
	 * print_min: determines the min iteratively and recursively.
	 * ... and prints them both.
	 */
	public void print_min() {
		System.out.println("Iteratively determined min at index " + find_min_iterative());
		System.out.println("Recursively determined min at index " + find_min_recursive());
	}


	/**
	 * main for Practice 03: print the array and determine the min.
	 */
	public static void main(String [] args) {
		Practice03Test test = new Practice03Test(args);
		test.print_array();
		test.print_min();
	}

}
