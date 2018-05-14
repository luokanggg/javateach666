package com.ctbu.javateach666.service.impl.kingother;

import org.springframework.stereotype.Service;

import com.ctbu.javateach666.common.service.BaseServiceImpl;
import com.ctbu.javateach666.dao.AccountDao;
import com.ctbu.javateach666.pojo.po.kingother.Account;
import com.ctbu.javateach666.service.interfac.kingother.AccountService;

/**
 * 账号service实现
 * @author king
 *
 */
@Service
public class AccountServiceImpl extends BaseServiceImpl<AccountDao, Account> implements AccountService{
	
}
