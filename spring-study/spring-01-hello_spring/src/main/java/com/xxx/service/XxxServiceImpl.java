package com.xxx.service;

import com.xxx.dao.XxxDao;
import com.xxx.dao.XxxDaoAaImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Chenix
 * @create 2024-01-30 22:38
 */
public class XxxServiceImpl implements XxxService{
    private XxxDao xxxDao;

    public void setXxxDao(XxxDao xxxDao) {
        this.xxxDao = xxxDao;
    }

    @Override
    public String getStr() {
        return xxxDao.getStr();
    }
}
