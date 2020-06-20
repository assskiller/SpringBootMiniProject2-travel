package com.ljh.travels.controller;

import com.ljh.travels.entity.Result;
import com.ljh.travels.entity.User;
import com.ljh.travels.service.UserService;
import com.ljh.travels.utils.GenerateCheckCode;
import org.apache.el.stream.StreamELResolverImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.GenericFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.http.HttpRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Controller
@RequestMapping("user")
@CrossOrigin //允许跨域 因为是前后端分离
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 用户注册
     * @param key
     * @param code
     * @param request
     * @param user
     * @return
     */
    @ResponseBody
    @PostMapping("register")
    public Result register(String key,String code, HttpServletRequest request,@RequestBody User user) //因为axios是把对象转换为json的，所以要用requestBody才能接收到
    {
        Result result = new Result();
        String saveCode = (String) request.getServletContext().getAttribute(key);
        try {

            if(saveCode.equalsIgnoreCase(code))
            {
                //注册
                userService.register(user);
                result.setMsg("注册成功");
                result.setState(true);
            }
            else
            {
                throw new RuntimeException("验证码错误");
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
            result.setMsg(e.getMessage());
            result.setState(false);
        }

        return result;
    }


    /**
     * 前后端交互验证码
     * @param request
     * @return
     * @throws IOException
     */
    @ResponseBody
    @GetMapping("getImage")
    public Map<String, String> getImage(HttpServletRequest request) throws IOException {

        //因为是前后端分离，所以不能使用session
        //解决方案是，将验证码存入servletContext,键为唯一的时间戳，值为验证码
        //然后把时间戳返回出去，因为要同时返回时间戳和图片，所以最后返回一个map
        Map<String,String> result = new HashMap<>();

        //获取验证码图片和验证码
        GenerateCheckCode generateCheckCode  = new GenerateCheckCode();
        String code = generateCheckCode.getCode();
        BufferedImage image = generateCheckCode.getImage();

        //创建一个唯一的key,并将（key,验证码)存入servletContext
        String key = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        request.getServletContext().setAttribute(key,code);

        //进行Base64编码
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ImageIO.write(image,"png",bos);
        String encodedImage = Base64Utils.encodeToString(bos.toByteArray());

        //将编码后的图片和key返回到前端
        result.put("key",key);
        result.put("image",encodedImage);
        return result;
    }

    @ResponseBody
    @PostMapping("login")
    public Result login(@RequestBody User user)
    {
        Result result = new Result();
        try {
            User user1 = userService.login(user);
            result.setState(true);
            result.setMsg("登陆成功");
        } catch (Exception e) {
            result.setState(false);
            result.setMsg(e.getMessage());
        }
        return result;
    }
}
