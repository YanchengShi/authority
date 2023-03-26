package org.hhh.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.hhh.model.system.SysUser;
import org.hhh.model.system.SysUser;
import org.hhh.model.vo.SysUserQueryVo;

import java.util.Map;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author yan
 * @since 2023-03-13
 */
public interface SysUserService extends IService<SysUser> {
    IPage<SysUser> selectPage(Page<SysUser> pageParam, SysUserQueryVo vo);
    void updateStatusById(Long id,Integer status);

    SysUser  getUserInfoByUserName(String username);

    Map<String, Object> getInfoByUserName(String userName);
}
