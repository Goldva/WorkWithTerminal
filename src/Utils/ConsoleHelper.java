package Utils;

import java.io.*;

public class ConsoleHelper {
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;

    private volatile static ConsoleHelper consoleHelper = null;

    public static ConsoleHelper getInstance(){
        if (consoleHelper == null){
            synchronized (ConsoleHelper.class) {
                if (consoleHelper == null)
                    consoleHelper = new ConsoleHelper();
            }
        }
        return consoleHelper;
    }

    public ConsoleHelper() {
            bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
    }

    public synchronized int readInt() {
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

    public synchronized String readString()  {
        try {
            return bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }



    public synchronized void write(String text) {
        try {
            bufferedWriter.write(text + "\n");
            bufferedWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
