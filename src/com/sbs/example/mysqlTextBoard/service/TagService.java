package com.sbs.example.mysqlTextBoard.service;

import java.util.List;

import com.sbs.example.mysqlTextBoard.container.Container;
import com.sbs.example.mysqlTextBoard.dao.TagDao;
import com.sbs.example.mysqlTextBoard.dto.Tag;

public class TagService {
	private TagDao tagDao;

	public TagService() {
		tagDao = Container.tagDao;
	}

	public List<String> getTagBodiesByRelTypeCode(String relTypeCode) {
		return tagDao.getTagBodiesByRelTypeCode(relTypeCode);
	}

	public List<String> getArticleTags(String relTypeCode, int relId) {
		return tagDao.getArticleTags(relTypeCode, relId);
	}

}
