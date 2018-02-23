/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketbasketanalysis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Asus
 */
public class MarketBasketAnalysis {

    /**
     * @param args the command line arguments
     */
    
    public static ArrayList inisialisasi(){
        ArrayList<String> items = new ArrayList<>();
        ArrayList<ArrayList<String>> transaction = new ArrayList<>();
        
        items.add("Onion");
        items.add("Potato");
        items.add("Burger");
        transaction.add(items);
        items = new ArrayList<>();
        
        items.add("Potato");
        items.add("Burger");
        items.add("Milk");
        transaction.add(items);
        items = new ArrayList<>();
        
        items.add("Milk");
        items.add("Beer");
        transaction.add(items);
        items = new ArrayList<>();
        
        items.add("Onion");
        items.add("Potato");
        items.add("Milk");
        transaction.add(items);
        items = new ArrayList<>();
        
        items.add("Onion");
        items.add("Potato");
        items.add("Burger");
        items.add("Milk");
        transaction.add(items);
        items = new ArrayList<>();
        
        items.add("Onion");
        items.add("Potato");
        items.add("Burger");
        items.add("Milk");
        items.add("Beer");
        items.add("Diaper");
        transaction.add(items);
        items = new ArrayList<>();
        
        items.add("Milk");
        items.add("Diaper");
        items.add("Beer");
        transaction.add(items);
        items = new ArrayList<>();
        
        items.add("Potato");
        items.add("Milk");
        transaction.add(items);
        items = new ArrayList<>();
        
        items.add("Bread");
        items.add("Milk");
        transaction.add(items);
        items = new ArrayList<>();
        
        items.add("Bread");
        items.add("Diaper");
        items.add("Beer");
        items.add("Potato");
        transaction.add(items);
        items = new ArrayList<>();
        
        items.add("Milk");
        items.add("Diaper");
        items.add("Beer");
        items.add("Burger");
        transaction.add(items);
        items = new ArrayList<>();
        
        items.add("Bread");
        items.add("Milk");
        items.add("Diaper");
        items.add("Beer");
        transaction.add(items);
        items = new ArrayList<>();
        
        items.add("Bread");
        items.add("Milk");
        items.add("Diaper");
        items.add("Onion");
        transaction.add(items);
        items = new ArrayList<>();
        
        return transaction;
    }
    
    public static HashMap countProbability(ArrayList<ArrayList> transaction){
        HashMap<String, Integer> itemOccurence = new HashMap<>();
        for (ArrayList<String> arrayList : transaction) {
            for (String item : arrayList) {
                if(!itemOccurence.containsKey(item)){
                    itemOccurence.put(item, 1);
                }
                else{
                    itemOccurence.put(item, (itemOccurence.get(item) + 1));
                }
            }
        }
        
        HashMap<String, Double> itemProbability = new HashMap<>();
        
        for (Map.Entry<String, Integer> entry : itemOccurence.entrySet()) {
            String key = entry.getKey();
            int value = entry.getValue();
            double probability = (double) value / transaction.size();
            itemProbability.put(key, probability);
        }
        
        return itemProbability;
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        ArrayList<ArrayList> transaction = inisialisasi();
        HashMap<String, Double> itemProbability = countProbability(transaction);
        
        for (Map.Entry<String, Double> entry : itemProbability.entrySet()) {
            String key = entry.getKey();
            Double value = entry.getValue();
            System.out.println(key + " " + value);
        }
        
        
    }
    
}
