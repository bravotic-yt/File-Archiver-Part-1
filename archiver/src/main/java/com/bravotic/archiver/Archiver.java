/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.bravotic.archiver;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author collin
 */
public class Archiver {

    // archiver create file.ar files...
    // archiver list file.ar
    // archiver unarchive file.ar
    public static void main(String[] args) {
        if (args.length >= 2) {
            if (args[0].equals("create")) {
                ArFile ar = new ArFile();
                
                for (int x = 2; x < args.length; x++) {
                    try {
                        ar.addFile(args[x]);
                    } catch (IOException ex) {
                        System.out.println("Cannot add file " + args[x]);
                    }
                }
                
                try {
                    ar.toFile(args[1]);
                } catch (IOException ex) {
                    System.out.println("Cannot write to file " + args[1]);
                }
            }
            else if (args[0].equals("list")) {
                try {
                    ArFile ar = new ArFile(args[1]);
                    ar.printFiles();
                } catch (Exception ex) {
                    System.out.println("Could not open file " + args[1]);
                }
            }
            else if(args[0].equals("unarchive")) {
                try {
                    ArFile ar = new ArFile(args[1]);
                    ar.unarchive();
                } catch (Exception ex) {
                    System.out.println("Could not open file " + args[1]);
                }
                
            }
        }
        else {
            System.out.println("Usage:");
            System.out.println("archiver create file.ar files...\n" +
                "archiver list file.ar\n" +
                "archiver unarchive file.ar");
        }
    }
}
