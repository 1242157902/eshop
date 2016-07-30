package com.eshop.service;

import com.eshop.entrity.Sequence;

public interface SequenceService {
	
	public  Sequence getSequenceByType(String type,String company)throws Exception;
	
	public  void updateSequence(Sequence sequence,String company)throws Exception;
}
