package dsa.conversion;

public class BinaryConverter {


    // Method to convert binary string to decimal
    public static int binaryToDecimal(String binary) {
        return Integer.parseInt(binary, 2);
    }


    // Method to convert binary string to octal
    public static String binaryToOctal(String binary) {
        int decimal = Integer.parseInt(binary, 2);
        return Integer.toOctalString(decimal);
    }

    // Method to convert binary string to hexadecimal
    public static String binaryToHexadecimal(String binary) {
        int decimal = Integer.parseInt(binary, 2);
        return Integer.toHexString(decimal).toUpperCase();
    }


    public static void main(String[] args) {
        String binaryStr = "101010";  // Example binary string

        // Convert binary to decimal
        int decimalResult = binaryToDecimal(binaryStr);
        System.out.println("Binary to Decimal: " + decimalResult);

        // Convert binary to octal
        String octalResult = binaryToOctal(binaryStr);
        System.out.println("Binary to Octal: " + octalResult);

        // Convert binary to hexadecimal
        String hexResult = binaryToHexadecimal(binaryStr);
        System.out.println("Binary to Hexadecimal: " + hexResult);
    }
}
