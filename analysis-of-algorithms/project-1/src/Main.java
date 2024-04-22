import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) {
		int[] sizes = {10, 100, 1000, 10000, 100000};
		String directoryName = "sample-inputs";
		File directory = new File(directoryName);
		
		if (!directory.exists()) {
			if (directory.mkdir()) {
				System.out.println("Directory created successfully under the folder: " + directoryName);
			} else {
				System.out.println("Failed to create sample inputs directory");
				return;
			}
		}
		
		for (int size : sizes) {
			int[] randomOrder = RandomArrayGenerator.generateRandomArray(size);
			int[] sortedOrder = Arrays.copyOf(randomOrder, randomOrder.length);
			Arrays.sort(sortedOrder);
			int[] reverseOrder = Arrays.copyOf(sortedOrder, sortedOrder.length);
			for (int i = 0; i < reverseOrder.length / 2; i++) {
				int temp = reverseOrder[i];
				reverseOrder[i] = reverseOrder[reverseOrder.length - i - 1];
				reverseOrder[reverseOrder.length - i - 1] = temp;
			}
			
			String[] orderTypes = {"randomOrder", "sortedOrder", "reverseOrder"};
			int[][] arrays = {randomOrder, sortedOrder, reverseOrder};
			
			for (int i = 0; i < orderTypes.length; i++) {
				String filePath = directoryName + File.separator + orderTypes[i] + "Size" + size + ".txt";
				File file = new File(filePath);
				if (!file.exists()) {
					try {
						FileHandler.writeFile(arrays[i], filePath);
						System.out.println("Created " + filePath);
					} catch (IOException e) {
						System.out.println("Failed to write to " + filePath + ": " + e.getMessage());
					}
				}
			}
		}
		
		Main.seperator();
		System.out.println("Based on Insertion Sort Algorithm");
		for (int size : sizes) {
			for (String orderType : new String[]{"randomOrder", "sortedOrder", "reverseOrder"}) {
				String filePath = directoryName + File.separator + orderType + "Size" + size + ".txt";
				try {
					int[] array = FileHandler.readFile(filePath);
					InsertionSort.Result result = InsertionSort.sortAndFindMedian(array);
					System.out.printf("%sSize%d: Median = %.2f, Time Elapsed = %d ms (%d ns)%n",
									orderType, size, result.median, result.timeElapsedMillis, result.timeElapsedNanoseconds);
				} catch (IOException e) {
					System.out.println("An error occurred while reading from the file: " + e.getMessage());
				}
			}
		}
		
		Main.seperator();
		System.out.println("Based on Merge Sort Algorithm");
		for (int size : sizes) {
			for (String orderType : new String[]{"randomOrder", "sortedOrder", "reverseOrder"}) {
				String filePath = directoryName + File.separator + orderType + "Size" + size + ".txt";
				try {
					int[] array = FileHandler.readFile(filePath);
					MergeSort.Result result = MergeSort.sortAndFindMedian(array);
					System.out.printf("%sSize%d: Median = %.2f, Time Elapsed = %d ms (%d ns)%n",
									orderType, size, result.median, result.timeElapsedMillis, result.timeElapsedNanoseconds);
				} catch (IOException e) {
					System.out.println("An error occurred while reading from the file: " + e.getMessage());
				}
			}
		}
		
		Main.seperator();
		
	}
	
	public static void seperator() {
		for (int i = 0; i < 70; i++) {
			System.out.print("-");
		}
		System.out.println("\n");
	}
}
