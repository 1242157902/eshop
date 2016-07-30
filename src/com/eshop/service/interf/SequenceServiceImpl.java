package com.eshop.service.interf;

import com.eshop.dao.SequenceDao;
import com.eshop.entrity.Sequence;
import com.eshop.service.SequenceService;

public class SequenceServiceImpl  implements SequenceService{

	SequenceDao sd = new SequenceDao();
	@Override
	public Sequence getSequenceByType(String type,String company) throws Exception {
		return sd.getSequenceByType(type, company);
	}

	@Override
	public void updateSequence(Sequence sequence, String company)
			throws Exception {
		sd.updateSequence(sequence, company);
	}

 

}
