package com.nssol_sh.s5portal.persistence;

import java.util.List;

import com.nssol_sh.s5portal.domain.Class1sts;
import com.nssol_sh.s5portal.domain.Class2sts;
import com.nssol_sh.s5portal.domain.Projects;
import com.nssol_sh.s5portal.domain.Status;
import com.nssol_sh.s5portal.domain.Tracker;

public interface CommonMapper {

	Tracker getTrackerByName(String name);

	List<Tracker> getTrackers();

	List<Class1sts> getClass1stsByProjectIdentifier(String projectIdentifier);

	List<Class2sts> getClass2stsByParentId(int parentId);

	Status getStatusByName(String name);
	
	Projects getProjectByIdentifier(String identifier);
}
