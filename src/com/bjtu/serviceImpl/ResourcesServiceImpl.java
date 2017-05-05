package com.bjtu.serviceImpl;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bjtu.dao.ResourcesDao;
import com.bjtu.service.ResourcesService;

@Service
public class ResourcesServiceImpl implements ResourcesService{
	@Autowired
    private ResourcesDao resourcesDao;

	public ResourcesDao getResourcesDao() {
		return resourcesDao;
	}

	public void setResourcesDao(ResourcesDao resourcesDao) {
		this.resourcesDao = resourcesDao;
	}

	@Override
	public HashMap<Integer, String> getImageUrl(int count) {
		return resourcesDao.getImageUrl(count);
	}

	//�����ϴ�ͼƬ��URL
	@Override
	public void savePicUrl(int user_id,String pic_url) {
		resourcesDao.savePicUrl(user_id, pic_url);
	}

}
