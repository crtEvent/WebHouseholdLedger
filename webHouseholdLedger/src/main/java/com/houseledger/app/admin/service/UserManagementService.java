package com.houseledger.app.admin.service;

import java.util.List;

import com.houseledger.app.admin.dto.SelectUserListDTO;
import com.houseledger.app.admin.dto.UserManagementInfoDTO;

public interface UserManagementService {
	
	public List<UserManagementInfoDTO> getuserList(SelectUserListDTO dto) throws Exception;

}
