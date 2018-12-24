package cn.hz.service.impl;

import cn.hz.dao.IOrdersDao;
import cn.hz.domain.Orders;
import cn.hz.service.IOrdersService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OrdersServiceImpl implements IOrdersService {

    @Autowired
    private IOrdersDao ordersDao;

    @Override
    public List<Orders> findAll(Integer page,Integer pageSize) throws Exception{
        PageHelper.startPage(page, pageSize);
        return ordersDao.findAll();
    }

    @Override
    public Orders findById(String id) throws Exception {
        Orders orders = ordersDao.findById(id);
        return orders;
    }
}
