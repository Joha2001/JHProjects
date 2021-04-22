public class Analyzer {

    public static void main(String[] args){//Main method
        String[] dataSet = StringData.getData(); //Uses StringData.java to get String array.
        testSearches(dataSet,"not_here"); //Tests for "not_Here"
        testSearches(dataSet, "mzzzz"); //Tests for "mzzzz"
        testSearches(dataSet, "aaaaa"); //Tests for "aaaaa"
    }

    public static int linearSearch(String[] dataSet, String element){//Uses a linear search to find specified element in the dataSet. Returns index of element, or -1 if not found.
        for (int i = 0; i < dataSet.length; i++){//Checks every i until it finds the string
            if (dataSet[i].equals(element))
                return i;//If found, returns index of the found String
        }
        return -1;//If the String is not found, returns -1.
    }

    public static int binarySearch(String[] dataSet, String element){//Uses a binary search to find specified element in the dataSet. Returns index of element, or -1 if not found.
        int low = 0;
        int high = dataSet.length - 1;
        while (low <= high){
            int middle = (high+low)/2;
            if (dataSet[middle].compareTo(element) > 0)
                high = middle - 1;
            else if (dataSet[middle].compareTo(element) < 0)
                low = middle + 1;
            else
                return middle;
        }
        return -1;
    }

    public static void testSearches(String[] dataSet, String test){//Tests both the searches and gives index found and time taken for search.
        long start = System.nanoTime();
        int search = linearSearch(dataSet,test);
        System.out.println("The index of the String " + test + " was: " + search);
        long end = System.nanoTime();
        System.out.println("The time it took to complete linearSearch for " + test + " was: " + (end-start));
        start = System.nanoTime();
        binarySearch(dataSet,test);
        System.out.println("The index of the String " + test + " was: " + search);
        end = System.nanoTime();
        System.out.println("The time it took to complete binarySearch for " + test + " was: " + (end-start));
    }

}
