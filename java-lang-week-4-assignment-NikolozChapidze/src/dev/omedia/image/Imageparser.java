package dev.omedia.image;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Locale;
import java.util.Scanner;
import java.util.UUID;
import java.util.regex.Pattern;

public class Imageparser {
    public static void parseImage(){
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

        switch (outputType){
            case "IMAGE" -> imageToImage(filePath);
            case "TXT" -> imageToTxt(filePath);
            default -> throw new IllegalStateException("Invalid type: " + outputType);
        }

    }

    private static void imageToImage(String filePath){
        try {
            File file = new File(filePath);
            BufferedImage image = ImageIO.read(file);
            BufferedImage newImage = new BufferedImage(image.getWidth(),image.getHeight(), BufferedImage.TYPE_INT_RGB);
            for (int y = 0; y < image.getHeight() ; y++) {
                for (int x = 0; x < image.getWidth(); x++) {
                    Color color = new Color(image.getRGB(x,y));
                    if ((color.getRed()+color.getBlue()+color.getGreen())/3 >= 128){
                        newImage.setRGB(x,y,new Color(255,255,255).getRGB());
                    }else{
                        newImage.setRGB(x,y,new Color(0,0,0).getRGB());
                    }
                }
            }

            File newFile = new File(file.getParentFile().getAbsolutePath()+ "\\me_"+ UUID.randomUUID()+".jpeg");
            ImageIO.write(newImage, "jpeg", newFile);
        }catch (Exception e){
            System.out.println("Error: " + e);
        }
        System.out.println("Task complete.");
    }

    private static void imageToTxt(String filePath){
        StringBuffer result = new StringBuffer("");
        try {
            File file = new File(filePath);
            BufferedImage image = ImageIO.read(file);
            for (int y = 0; y < image.getHeight() ; y++) {
                for (int x = 0; x < image.getWidth(); x++) {
                    Color color = new Color(image.getRGB(x,y));
                    if ((color.getRed()+color.getBlue()+color.getGreen())/3 >= 128){
                        result.append(".");
                    }else{
                        result.append(";");
                    }
                }
                result.append("\n");
            }
            Files.write(Paths.get(file.getParentFile().getAbsolutePath() +"\\me_"+ UUID.randomUUID()+".txt"),
                    result.toString().getBytes(), StandardOpenOption.CREATE);
        }catch (Exception e){
            System.out.println("Error: " + e);
        }
        System.out.println("Task complete.");
    }
}
