package com.ljh.travels.controller;

import com.alibaba.druid.support.spring.DruidNativeJdbcExtractor;
import com.ljh.travels.entity.Place;
import com.ljh.travels.entity.Result;
import com.ljh.travels.service.PlaceService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("place")
public class PlaceController {
    @Autowired
    private PlaceService placeService;

    @Value("${upload.dir}")
    private String realPath;

    /**
     *
     * @param page 页号
     * @param rows  每一页展示的数量
     * @param provinceId
     * @return
     */
    @GetMapping("findAll")
    public Result findAllById(Integer page,Integer rows,String provinceId)
    {

        System.out.println(page);
        System.out.println(rows);
        System.out.println(provinceId);

        Map<String,Object> map = new HashMap<>();
        Result result = new Result();

        try {
            List<Place> places = placeService.findPageByProvinceId(page,rows,provinceId);
            int totalCounts = placeService.findTotalCountsByProvinceId(provinceId);
            int totalPages = totalCounts%rows==0?totalCounts/rows:totalCounts/rows+1;
            map.put("places",places);
            map.put("totalCounts",totalCounts);
            map.put("totalPages",totalPages);
        } catch (Exception e) {
            e.printStackTrace();
            result.setState(false);
            result.setMsg("查询错误");
            return result;
        }
        result.setState(true);
        result.setObject(map);
        return result;
    }

    @PostMapping("save")
    public Result save(MultipartFile pic,Place place) throws IOException {
        Result result = new Result();
        try {
            //先进行base64编码,再保存文件,不然会报错(其实保不保存无所谓,因为我已经编码存储到数据库中了)
            place.setPicPath(Base64Utils.encodeToString(pic.getBytes()));
            //文件保存
            String newFileName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) +"."+ FilenameUtils.getExtension(pic.getOriginalFilename());
            pic.transferTo(new File(realPath,newFileName));
            //保存place对象
            placeService.save(place);
            result.setState(true);
        } catch (Exception e) {
            e.printStackTrace();
            result.setState(false);
            result.setMsg("保存景点发生错误");
        }
        return result;
    }

    @GetMapping("delete")
    public Result delete(String id){
        Result result = new Result();

        try {
            placeService.delete(id);
            result.setState(true);
        } catch (Exception e) {
            e.printStackTrace();
            result.setState(false);
            result.setMsg("删除景点错误");
        }
        return result;
    }

    @GetMapping("findOne")
    public Result findOne(String id){
        Result result = new Result();

        try {
            result.setObject(placeService.findOne(id));
            result.setState(true);
        } catch (Exception e) {
            e.printStackTrace();
            result.setState(false);
            result.setMsg("查询景点信息失败");
        }
        return result;
    }

    @PostMapping("update")
    public Result update(MultipartFile pic,Place place){
        Result result = new Result();
        try {
            //如果修改了图片
            if(pic != null)
            {
                //先进行base64编码,再保存文件,不然会报错(其实保不保存无所谓,因为我已经编码存储到数据库中了)
                place.setPicPath(Base64Utils.encodeToString(pic.getBytes()));
                //文件保存
                String newFileName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) +"."+ FilenameUtils.getExtension(pic.getOriginalFilename());
                pic.transferTo(new File(realPath,newFileName));
            }
            else{
                //如果没修改图片
                place.setPicPath(placeService.findOne(place.getId()).getPicPath()); //设为原来的picPath
            }
            placeService.update(place);
            result.setState(true);
        } catch (Exception e) {
            e.printStackTrace();
            result.setState(false);
            result.setMsg("修改景点错误");
        }
        return result;
    }

}
