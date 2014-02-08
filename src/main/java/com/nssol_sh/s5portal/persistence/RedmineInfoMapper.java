package com.nssol_sh.s5portal.persistence;

import java.util.List;

import com.nssol_sh.s5portal.domain.RedmineInfo;

public interface RedmineInfoMapper {

	List<RedmineInfo> getRedmineInfoByUserId(String userId);
}