package com.wu.dubbo;


/**
 * 城市业务 Dubbo 服务层
 *
 * Created by bysocket on 28/02/2017.
 */
public interface DubboCityService {

    /**
     * 根据城市名称，查询城市信息
     * @param cityName
     */
    String findCityByName(String cityName);
}
