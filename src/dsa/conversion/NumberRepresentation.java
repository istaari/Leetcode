package dsa.conversion;

public class NumberRepresentation {

    public static void main(String[] args) {
        // Binary representation (0b prefix)
        int binaryNum = 0b1010;  // Binary for 10
        System.out.println("Binary 0b1010 in decimal: " + binaryNum);

        // Decimal representation (no prefix)
        int decimalNum = 10;  // Decimal number
        System.out.println("Decimal 10: " + decimalNum);

        // Octal representation (0 prefix)
        int octalNum = 012;  // Octal for 10
        System.out.println("Octal 012 in decimal: " + octalNum);

        // Hexadecimal representation (0x prefix)
        int hexNum = 0xA;  // Hexadecimal for 10
        System.out.println("Hexadecimal 0xA in decimal: " + hexNum);
    }
}

