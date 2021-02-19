package com.example.convertjsontohtmltobitmaptobase64.common;

import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Base64;

@Service
public class Base64Converter {

    public String encodeImageToBase64(BufferedImage bufferedImage) throws IOException {
        byte[] bytes = toByteArray(bufferedImage, "bmp");
        String imageString = Base64.getEncoder().encodeToString(bytes);
        // Write base64 in file if you need
//        FileWriter fileWriter = new FileWriter(savePath);
//        fileWriter.write(imageString);
//        fileWriter.close();
        return imageString;
    }

    public String encodeImageToBase64AndSaveFile(String imgPath, String savePath) throws IOException {

        FileInputStream imageStream = new FileInputStream(imgPath);
        byte[] bytes = imageStream.readAllBytes();
        String imageString = Base64.getEncoder().encodeToString(bytes);

        // Write base64 in file if you need
        FileWriter fileWriter = new FileWriter(savePath);
        fileWriter.write(imageString);
        fileWriter.close();

        imageStream.close();

        return imageString;

    }

    public void decodeBase64ToImageAndSaveFile(String txtPath, String savePath) throws IOException {

        FileInputStream inputStream = new FileInputStream(txtPath);
        byte[] bytesTxt = inputStream.readAllBytes();
        byte[] bytes64 = Base64.getDecoder().decode(bytesTxt);

        // Export file
        FileOutputStream fileOutputStream = new FileOutputStream(savePath);
        fileOutputStream.write(bytes64);
        fileOutputStream.close();

        inputStream.close();

    }

    // convert BufferedImage to byte[]
    private byte[] toByteArray(BufferedImage bufferedImage, String format) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, format, baos);
        byte[] bytes = baos.toByteArray();
        return bytes;
    }

    // convert byte[] to BufferedImage
    private BufferedImage toBufferedImage(byte[] bytes) throws IOException {
        InputStream inputStream = new ByteArrayInputStream(bytes);
        BufferedImage bufferedImage = ImageIO.read(inputStream);
        return bufferedImage;
    }

}