import exceptions.AccountBlockedException;

import java.io.*;

public class Main {
    public static void main(String[] args) throws AccountBlockedException {
        StreamTokenizer tokenizer;
        BufferedReader br1 = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            try {
                String s = br1.readLine();

                System.out.println(String.format("%03d",Integer.parseInt(s)));


            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
