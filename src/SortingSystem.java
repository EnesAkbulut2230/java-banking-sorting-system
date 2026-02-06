import java.util.Comparator;

public class SortingSystem {
    private static final int INSERTION_SORT_THRESHOLD = 10;
    public static <T> void hybridSort(T[] array, Comparator<? super T> comparator) {
        hybridSort(array, comparator, 0, array.length - 1);
    }

    private static <T> void hybridSort(T[] array, Comparator<? super T> comparator, int min, int max) {
        int numberOfElement = max - min + 1;
        if (numberOfElement <= INSERTION_SORT_THRESHOLD) {
            insertionSort(array, comparator, min, max);
        } else {
            int pivotIndex = partition(array, comparator, min, max);
            hybridSort(array, comparator, min, pivotIndex - 1);
            hybridSort(array, comparator, pivotIndex + 1, max);
        }
    }

    // Lomuto
    private static <T> int partition(T[] array, Comparator<? super T> comparator, int min, int max) {
        T lastElement = array[max];
        int i = min - 1;
        for (int j = min; j < max; j++) {
            if (comparator.compare(array[j], lastElement) <= 0) {
                swapTwoElementsHelper(array, ++i, j);
            }
        }
        swapTwoElementsHelper(array, i + 1, max);
        return i + 1;
    }

    private static <T> void insertionSort(T[] array, Comparator<? super T> comparator, int min, int max) {
        for (int i = min + 1; i <= max; i++) {
            T theKey = array[i];
            int j = i - 1;
            while (j >= min && comparator.compare(array[j], theKey) > 0) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = theKey;
        }
    }

    private static <T> void swapTwoElementsHelper(T[] array, int i, int j) {
        T tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }
}
