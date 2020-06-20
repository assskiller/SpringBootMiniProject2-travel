package com.ljh.travels.utils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class GenerateCheckCode {
    BufferedImage image;
    String code;

    public GenerateCheckCode() {
        image = new BufferedImage(100,50,BufferedImage.TYPE_INT_RGB);
        //画验证码
        Graphics g = image.getGraphics();
        g.setColor(Color.GRAY);
        g.fillRect(0,0,100,50);
        g.setColor(Color.BLUE);
        g.drawRect(0,0,100-1,50-1);

        //add word
        String charset = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        String s1,s2,s3,s4;
        s1 = charset.charAt(random.nextInt(charset.length()))+"";
        s2 = charset.charAt(random.nextInt(charset.length()))+"";
        s3 = charset.charAt(random.nextInt(charset.length()))+"";
        s4 = charset.charAt(random.nextInt(charset.length()))+"";

        code = s1+s2+s3+s4;
        g.setFont(new Font("Times New Roman",Font.PLAIN,22));
        g.drawString(s1,20,25);
        g.drawString(s2,40,25);
        g.drawString(s3,60,25);
        g.drawString(s4,80,25);

        //draw some lines
        g.setColor(Color.RED);
        for(int i=0;i<5;i++)
        {
            g.drawLine(random.nextInt(100),random.nextInt(50),random.nextInt(100),random.nextInt(50));
        }
    }

    public String getCode()
    {
        return code;
    }
    public BufferedImage getImage()
    {
        return image;
    }
}
