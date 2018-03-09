/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketbasketanalysis;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Asus
 */
public class MarketBasketAnalysis {

    /**
     * @param args the command line arguments
     */
    private static final double THRESHOLD = 0.4;

    public static ArrayList inisialisasi() {
        ArrayList<String> items = new ArrayList<>();
        ArrayList<ArrayList<String>> transaction = new ArrayList<>();
        
        String path = JOptionPane.showInputDialog(null, "Masukkan path file transaksi: ");
        
        File file = new File(path);
        
        try {
            BufferedReader buffReader = new BufferedReader(new FileReader(file));
            String buffer = buffReader.readLine();
            
            while (buffer != null) {
                String[] temp = buffer.split(" ");
                items.addAll(Arrays.asList(temp));
                transaction.add(items);
                buffer = buffReader.readLine();
                items = new ArrayList<>();
            }
            
        } catch (IOException ex) {
            Logger.getLogger(MarketBasketAnalysis.class.getName()).log(Level.SEVERE, null, ex);
        }

        return transaction;
    }
    
    public static HashMap probabilityCount(HashMap<String, Integer> itemOccurence, ArrayList<ArrayList> transaction){
        HashMap<String, Double> itemProbability = new HashMap<>();

        for (Map.Entry<String, Integer> entry : itemOccurence.entrySet()) {
            String key = entry.getKey();
            int value = entry.getValue();
            double probability = (double) value / transaction.size();
            itemProbability.put(key, probability);
        }
        
        return itemProbability;
    }

    public static HashMap firstProbabilityCount(ArrayList<ArrayList> transaction) {
        HashMap<String, Integer> itemOccurence = new HashMap<>();
        
        for (ArrayList<String> arrayList : transaction) {
            for (String item : arrayList) {
                if (!itemOccurence.containsKey(item)) {
                    itemOccurence.put(item, 1);
                } else {
                    itemOccurence.put(item, (itemOccurence.get(item) + 1));
                }
            }
        }

        return probabilityCount(itemOccurence, transaction);
    }

    public static HashMap secondProbabilityCount(ArrayList<String> combination, ArrayList<ArrayList> transaction) {
        HashMap<String, Integer> itemOccurence = new HashMap<>();
        
        for (int i = 0; i < combination.size(); i++) {
            for (int j = i + 1; j < combination.size(); j++) {
                for (ArrayList<String> list : transaction) {
                    if (list.contains(combination.get(i)) && list.contains(combination.get(j))) {
                        String key = combination.get(i) + " " + combination.get(j);
                        if (!itemOccurence.containsKey(key)) {
                            itemOccurence.put(key, 1);
                        } else {
                            itemOccurence.put(key, itemOccurence.get(key) + 1);
                        }
                    }
                }

            }
        }

        return probabilityCount(itemOccurence, transaction);
    }

    public static ArrayList<String> removeItemByThreshold(HashMap<String, Double> transaction) {
        ArrayList<String> removedKey = new ArrayList<>();
        ArrayList<String> result = new ArrayList<>();

        for (Map.Entry<String, Double> entry : transaction.entrySet()) {
            String key = entry.getKey();
            Double value = entry.getValue();

            if (value < THRESHOLD) {
                removedKey.add(key);
            }
        }

        for (String key : removedKey) {
            transaction.remove(key);
        }

        for (Map.Entry<String, Double> entry : transaction.entrySet()) {
            String key = entry.getKey();

            result.add(key);
        }

        return result;
    }

    public static void main(String[] args) {
        // TODO code application logic here
        /*
            Isi ArrayList of ArrayList String of transaction dengan file data yang diinput
            Itung probabilitas setiap item terhadap keseluruhan transaksi
            Hilangkan item yang probabilitasnya di bawah THRESHOLD (0.4)
            Itung kembali probabilitas kombinasi dari 2 item terhadap keseluruhan transaksi
            Tampilkan hasilnya
        */
        ArrayList<ArrayList> transaction = inisialisasi();
        HashMap<String, Double> itemProbability = firstProbabilityCount(transaction);
        
        System.out.println("First Result: ");
        for (Map.Entry<String, Double> entry : itemProbability.entrySet()) {
            String key = entry.getKey();
            Double value = entry.getValue();
            
            System.out.println(key + " " + value);
        }
        
        System.out.println("");
        ArrayList<String> remainedItem = removeItemByThreshold(itemProbability);
        
        System.out.print("Remaining item: ");
        for (String string : remainedItem) {
            System.out.print(string + " ");
        }
        
        System.out.println("\n");
        itemProbability = secondProbabilityCount(remainedItem, transaction);

        System.out.println("Second Result: ");
        
        String message = "Result:\n";
        for (Map.Entry<String, Double> entry : itemProbability.entrySet()) {
            String key = entry.getKey();
            Double value = entry.getValue();

            message += key + "     " + value + "\n";
            System.out.println(key + " " + value);
        }
        
        JOptionPane.showMessageDialog(null, message);
    }

}
