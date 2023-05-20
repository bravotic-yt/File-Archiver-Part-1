/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bravotic.archiver;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author collin
 */
public class ArFile {
    ArrayList<ArFileEntry> entries;
    
    public ArFile () {
        entries = new ArrayList<>();
    }
    
    public ArFile (String file) throws FileNotFoundException, IOException, Exception {
        entries = new ArrayList<>();
        
        File ar = new File(file);
        BufferedReader br = new BufferedReader(new FileReader(ar));
        
        String magic = br.readLine();
        
        if (magic.equals("!<arch>")) {
            String info;
            
            while ((info = br.readLine()) != null) {
                String name = info.substring(0, 0+16).trim();
                int mod = Integer.parseInt(info.substring(16, 16+12).trim());
                int oid = Integer.parseInt(info.substring(28, 28+6).trim());
                int gid = Integer.parseInt(info.substring(34, 34+6).trim());
                int mode = Integer.parseInt(info.substring(40, 40+8).trim());
                int size = Integer.parseInt(info.substring(48, 48+10).trim());
                String end = info.substring(58);
                
                if (end.equals("`")) {
                    char[] data = new char[size];
                    
                    br.read(data);
                    
                    ArFileEntry fe = new ArFileEntry(name, mod, oid, gid, mode, 
                            size, data);
                    
                    entries.add(fe);
                   
                }
                else {
                    throw new Exception("Expected end of header");
                }
            }
            
            br.close();
        }
        else {
            throw new Exception("File is not an ar file");
        }
    }
    
    public void addFile (String fileName) throws FileNotFoundException, IOException {
        File add = new File(fileName);
        BufferedReader br = new BufferedReader(new FileReader(add));
        
        String name = add.getName();
        int size = (int)add.length();
        
        char[] data = new char[size];
        
        br.read(data);
        
        br.close();
        
        ArFileEntry fe = new ArFileEntry(name, 0, 0, 0, 100644, size, data);
        
        entries.add(fe);
    }
    
    public void toFile (String fname) throws IOException {
        File out = new File(fname);
        
        FileWriter fw = new FileWriter(out);
        
        fw.write("!<arch>\n");
        
        for (ArFileEntry e : entries) {
            fw.write(e.toEntry());
        }
        
        fw.close();
    }
    
    public void printFiles () {
        for (ArFileEntry e : entries) {
            System.out.println(e.getName());
        }
    }
    
    public void unarchive () throws IOException {
        for (ArFileEntry e : entries) {
            e.toFile();
        }
    }
}
