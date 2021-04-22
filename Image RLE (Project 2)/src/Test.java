public class Test {

    public static void main(String[] args) {
        byte[] test = (RleProgram.stringToRle("28:10:6B:10:10B:10:2B:10:12B:10:2B:10:5B:20:11B:10:6B:10"));
        byte[] testX = RleProgram.stringToData("28106B10AB102B10CB102B105B20BB106B10");

        for (int i = 0; i < test.length;i++) {
            System.out.print(test[i] + " ");
        }
        System.out.println();
        for (int i = 0; i <testX.length;i++)
        System.out.print(testX[i] + " ");

    }
}
