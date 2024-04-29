package in.nucleusteq.plasma.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import in.nucleusteq.plasma.dao.EmployeeRepository;
import in.nucleusteq.plasma.entity.Employee;

@Component
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private EmployeeRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Employee credential = repository.getByEmail(username);

		if (credential == null) {
			throw new UsernameNotFoundException("user not found with name :" + username);
		}
		CustomUserDetails customUserDetails = new CustomUserDetails(credential);
		return customUserDetails;
	}
}