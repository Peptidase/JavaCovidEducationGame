package AsteroidGame;

import jdk.jfr.Percentage;

import javax.swing.*;
import java.io.*;
import java.util.*;

public class Score {


    //Member attributes to check and read the score file;
    String filename;
    FileWriter Writer;
    BufferedWriter BWriter;
    File Scorefile;
    Scanner Reader;


    public Score(){
        this.filename = "scores.txt";
        LoadFile();

    }

    private void LoadFile(){

            Scorefile = new File(filename);


    }

    private HashMap<String,Integer> SortScores(HashMap<String,Integer> Scores){
        List<Map.Entry<String,Integer>> list = new LinkedList<Map.Entry<String, Integer>>(Scores.entrySet());

        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return (o1.getValue().compareTo(o2.getValue()));
            }
        });

        HashMap<String,Integer> temp = new LinkedHashMap<String, Integer>();
        for(Map.Entry<String,Integer> aa : list){
            temp.put(aa.getKey(), aa.getValue());
        }

        return temp;
    }

    public void Write(String line){
        try {
            Writer = new FileWriter(Scorefile,true);
            BWriter = new BufferedWriter(Writer);
            BWriter.write("\n"+line);
            BWriter.close();
            Writer.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        }

    public HashMap<String,Integer> getScores(){
        HashMap<String,Integer> Scores = new HashMap<String,Integer>();

        try{
            Reader = new Scanner(Scorefile);
            if(Reader.hasNextLine()) {


                while (Reader.hasNextLine()) {
                    String[] data = Reader.nextLine().split(" ");

                    Scores.put(data[0], Integer.parseInt(data[1]));
                }
            }
            Reader.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }
        HashMap<String,Integer> temp = new HashMap<>();
        Scores = SortScores(Scores);

        return Scores;

    }
    }


