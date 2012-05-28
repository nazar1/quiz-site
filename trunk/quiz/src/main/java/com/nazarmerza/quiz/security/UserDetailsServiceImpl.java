package com.nazarmerza.quiz.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;    
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nazarmerza.quiz.dao.UserDao;
import com.nazarmerza.quiz.domain.User;

@Service("userDetailsService") 
public class UserDetailsServiceImpl implements UserDetailsService {
 
	@Autowired
  private UserDao userDao;
	
	@Autowired
  private Assembler assembler;
  
  /*
  @Autowired
  public UserDetailsServiceImpl(UserDao userDao, Assembler assembler){
	  this.userDao = userDao;
	  this.assembler = assembler;
  }

	*/
  
  @Transactional(readOnly = true)
  public UserDetails loadUserByUsername(String username)
      throws UsernameNotFoundException, DataAccessException {

    //UserDetails userDetails = null;
    User userEntity = userDao.findByName(username);
    if (userEntity == null)
      throw new UsernameNotFoundException("user not found");
    
    return assembler.buildUserFromUserEntity(userEntity);
  }
}
