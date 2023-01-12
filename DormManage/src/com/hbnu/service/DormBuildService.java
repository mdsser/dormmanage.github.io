package com.hbnu.service;

import com.hbnu.pojo.DormBuild;

import java.util.List;

public interface DormBuildService {
    List<DormBuild> getAllDormBuild();

    List<DormBuild> getDormBuildByUserId(Integer id);

    void deleteDormBuildsByUserId(Integer id);

    void addDormBuildAndByUserId(Integer id, String[] dormBuildIds);
}
