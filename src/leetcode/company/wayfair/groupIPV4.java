package leetcode.company.wayfair;


import java.util.ArrayList;
import java.util.List;

// IPv4 address classes are categorized as follows:
// Class A: 0.0.0.0 - 127.255.255.255 (For large networks)
// Class B: 128.0.0.0 - 191.255.255.255 (For medium-sized networks)
// Class C: 192.0.0.0 - 223.255.255.255 (For small networks)
// Class D: 224.0.0.0 - 239.255.255.255 (Reserved for multicast)
// Class E: 240.0.0.0 - 255.255.255.255 (Reserved for experimental use)
// This class provides methods or examples to handle or classify IPv4 addresses.
public class groupIPV4 {

    public static boolean validateIP(String ip) {
        String[] parts = ip.split("\\.");

        if (parts.length != 4) return false;

        for (String part : parts) {
            try {
                int octet = Integer.parseInt(part);
                if (octet < 0 || octet > 255) return false;

            } catch (NumberFormatException e) {
                return false;
            }
        }

        return true;
    }

    public static List<String> classifyIPs(List<String> ips) {
        List<String> result = new ArrayList<>();

        for (String ip : ips) {
            if (validateIP(ip)) {
                int firstOctet = Integer.parseInt(ip.split("\\.")[0]);

                if (firstOctet >= 0 && firstOctet <= 127) result.add("A");

                if (firstOctet >= 128 && firstOctet <= 191) result.add("B");

                if (firstOctet >= 192 && firstOctet <= 223) result.add("C");

                if (firstOctet >= 224 && firstOctet <= 239) result.add("D");

                if (firstOctet >= 240 && firstOctet <= 255) result.add("E");

            } else {
                result.add("Invalid");
            }
        }

        return result;
    }


    public static void main(String[] args) {
        // Create an instance of the groupIPV4 class
        groupIPV4 ipClassifier = new groupIPV4();

        // Test case 1: Valid IP addresses from all classes
        List<String> validIps = new ArrayList<>();
        validIps.add("10.0.0.1");   // Class A
        validIps.add("150.10.10.10"); // Class B
        validIps.add("200.100.50.25"); // Class C
        validIps.add("230.1.2.3");    // Class D (Multicast)
        validIps.add("250.100.100.100"); // Class E (Experimental)

        List<String> validResults = classifyIPs(validIps);
        System.out.println("Test Case 1 - Valid IPs classification: " + validResults);

        // Test case 2: Invalid IP addresses
        List<String> invalidIps = new ArrayList<>();
        invalidIps.add("256.0.0.1");  // Invalid - First octet exceeds 255
        invalidIps.add("192.168.1");  // Invalid - Missing octets
        invalidIps.add("abc.1.1.1");  // Invalid - Non-numeric characters
        invalidIps.add("192.0.0.-1"); // Invalid - Negative octet
        invalidIps.add("10.10.10.256"); // Invalid - Octet exceeds 255

        List<String> invalidResults = classifyIPs(invalidIps);
        System.out.println("Test Case 2 - Invalid IPs classification: " + invalidResults);

        // Test case 3: Edge case IP addresses
        List<String> edgeIps = new ArrayList<>();
        edgeIps.add("0.0.0.0");   // Class A (Lowest in range)
        edgeIps.add("127.255.255.255"); // Class A (Highest in range)
        edgeIps.add("128.0.0.0");   // Class B (Lowest in range)
        edgeIps.add("191.255.255.255"); // Class B (Highest in range)
        edgeIps.add("192.0.0.0");  // Class C (Lowest in range)
        edgeIps.add("223.255.255.255"); // Class C (Highest in range)
        edgeIps.add("224.0.0.0");  // Class D (Lowest in range)
        edgeIps.add("239.255.255.255"); // Class D (Highest in range)
        edgeIps.add("240.0.0.0");  // Class E (Lowest in range)
        edgeIps.add("255.255.255.255"); // Class E (Highest in range)

        List<String> edgeResults = classifyIPs(edgeIps);
        System.out.println("Test Case 3 - Edge IPs classification: " + edgeResults);
    }


}
