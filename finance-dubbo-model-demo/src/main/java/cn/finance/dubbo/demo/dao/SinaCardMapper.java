package cn.finance.dubbo.demo.dao;

import java.util.List;

import cn.finance.dubbo.demo.model.SinaCard;
import cn.finance.dubbo.demo.model.SinaCardExample;

public interface SinaCardMapper {
    int countByExample(SinaCardExample example);

    int deleteByPrimaryKey(Integer scId);

    int insert(SinaCard record);

    int insertSelective(SinaCard record);

    List<SinaCard> selectByExample(SinaCardExample example);

    SinaCard selectByPrimaryKey(Integer scId);

    int updateByPrimaryKeySelective(SinaCard record);

    int updateByPrimaryKey(SinaCard record);
}