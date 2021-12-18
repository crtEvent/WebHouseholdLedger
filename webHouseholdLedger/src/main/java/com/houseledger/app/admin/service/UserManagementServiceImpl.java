package com.houseledger.app.admin.service;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.houseledger.app.admin.dao.UserManagementDAO;
import com.houseledger.app.admin.dto.SelectUserListDTO;
import com.houseledger.app.admin.dto.UserManagementInfoDTO;

@Service("userManagementService")
public class UserManagementServiceImpl implements UserManagementService{
	
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Resource(name = "userManagementDAO")
	UserManagementDAO userManagementDAO;
	
	public List<UserManagementInfoDTO> getuserList(SelectUserListDTO dto) throws Exception {
		return userManagementDAO.selectUserList(dto);
	}
}
