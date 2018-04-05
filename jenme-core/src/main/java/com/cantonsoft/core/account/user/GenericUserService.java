package com.cantonsoft.core.account.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cantonsoft.core.account.user.dao.ClientUserDao;
import com.cantonsoft.core.account.user.dao.PartnerUserDao;
import com.cantonsoft.core.account.user.dao.UserDao;
import com.cantonsoft.core.account.user.model.ClientUser;
import com.cantonsoft.core.account.user.model.GenericUser;
import com.cantonsoft.core.account.user.model.PartnerUser;
import com.cantonsoft.core.account.user.model.User;
import com.cantonsoft.core.account.user.model.UserAuth;
import com.cantonsoft.core.cloud.client.model.Client;
import com.cantonsoft.core.cloud.partner.model.Partner;
import com.cantonsoft.framework.util.DictionaryManager;

@Service
public class GenericUserService {
	@Autowired
	private UserDao userDao;
	@Autowired
	private PartnerUserDao partnerUserDao;
	@Autowired
	private ClientUserDao clientUserDao;
	@Autowired
	private DictionaryManager dictionaryManager;
	@Autowired
	private UserService userService;
	@Autowired
	private AuthService authService;
	@Autowired
	private PartnerUserService partnerUserService;
	@Autowired
	private ClientUserService clientUserService;
	
	public GenericUser findUser(String domain, Long userId)
	{
		GenericUser user = null;
		if (UserAuth.AUTH_TYPE_MAIN.equals(domain))
		{
			user = userDao.findOne(userId);
		}
		else if (UserAuth.AUTH_TYPE_PARTNER.equals(domain))
		{
			user = partnerUserDao.findOne(userId);
		}
		else if (UserAuth.AUTH_TYPE_CLIENT.equals(domain))
		{
			user = clientUserDao.findOne(userId);
		}
		return user;
	}
	
	public void saveUser(String domain, Long userId, UserProfile profile)
	{
		if (UserAuth.AUTH_TYPE_MAIN.equals(domain))
		{
			User user = userService.getUpdateObject(userId, profile);
			userService.update(user);
		}
		else if(UserAuth.AUTH_TYPE_PARTNER.equals(domain))
		{
			PartnerUser user = partnerUserService.getUpdateObject(userId, profile);
			partnerUserService.update(user);
		}
		else if(UserAuth.AUTH_TYPE_CLIENT.equals(domain))
		{
			ClientUser user = clientUserService.getUpdateObject(userId, profile);
			clientUserService.update(user);
		}
	}
	
	public void createPartnerUser(Partner partner) {
		PartnerUser partnerUser = new PartnerUser();
		partnerUser.setEmail(partner.getEmail());
		partnerUser.setEnabled(true);
		partnerUser.setTitle("管理员");
		partnerUser.setPhone(partner.getPhone());
		partnerUser.setType(PartnerUser.TYPE_ADMIN);
		partnerUser.setPartnerId(partner.getId());
		partnerUserDao.save(partnerUser);
		String username = dictionaryManager.getDictByUniqueKey("setting:partnerUserName").getValue() + "@" + partner.getId();
		String password = dictionaryManager.getDictByUniqueKey("setting:partnerUserPassword").getValue();
		authService.createAuthInfo(partnerUser.getDomain(), partnerUser.getId(), username, password);
	}


	public void createClientUser(Client client) {
		ClientUser clientUser = new ClientUser();
		clientUser.setClientId(client.getId());
		clientUser.setTitle("管理员");
		clientUser.setEmail(client.getEmail());
		clientUser.setPhone(client.getPhone());
		clientUser.setEnabled(true);
		clientUser.setType(ClientUser.TYPE_ADMIN);
		clientUserDao.save(clientUser);
		String username = dictionaryManager.getDictByUniqueKey("setting:clientUserName").getValue() + "@" + client.getId();
		String password = dictionaryManager.getDictByUniqueKey("setting:clientUserPassword").getValue();
		authService.createAuthInfo(clientUser.getDomain(), clientUser.getId(), username, password);
	}
}
