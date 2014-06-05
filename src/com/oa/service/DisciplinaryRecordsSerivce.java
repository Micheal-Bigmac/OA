package com.oa.service;

import java.util.List;
import com.oa.model.DisciplinaryRecords;

public interface DisciplinaryRecordsSerivce {
	public void addDis(DisciplinaryRecords disciplinaryRecords);
	public void updateDis(DisciplinaryRecords disciplinaryRecords);
	public List<DisciplinaryRecords> selectDis();
	public DisciplinaryRecords selectDis(Class<DisciplinaryRecords> class1,
			Integer disId);
	public void deleteDis(DisciplinaryRecords dis);
	public List<DisciplinaryRecords> getDisciplinaryRecordsPages(int i,
			Class<DisciplinaryRecords> class1, String hql);
	public List<DisciplinaryRecords> getAllDisciplinaryRecords(
			Class<DisciplinaryRecords> class1, String hql);
}
