package core.service;

import core.api.repository.RoleRepository;
import core.api.repository.UserRepository;
import core.api.service.UserService;
import core.model.User;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private RoleRepository roleRepository;

    private ModelMapper modelMapper;

    @Override
    @Transactional
    public User create(User userJson) {
        return userRepository.saveAndFlush(userJson);
    }

    @Override
    @Transactional
    public User update(Long id, User userJson) {
        User updatedUser = userRepository.getById(id);
        modelMapper.map(userJson, updatedUser);
        return userRepository.save(updatedUser);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow();
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }

        /*Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        user.getRoles().stream().forEach(userRole->
                grantedAuthorities.add(
                        new SimpleGrantedAuthority(
                                roleRepository.findById(userRole.getId().getRoleId()).orElseThrow().getRoleName())));

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);*/
        return null;
    }
}
