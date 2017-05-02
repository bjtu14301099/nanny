package com.bjtu.dao;

import java.util.List;

import com.bjtu.entity.NannyUser;

public interface UserDao {
	// �õ������û�
    public List<NannyUser> getAllUser();

    // ����û����Ƿ����
    public boolean isExists(String username);
    
    //����˺������Ƿ���ȷ
    public boolean checkLogin(String username,String password);
    
    //��ע��
    public void register(String username,String password);
}
