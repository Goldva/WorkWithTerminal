package Utils;

import java.io.*;

public class ConsoleHelper {
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;

    public ConsoleHelper() {
        bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
    }

    public int readInt() {
        int result;
        while (true){
            try {
                result = Integer.parseInt(bufferedReader.readLine());
                return result;
            } catch (NumberFormatException e){
                write("Необходимо вводить только цифры");
            } catch (IOException e) {
                e.printStackTrace();
                return -1;
            }
        }
    }

    public String readString()  {
        try {
            return bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }



    public void write(String text) {
        try {
            bufferedWriter.write(text + "\n");
            bufferedWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
