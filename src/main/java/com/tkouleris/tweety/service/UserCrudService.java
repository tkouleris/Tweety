package com.tkouleris.tweety.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.tkouleris.tweety.dao.UserRepository;
import com.tkouleris.tweety.model.User;

@Service
public class UserCrudService {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private UserRepository R_User;
	 	
	public User createNewUser(User user) throws Exception
	{
		String UserEmail = user.getEmail().trim();
		String UserUsername = user.getUsername().trim();
		String UserPassword = user.getPassword().trim();
		
		boolean UsernameIsNotValid = (UserUsername == null) || !UserUsername.matches("[A-Za-z0-9_]+");
		/* Email Restriction
		 * ---------------------
		 *This expression matches email addresses, and checks that they are of the proper form. 
		 *It checks to ensure the top level domain is between 2 and 4 characters long, 
		 *but does not check the specific domain against a list (especially since 
		 *there are so many of them now).		  		
		 */
		boolean EmailIsNotValid = (UserEmail == null ) || !UserEmail.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
		
		/* Password Restriction
		 * ------------------------
		 * At least 8 chars
		 * Contains at least one digit
		 * Contains at least one lower alpha char and one upper alpha char
		 * Contains at least one char within a set of special chars (@#%$^ etc.)
		 * Does not contain space, tab, etc.
		 */
		boolean PasswordIsNotValid = (UserPassword == null ) 
				|| !UserPassword.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,20}");
		
		if(UsernameIsNotValid) throw new Exception("Username not set or not valid!");
		if(PasswordIsNotValid ) throw new Exception("Password not set or not valid");
		if(EmailIsNotValid) throw new Exception("Email not set or not valid!");		
				
		
	    user.setPassword(passwordEncoder.encode(user.getPassword()));	    
	    return R_User.save(user);
	}
}