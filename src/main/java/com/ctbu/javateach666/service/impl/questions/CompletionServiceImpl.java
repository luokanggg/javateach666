package com.ctbu.javateach666.service.impl.questions;

import org.springframework.stereotype.Service;

import com.ctbu.javateach666.common.service.BaseServiceImpl;
import com.ctbu.javateach666.dao.CompletionDao;
import com.ctbu.javateach666.dao.SingleChoiceDao;
import com.ctbu.javateach666.pojo.po.questions.Completion;
import com.ctbu.javateach666.pojo.po.questions.SingleChoice;
import com.ctbu.javateach666.service.interfac.questions.CompletionService;
import com.ctbu.javateach666.service.interfac.questions.SingleChoiceService;

/**
 * 单选题service实现
 * @author king
 *
 */
@Service
public class CompletionServiceImpl extends BaseServiceImpl<CompletionDao, Completion> implements CompletionService{
	
}
