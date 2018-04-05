package com.cantonsoft.core.account;

import com.cantonsoft.core.account.user.model.GenericUser;

public interface LoginSuccessProcessor {
	void process(GenericUser user);
}
