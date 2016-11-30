package Utils;


import java.io.*;
import java.util.*;

import bin.Card;
import bin.Client;


public class FileHelper {
    private String fileName;

    public FileHelper(String clientName) {
        this.fileName = clientName.replaceAll(" ", "_");
        File fileNumbersCards = new File("DataClients\\allNumberCards.txt");
        if(!fileNumbersCards.exists()) {
            try {
                fileNumbersCards.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void writeClientToFile(Client client) {
        try(FileWriter fileWriter = new FileWriter("DataClients\\" + fileName + ".txt")){
            Map<String, Card> clientCards = client.getMyCards();
            for (Map.Entry<String, Card> card : clientCards.entrySet()) {
                StringBuilder dataClient = new StringBuilder();
                Calendar dateBlockedCard = card.getValue().getHowLongBlockedCard();
                dataClient.append("=======================================\r\n");
                dataClient.append(card.getValue().getNumberCard() + "\r\n");
                dataClient.append(card.getValue().getPin() + "\r\n");
                dataClient.append(card.getValue().getMoney() + "\r\n");
                dataClient.append(String.valueOf(dateBlockedCard.getTime().getTime()) + "\r\n");
                dataClient.append(card.getValue().getCountIncorrectPin() + "\r\n");
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

                client.addCard(new Card(numberCard, pin, money, howLongBlockedCard, countIncorrectPin));
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
