package com.eshop.service.interf;

import com.eshop.entrity.User;

public interface UserService {

  public void updateInfo(User user, String company)throws Exception;

public User getInfoById(int user_id, String company)throws Exception;

}
