package leetcode.stack;

public class CrawlerLogFolder {

    public static int minOperations(String[] logs) {
        int counter = 0;

        for (String op : logs) {
            if (op.equals("../")) {
                if (counter > 0) {
                    counter--;
                }
            } else if (!op.equals("./")) {
                counter++;
            }
        }

        return counter;
    }


    public static void main(String[] args) {
        String[] logs = {"./", "wz4/", "../", "mj2/", "../", "../", "ik0/", "il7/"};
        System.out.println(minOperations(logs));
    }

}
