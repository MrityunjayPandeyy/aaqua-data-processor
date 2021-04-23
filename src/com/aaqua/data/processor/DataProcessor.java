package com.aaqua.data.processor;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.logging.Logger;

import static java.lang.Integer.parseInt;

public class DataProcessor {

    static Logger log = Logger.getLogger("DataProcessor");

    public static void main(String[] args) throws IOException, InterruptedException {

        if (args[0] == null) {
            log.info("Location is not passed as an argument");
        }
        String inputFolder = args[0];

        if (args[1] == null) {
            log.info("fileSize is not passed as an argument");
        }
        int fileSize = parseInt(args[1]);

        if (args[2] == null) {
            log.info("folder is not passed as an argument");
        }
        String folder = args[2];

        log.info("Passed Fields \n location of file :" + inputFolder + "\n filesSize:" + fileSize + "\n Folder details : " + folder);

        String[] folderArray = folder.split(",");
        Map<Integer, DPFile> fileList = new HashMap<>();
        log.info(folderArray.toString());
        for (int i = 0; i < folderArray.length - 1; i++) {
            int count = i;
            DPFile dpFile = new DPFile(folderArray[i],Integer.parseInt(folderArray[count+1]));
            fileList.put(count,dpFile );
            i++;
        }
        log.info(fileList.toString());
        int count = fileList.size();
        for (int i = 0; i < count+1; i++) {
            DPFile dpFile = fileList.get(i);
            Random random = new Random();
            String fileName =dpFile.getName()+random.nextInt()+".csv";
            random_file_writer(dpFile.getSize(),fileName,inputFolder+"\\"+dpFile.getName());
            i++;
        }
    }



    public static void random_file_writer(int filesize,String name,String dir){
        byte[] bytes = new byte[filesize * 1000000];
        BufferedOutputStream bos = null;
        FileOutputStream fos = null;

        try {
            Random rand = new Random();
            java.io.File file = new File(dir, name);
            fileWithDirectoryAssurance(dir,name);
            RandomAccessFile raf = new RandomAccessFile(file, "rw");
            raf.setLength(filesize * 1000000);
            raf.close();

            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);

            rand.nextBytes(bytes);
            bos.write(bytes);

            bos.flush();
            bos.close();
            fos.flush();
            fos.close();

        } catch (FileNotFoundException fnfe) {
            System.out.println("File not found" + fnfe);
        } catch (IOException ioe) {
            System.out.println("Error while writing to file" + ioe);
        } finally {
            try {
                if (bos != null) {
                    bos.flush();
                    bos.close();
                }
                if (fos != null) {
                    fos.flush();
                    fos.close();
                }
            } catch (Exception e) {
                System.out.println("Error while closing streams" + e);
            }
        }
    }

    /** Creates parent directories if necessary. Then returns file */
    private static File fileWithDirectoryAssurance(String directory, String filename) {
        File dir = new File(directory);
        if (!dir.exists()) dir.mkdirs();
        return new File(directory + "/" + filename);
    }


}


