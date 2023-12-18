package com.admin.store.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.admin.store.pojo.Menu;
import com.admin.store.service.MenuService;
import com.admin.store.mapper.MenuMapper;
import org.springframework.stereotype.Service;

/**
 * @author love
 * @description 针对表【menu】的数据库操作Service实现
 * @createDate 2023-10-27 14:51:05
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu>
        implements MenuService {
}




