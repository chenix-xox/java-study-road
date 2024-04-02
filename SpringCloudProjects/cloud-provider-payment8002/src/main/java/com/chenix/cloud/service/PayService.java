package com.chenix.cloud.service;

import com.chenix.cloud.entities.Pay;

import java.util.List;

/**
 * @author Chenix
 * @create_date 2024/3/28 1:06
 */
public interface PayService {
    int add(Pay pay);

    int delete(Integer id);

    int update(Pay pay);

    Pay getById(Integer id);

    List<Pay> getAll();
}
