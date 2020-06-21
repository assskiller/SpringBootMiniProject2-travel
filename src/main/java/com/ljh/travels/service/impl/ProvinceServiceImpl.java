package com.ljh.travels.service.impl;

import com.ljh.travels.dao.ProvinceDao;
import com.ljh.travels.entity.Province;
import com.ljh.travels.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProvinceServiceImpl implements ProvinceService {

    @Autowired
    private ProvinceDao provinceDao;

    /**
     *分页查询
     * @param page 是当前页
     * @param rows 是每一页的行数
     * @return
     */
    @Override
    public List<Province> findByPage(Integer page, Integer rows) {
        int start = (page-1)*rows;
        return provinceDao.findByPage(start,rows);
    }

    /**
     * 查询总条数
     * @return
     */
    @Override
    public Integer findTotals() {
        return provinceDao.findTotals();
    }

    /**
     * 增加省份
     * @param province
     */
    @Override
    public void save(Province province) {
        province.setPlaceCounts(0); //刚添加省份时，景点个数为0
        provinceDao.save(province);
    }


    /**
     * 删除省份
     * @param id
     */
    @Override
    public void delete(String id) {
        provinceDao.delete(id);
    }

    @Override
    public Province findOne(String id) {
        return provinceDao.findOneByID(id);
    }

    @Override
    public void update(Province province) {
        provinceDao.update(province);
    }


}
