package in.nucleusteq.plasma.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import in.nucleusteq.plasma.entity.UserWorkDetail;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import in.nucleusteq.plasma.entity.Employee;
import in.nucleusteq.plasma.entity.Role;

public class CustomUserDetails implements UserDetails {

	private static final long serialVersionUID = 1L;
	private Employee user;

	public CustomUserDetails(Employee credential) {
		super();
		this.user = credential;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		UserWorkDetail workDetail = user.getUserWorkDetail();

		Set<Role> roles = workDetail.getRoles();
		System.out.println(roles.toString() + " Roles in authorities ");
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();

		for (Role role : roles) {
			authorities.add(new SimpleGrantedAuthority(role.getName()));
		}

		return authorities;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
