package com.ljh.travels.controller;

import com.ljh.travels.entity.Province;
import com.ljh.travels.entity.Result;
import com.ljh.travels.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("province")
public class ProvinceController {

    @Autowired
    private ProvinceService provinceService;

    /**
     * 这个函数把分页查询结果，总记录数，总页数全部给做了
     * @param page
     * @param rows
     * @return
     */
    @GetMapping("findByPage")
    public Map<String,Object> findByPage(Integer page,Integer rows)
    {
        Map<String,Object> map = new HashMap<>();
        List<Province> provinces = provinceService.findByPage(page,rows);
        int totals = provinceService.findTotals();
        int totalPages = totals%rows==0?totals/rows:totals/rows+1;
        map.put("provinces",provinces);
        map.put("totals",totals);
        map.put("totalPages",totalPages);
        return map;
    }

    /**
     * 添加省份信息
     * @param province
     * @return
     */
    @PostMapping("save")
    public Result save(@RequestBody  Province province)
    {
        System.out.println(province);
        Result result =new Result();
        try {
            provinceService.save(province);
            result.setMsg("保存省份成功");
            result.setState(true);
        } catch (Exception e) {
            e.printStackTrace();
            result.setMsg("保存省份失败");
            result.setState(false);
        }
        return result;
    }

    /**
     * 删除省份
     * @param id
     * @return
     */
    @GetMapping("delete")
    public Result delete(String id)
    {
        Result result =new Result();
        try {
            provinceService.delete(id);
            result.setState(true);
        } catch (Exception e) {
            e.printStackTrace();
            result.setState(false);
            result.setMsg("删除省份失败");
        }
        return result;
    }

    /**
     * 根据id查询省份
     * @param id
     * @return
     */
    @GetMapping("findOne")
    public Result findOne(String id)
    {
        Result result= new Result();
        try {
            Province province=  provinceService.findOne(id);
            result.setState(true);
            result.setObject(province);
        } catch (Exception e) {
            e.printStackTrace();
            result.setState(false);
            result.setMsg("查询id为"+id+"的省份失败");
        }
        return result;
    }

    @PostMapping("update")
    public Result update(@RequestBody Province province)
    {
        Result result = new Result();
        try {
            provinceService.update(province);
            result.setState(true);
        } catch (Exception e) {
            e.printStackTrace();
            result.setState(false);
            result.setMsg("修改省份信息失败");
        }
        return result;
    }




}
