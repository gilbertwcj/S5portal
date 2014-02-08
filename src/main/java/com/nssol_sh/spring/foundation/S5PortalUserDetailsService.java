package com.nssol_sh.spring.foundation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.nssol_sh.s5portal.domain.RedmineInfo;
import com.nssol_sh.s5portal.service.RedmineInfoService;


public class S5PortalUserDetailsService implements UserDetailsService {

	@Autowired
	@Qualifier("redmineInfoServiceForCas")
	private RedmineInfoService redmineInfoService;

	@Override
	public UserDetails loadUserByUsername(String name)
			throws UsernameNotFoundException {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		DataSourceContextHolder.setDefaultDataSource();
		List<RedmineInfo> userInfo =  redmineInfoService.getRedmineInfoByUserId(name);
		for (RedmineInfo redmineInfo : userInfo) {
			authorities.add(new SimpleGrantedAuthority(redmineInfo.getUrl()+"|"+redmineInfo.getProjectId()));
		}		
		//authorities.add(new SimpleGrantedAuthority("S5_USER"));
		UserDetails userDetails = new User(name, "password", authorities);
		return userDetails;
	}

}
