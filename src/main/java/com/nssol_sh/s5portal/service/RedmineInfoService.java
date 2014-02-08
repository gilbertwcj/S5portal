package com.nssol_sh.s5portal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nssol_sh.s5portal.domain.RedmineInfo;
import com.nssol_sh.s5portal.persistence.RedmineInfoMapper;

@Service
public class RedmineInfoService {

	@Autowired
	private RedmineInfoMapper redmineInfoMapper;

	public List<RedmineInfo> getRedmineInfoByUserId(String userId) {
		return redmineInfoMapper.getRedmineInfoByUserId(userId);
	}

}
