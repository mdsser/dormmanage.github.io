package com.hbnu.dao;

import com.hbnu.pojo.DormBuild;

import java.util.List;

public interface DormBuildDao {
    List<DormBuild> findDormBuildsByUserId(Integer id);

    List<DormBuild> findAllDormBuild();

    void saveManagerAndBuilds(Integer userId, String[] dormBuildIds);

    void deleteDormBuildsByUserId(Integer id);
}
