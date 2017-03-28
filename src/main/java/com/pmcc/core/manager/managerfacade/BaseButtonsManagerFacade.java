package com.pmcc.core.manager.managerfacade;

import com.pmcc.core.domain.BaseButtons;
import com.pmcc.core.domain.BaseRoleInfo;

import java.util.List;
import java.util.Map;

public interface BaseButtonsManagerFacade {

			public  List<BaseButtons> getButtonsList(int page, int limit, String swhere, String sort, String type);

			public Boolean save(BaseButtons baseButtons);

			public Map<String,Object> deleteById(String postData);
}
