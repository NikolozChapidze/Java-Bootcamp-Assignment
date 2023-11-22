package dev.omedia.file;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

public class FileCopier {
    private static final String regex = "^(?:[a-zA-Z]:)?[\\/\\\\]{0,2}(?:[.\\/\\\\ ](?![.\\/\\\\\\n])|[^<>:\"|?*.\\/\\\\ \\n])+$";
    private static final File source;
    private static final String destination;
    private static final int numOfThreads;

    private static ExecutorService service;

    static {
        Scanner scanner = new Scanner(System.in);
        String destination1;
        String source1;
        System.out.print("input source: ");
        source1 = scanner.nextLine();
        while (!Pattern.matches(regex, source1)){
            System.out.print("incorrect input! try again : ");
            source1 = scanner.nextLine();
        }
        source = new File(source1);

        System.out.print("input destination: ");
        destination1 = scanner.nextLine();
        while (!Pattern.matches(regex, destination1)){
            System.out.print("incorrect input! try again : ");
            destination1 = scanner.nextLine();
        }
        destination = destination1 + "\\";

        System.out.print("input number of threads: ");
        numOfThreads= scanner.nextInt();
    }

    public static void copyDir(){
        service = Executors.newFixedThreadPool(numOfThreads);
        copyDir(source,destination);
        service.shutdown();
        try {
            if (!service.awaitTermination(60, TimeUnit.SECONDS)) {
                service.shutdownNow();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void copyDir(File sourceFile, String destinationPath){
        for (var file : sourceFile.listFiles()) {
            if(file.isDirectory()) {
                copyDir(file, destinationPath + file.getName() + "_");
            }else {
                service.submit(callable(file, destinationPath));
            }
        }
    }

    public static Callable<String> callable(File file, String destinationPath) {
        return () ->{
                System.out.println(file.getName() + " currentthread " + Thread.currentThread().getName());
                try {
                    Files.copy(file.toPath(), (new File(destinationPath + file.getName())).toPath(),
                            StandardCopyOption.REPLACE_EXISTING);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            return "Complated";
        };
    }
}

