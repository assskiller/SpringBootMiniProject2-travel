package com.ljh.travels.controller;

import com.ljh.travels.entity.Place;
import com.ljh.travels.entity.Result;
import com.ljh.travels.service.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("place")
public class PlaceController {
    @Autowired
    private PlaceService placeService;

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
}
