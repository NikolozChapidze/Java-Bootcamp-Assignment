package dev.omedia.image;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.Scanner;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

public class Imageparser {
    public static void parseImage(){
        int n = 1;
        Scanner scanner = new Scanner(System.in);
        System.out.print("File path: ");
        String filePath = scanner.nextLine();
        String regex = "^(?:[a-zA-Z]:)?[\\/\\\\]{0,2}(?:[.\\/\\\\ ](?![.\\/\\\\\\n])|[^<>:\"|?*.\\/\\\\ \\n])+" +
                "(?:\\.jpg|png|gif|bmp)$";
        while (!Pattern.matches(regex, filePath)){
            System.out.print("incorrect input! try again : ");
            filePath = scanner.nextLine();
        }

        System.out.println("Please select which type of output you want : IMAGE or TXT");
        String outputType = scanner.nextLine().toUpperCase();
        while (!outputType.equals("IMAGE") && !outputType.equals("TXT")){
            System.out.print("incorrect input! try again : ");
            outputType = scanner.nextLine().toUpperCase();
        }

        System.out.print("Please input number of threads: ");
        n = scanner.nextInt();

        switch (outputType){
            case "IMAGE" :
                imageToImage(filePath,n);
                break;
            case "TXT" :
                imageToTxt(filePath,n);
                break;
            default :
                throw new IllegalStateException("Invalid type: " + outputType);
        }

    }

    private static void imageToImage(String filePath, int n){
        try {
            ExecutorService service = Executors.newFixedThreadPool(n);
            File file = new File(filePath);
            BufferedImage image = ImageIO.read(file);
            BufferedImage newImage = new BufferedImage(image.getWidth(),image.getHeight(), BufferedImage.TYPE_INT_RGB);
            for (int y = 0; y < image.getHeight() ; y++) {
                for (int x = 0; x < image.getWidth(); x++) {
                    service.submit(callableToImage(image,newImage,x,y));
                }
            }
            service.shutdown();
            if (!service.awaitTermination(60, TimeUnit.SECONDS)) {
                service.shutdownNow();
            }
            File newFile = new File(file.getParentFile().getAbsolutePath()+ "\\me_"+ UUID.randomUUID()+".jpeg");
            ImageIO.write(newImage, "jpeg", newFile);
        }catch (Exception e){
            System.out.println("Error: " + e);
        }
        System.out.println("Task complete.");
    }

    public static Callable<String> callableToImage(BufferedImage image, BufferedImage newImage, int x, int y) {
        return () -> {
            Color color = new Color(image.getRGB(x,y));
            if ((color.getRed()+color.getBlue()+color.getGreen())/3 >= 128){
                newImage.setRGB(x,y,new Color(255,255,255).getRGB());
            }else{
                newImage.setRGB(x,y,new Color(0,0,0).getRGB());
            }
            return "completed. x: " +x +", y: "+y+" pixel";
        };
    }

    private static void imageToTxt(String filePath, int n){
        StringBuilder result = new StringBuilder("");
        try {
            ExecutorService service = Executors.newFixedThreadPool(n);
            File file = new File(filePath);
            BufferedImage image = ImageIO.read(file);
            char[][] resOfChars = new char[image.getHeight()][image.getWidth()];

            for (int y = 0; y < image.getHeight() ; y++) {
                for (int x = 0; x < image.getWidth(); x++) {
                    service.submit(callableToTxt(image,resOfChars,x,y));
                }
            }
            service.shutdown();
            if (!service.awaitTermination(60, TimeUnit.SECONDS)) {
                service.shutdownNow();
            }
            for (var subArray : resOfChars) {
                result.append(subArray);
                result.append("\n");
            }
            Files.write(Paths.get(file.getParentFile().getAbsolutePath() +"\\me_"+ UUID.randomUUID()+".txt"),
                    result.toString().getBytes(), StandardOpenOption.CREATE);
        }catch (Exception e){
            System.out.println("Error: " + e);
        }
        System.out.println("Task complete.");
    }

    public static Callable<String> callableToTxt(BufferedImage image,char[][] resOfChars, int x, int y) {
        return () -> {
            Color color = new Color(image.getRGB(x, y));
            if ((color.getRed()+color.getBlue()+color.getGreen())/3 >= 128){
                resOfChars[y][x] = '.';
            }else{
                resOfChars[y][x] = ';';
            }
            return "completed. x: " +x +", y: "+y+" pixel";
        };
    }

}
