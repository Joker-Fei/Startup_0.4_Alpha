package com.syf.dao;

import com.syf.model.Update;

public interface UpdateDao {

	int find(Update update);

	Update findByVersion(Update update);

	Update findNewVersion();

}
