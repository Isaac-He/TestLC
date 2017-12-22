package com.isaac;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Isaac on 4/28/2017.
 */
public class ListFiles {

    private String path;

    private String csvPath;

    private static final String HEADER = "Name,Birth,Alias";

    public ListFiles(String path, String csvPath) {
        this.path = path;
        this.csvPath = csvPath;
    }

    public void writeFilesInfo() {
        File[] allFile = new File(path).listFiles();
        List<List<String>> infos = new ArrayList<>();
        for (File file : allFile) {
            if(file.isDirectory()) {
                if (file.getName().matches("\\d{4}[_]\\S+")) {
                    System.out.println(file.getPath());
                    List<String> info = new ArrayList<>();
                    info.add(file.getName().substring(5));
                    info.add(file.getName().substring(0, 4));
                    info.add("");
                    infos.add(info);
                }
            }
        }
        try {
            File csvFile = new File(csvPath);
            boolean needHeader = false;
            if (!csvFile.exists()) {
                csvFile.createNewFile();
                needHeader = true;
            }
            FileWriter fw = new FileWriter(csvFile, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            if (needHeader) {
                pw.println(HEADER);
            }

            for (int i = 0; i < infos.size(); i++) {
                pw.println(String.join(",", infos.get(i)));
            }
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void moveFiles() {
        File[] allFile = new File(path).listFiles();
        for (File file : allFile) {
            if(file.isDirectory()) {
                if (file.getName().matches("\\d{4}[_]\\S+")) {
                    System.out.println(file.getPath());
                    doMove(file);
                }
            }
        }
    }

    private void doMove(File dir){
        File[] subDirs = dir.listFiles();
        for (File subDir : subDirs) {
            if (subDir.isDirectory()) {
                File[] files = subDir.listFiles();
                for (File file : files) {
                    if (file.isFile()) {
                        String newLocaltion = dir.getPath() + "\\" + file.getName();
                        System.out.println(newLocaltion);
                        file.renameTo(new File(newLocaltion));
                    }
                }
                if (subDir.list().length == 0) {
                    subDir.delete();
                }
            }
        }
    }
}
