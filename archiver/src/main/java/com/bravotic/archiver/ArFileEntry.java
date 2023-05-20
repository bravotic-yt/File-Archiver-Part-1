/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bravotic.archiver;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author collin
 */
public class ArFileEntry {
    private String name;
    private int mod;
    private int oid;
    private int gid;
    private int mode;
    private int size;
    
    private char[] data;
    
    public ArFileEntry (String name, int mod, int oid, int gid, int mode,
            int size, char[] data) {
        this.name = name;
        this.mod = mod;
        this.oid = oid;
        this.gid = gid;
        this.mode = mode;
        this.size = size;
        this.data = data;
    }
    
    public void setName (String name) {
        this.name = name;
    }
    
    public void setModificationTimestamp (int mod) {
        this.mod = mod;
    }
        
    public void setOwnerID (int oid) {
        this.oid = oid;
    }
            
    public void setGroupID (int gid) {
        this.gid = gid;
    }
                
    public void setMode (int mode) {
        this.mode = mode;
    }
                    
    public void setSize (int size) {
        this.size = size;
    }
                        
    public void setData (char[] data) {
        this.data = data;
    }
    
    public String getName () {
        return this.name;
    }
    
    public int getModificationTimestamp () {
        return this.mod;
    }
        
    public int getOwnerID () {
        return this.oid;
    }
            
    public int getGroupID () {
        return this.gid;
    }
                
    public int getMode () {
        return this.mode;
    }
                    
    public int getSize () {
        return this.size;
    }
                        
    public char[] getData () {
        return this.data;
    }
    
    public String toEntry () {
        return String.format("%-16s%-12d%-6d%-6d%-8d%-10d`\n%s", this.name,
                this.mod, this.oid, this.gid, this.mode, this.size, 
                String.valueOf(this.data));
    }
    
    public void toFile () throws IOException {
        File output = new File(this.name);
        FileWriter fw = new FileWriter(output);
        
        fw.write(this.data);
        
        fw.close();
    }
}
