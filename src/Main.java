import exceptions.AccountBlockedException;

import java.io.*;

public class Main {
    public static void main(String[] args) throws AccountBlockedException {
        StreamTokenizer tokenizer;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        tokenizer = new StreamTokenizer(br);
        while (true) {
            try {
                tokenizer.nextToken();
                System.out.println(tokenizer.nval);
                System.out.println(tokenizer.sval);


            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
