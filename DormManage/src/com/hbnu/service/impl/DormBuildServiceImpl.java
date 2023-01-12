package com.hbnu.service.impl;

import com.hbnu.dao.DormBuildDao;
import com.hbnu.dao.impl.DormBuildDaoImpl;
import com.hbnu.pojo.DormBuild;
import com.hbnu.service.DormBuildService;

import java.util.List;

public class DormBuildServiceImpl implements DormBuildService {
    DormBuildDao dormBuildDao=new DormBuildDaoImpl();
    @Override
    public List<DormBuild> getAllDormBuild() {
        return dormBuildDao.findAllDormBuild();
    }

    @Override
    public List<DormBuild> getDormBuildByUserId(Integer id) {
        return dormBuildDao.findDormBuildsByUserId(id);
    }

    @Override
    public void deleteDormBuildsByUserId(Integer id) {
        dormBuildDao.deleteDormBuildsByUserId(id);
    }

    @Override
    public void addDormBuildAndByUserId(Integer id, String[] dormBuildIds) {
        dormBuildDao.saveManagerAndBuilds(id,dormBuildIds);
    }
}
