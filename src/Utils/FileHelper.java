package Utils;


import bin.Card;
import bin.Client;
import decorators.DecoratorCard;

import java.io.*;
import java.util.*;


public class FileHelper {
    private String fileName;

    public FileHelper(String clientName) {
        this.fileName = clientName.replaceAll(" ", "_");
        File fileNumbersCards = new File("DataClients\\allNumberCards.txt");
        if(!fileNumbersCards.exists()) {
            try {
                boolean created = fileNumbersCards.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void writeClientToFile(Client client) {
        try(FileWriter fileWriter = new FileWriter("DataClients\\" + fileName + ".txt")){
            Map<String, DecoratorCard> clientCards = client.getMyCards();
            StringBuilder dataClient;
            for (Map.Entry<String, DecoratorCard> card : clientCards.entrySet()) {
                dataClient = new StringBuilder();
                Calendar dateBlockedCard = card.getValue().getHowLongBlockedCard();
                dataClient.append("=======================================\r\n");
                dataClient.append(card.getValue().getNumberCard()).append("\r\n");
                dataClient.append(card.getValue().getPin()).append("\r\n");
                dataClient.append(card.getValue().getMoney()).append("\r\n");
                dataClient.append(String.valueOf(dateBlockedCard.getTime().getTime())).append("\r\n");
                dataClient.append(card.getValue().getCountIncorrectPin()).append("\r\n");
                fileWriter.write(dataClient.toString());
                fileWriter.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Client readClientFromFile() {
        Client client = new Client(fileName.replaceAll("_", " "));
        try(BufferedReader fileReader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream("DataClients\\" + fileName + ".txt")))) {
            while (fileReader.readLine() != null) {
                String numberCard = fileReader.readLine();
                int pin = Integer.parseInt(fileReader.readLine());
                int money = Integer.parseInt(fileReader.readLine());
                long date = Long.parseLong(fileReader.readLine());
                Calendar howLongBlockedCard = new GregorianCalendar();
                howLongBlockedCard.setTime(new Date(date));
                int countIncorrectPin = Integer.parseInt(fileReader.readLine());

                Card card = new Card(numberCard, pin, money, howLongBlockedCard, countIncorrectPin);
                client.addCard(new DecoratorCard(card));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return client;
    }

    public boolean checkClient(){
        return new File("DataClients\\" + fileName + ".txt").exists();
    }

    public boolean deleteClient(){
        return new File("DataClients\\" + fileName + ".txt").delete();
    }

    public List<String> getAllNumberCards()  {
        List<String> numberCards = new ArrayList<>();
        try(BufferedReader fileReader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream("DataClients\\allNumberCards.txt")))) {
            String number;
            while ((number = fileReader.readLine()) != null) {
                numberCards.add(number);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return numberCards;
    }

    public void deleteNumberCards(String numderForDelete){
        try {
            List<String> numberCards = getAllNumberCards();
            numberCards.remove(numderForDelete);
            try(FileWriter fileWriter = new FileWriter("DataClients\\allNumberCards.txt")){
                for (String number : numberCards){
                    fileWriter.write(number + "\r\n");
                }
                fileWriter.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addNumberCards(String numderForDelete){
        try {
            List<String> numberCards = getAllNumberCards();
            numberCards.add(numderForDelete);
            try(FileWriter fileWriter = new FileWriter("DataClients\\allNumberCards.txt")){
                for (String number : numberCards){
                    fileWriter.write(number + "\r\n");
                }
                fileWriter.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
