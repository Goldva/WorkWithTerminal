package Utils;

import java.io.*;

public class ConsoleHelper {
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;

    public ConsoleHelper() {
        bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
    }

    public int readInt() throws IOException {
        int result;
        while (true){
            try {
                result = Integer.parseInt(bufferedReader.readLine());
                return result;
            } catch (NumberFormatException e){
                write("Необходимо вводить только цифры");
            }
        }
    }

    public String readString() throws IOException {
        return bufferedReader.readLine();
    }



    public void write(String text) throws IOException {
        bufferedWriter.write(text);
    }
}
