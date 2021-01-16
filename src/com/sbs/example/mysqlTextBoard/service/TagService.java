package com.sbs.example.mysqlTextBoard.service;

import java.util.List;

import com.sbs.example.mysqlTextBoard.container.Container;
import com.sbs.example.mysqlTextBoard.dao.TagDao;

public class TagService {
	private TagDao tagDao;

	public TagService() {
		tagDao = Container.tagDao;
	}

	public List<String> getTagBodiesByRelTypeCode(String relTypeCode) {
		return tagDao.getTagBodiesByRelTypeCode(relTypeCode);
	}

}
