import exceptions.SuchUserExistsException;
import interfaces.Terminal;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class WorkTerminal implements Terminal {
    private BufferedReader br;
    private BufferedWriter bw;
    private Map<String,Client> clients;

    public WorkTerminal() {
        this.br = new BufferedReader(new InputStreamReader(System.in));
        this.bw = new BufferedWriter(new OutputStreamWriter(System.out));

        clients = new HashMap<>();
    }


    private StreamTokenizer readSymbols(){
        StreamTokenizer tokenizer = new StreamTokenizer(br);
        try {
            tokenizer.nextToken();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tokenizer;
    }

    private void writeSymbols(String text){
        try {
            bw.write(text);
            bw.newLine();
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int statusScore() {
        return 0;
    }

    @Override
    public void putMoney() {

    }

    @Override
    public void shootMoney() {

    }

    @Override
    public void createClient() {
        while (true) {
            System.out.println("Введите ваше имя");
            try {
                String clientName = readSymbols().sval;
                if (clients.containsKey(clientName))
                    throw new SuchUserExistsException();
                clients.put(clientName, new Client(clientName));
                return;
            } catch (SuchUserExistsException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    @Override
    public void deleteClient(String clientName) {

    }

    @Override
    public void createCard() {

    }

    @Override
    public void deleteCard() {

    }
}
