package org.hhh.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.hhh.model.system.SysMenu;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface SysMenuMapper extends BaseMapper<SysMenu> {
    //根据用户id查询菜单权限
    List<SysMenu> getUserMenuList(@Param("userId") Long id);
}
