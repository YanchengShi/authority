package org.hhh.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.hhh.model.system.SysUser;
import org.hhh.model.vo.SysUserQueryVo;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author yan
 * @since 2023-03-13
 */
@Repository
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {
    //条件分页查询
    IPage<SysUser> selectPage(Page<SysUser> pageParam, @Param("vo") SysUserQueryVo vo);

}
