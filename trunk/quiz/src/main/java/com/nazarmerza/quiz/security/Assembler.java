package com.nazarmerza.quiz.security;
import com.nazarmerza.quiz.domain.User;
import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.*;
@Service("assembler")
public class Assembler {

  @Transactional(readOnly = true)
  org.springframework.security.core.userdetails.User 
  buildUserFromUserEntity(User userEntity) {

    String username = userEntity.getUserName();
    String password = userEntity.getPassword();
    
    boolean enabled = true;
    boolean accountNonExpired = true;
    boolean credentialsNonExpired = true;
    boolean accountNonLocked = true;

    Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
    authorities.add(new GrantedAuthorityImpl(userEntity.getAuthority()));
    /*
    for (SecurityRoleEntity role : userEntity.getRoles()) {
      authorities.add(new GrantedAuthorityImpl(role.getRoleName()));
    }
	*/
    org.springframework.security.core.userdetails.User user = 
    		new org.springframework.security.core.userdetails.User(username, password, enabled,
      accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
   
    return user;
  }
}

