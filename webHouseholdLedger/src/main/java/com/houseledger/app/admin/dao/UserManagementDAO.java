package com.houseledger.app.admin.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.houseledger.app.admin.dto.SelectUserListDTO;
import com.houseledger.app.admin.dto.UserManagementInfoDTO;
import com.houseledger.app.common.dao.AbstractDAO;

@Repository("userManagementDAO")
public class UserManagementDAO extends AbstractDAO {
	
	@SuppressWarnings("unchecked")
	public List<UserManagementInfoDTO> selectUserList(SelectUserListDTO dto) throws Exception {
		
		return selectList("userManagement.selectUserList", dto);
	}
	
}
