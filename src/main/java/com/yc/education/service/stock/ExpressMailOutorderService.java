package com.yc.education.service.stock;

import com.yc.education.model.stock.ExpressMailOutorder;
import com.yc.education.service.IService;

import java.util.List;

/**
 * @ClassName ExpressMailOutorderService
 * @Description TODO
 * @Author QuZhangJing
 * @Date 2019/4/28 22:50
 * @Version 1.0
 */
public interface ExpressMailOutorderService extends IService<ExpressMailOutorder> {



    List<ExpressMailOutorder> findExpressMailOutorders(long mailid);


}
