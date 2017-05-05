package com.bjtu.service;

import java.util.List;

import com.bjtu.entity.NannyUser;
import com.bjtu.entity.UserBaseInfo;

public interface NannyUserService {
	public List<NannyUser> getAllUser();
    public boolean isExists(String username);
    public boolean checkLogin(String username,String password);
    
    //ע��
    public void register(String username,String password);
    
    //����û�������Ϣ
    public void addBaseInof(UserBaseInfo userBaseInfo);
    
    //����username��ȡid
    public int getIdByUsername(String username);
}
