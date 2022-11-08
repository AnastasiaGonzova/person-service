package core.service;

import core.api.repository.RoleRepository;
import core.api.repository.service.*;
import core.api.service.*;
import core.model.*;
import core.model.UserRole.UserRole;
import core.model.UserRole.UserRoleKey;
import dto.internal.LoginRequest;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.Clock;
import java.time.LocalDate;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class SecurityServiceImpl implements SecurityService {

    private UserService userService;
    private TokenService tokenService;
    private PersonDataService personDataService;
    private MedicalCardService medicalCardService;
    private ContactService contactService;
    private RoleRepository roleRepository;
    private UserRoleRepository userRoleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public String login(LoginRequest loginRequest) {
        User user = Optional.of(userService.findByUsername(loginRequest.getUsername()))
                .orElseThrow(() -> new RuntimeException("User not found"));
        if (bCryptPasswordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            return tokenService.generateToken(user);
        } else {
            throw new RuntimeException();
        }
    }

    @Override
    public String sighUp(PersonData personDataJson, User userJson, Contact contact) {
        Random random = new Random();

        userJson.setPassword(bCryptPasswordEncoder.encode(userJson.getPassword()));
        userJson.setPersonData(personDataJson);
        Long randomId = null;
        if(personDataJson.getId() == null){

            do{
                randomId = random.nextLong();

            }while(personDataService.get(randomId) == null);

            personDataJson.setId(randomId);
        }
        do{
            randomId = random.nextLong();
        }while (contactService.get(randomId) == null);

        contact.setId(randomId);
        personDataJson.getContacts().add(contact);

        do{
            randomId = random.nextLong();
        }while (medicalCardService.get(randomId) == null);

        MedicalCard medicalCard = new MedicalCard(
                randomId, "REG", "H",
                Date.valueOf(LocalDate.now(Clock.systemDefaultZone())), "");
        personDataJson.setMedicalCard(medicalCard);

        final Role role = roleRepository.findByRoleName("USER");
        final UserRoleKey userRoleKey = new UserRoleKey(userJson.getId(), role.getId());
        UserRole userRole = new UserRole();
        userRole.setId(userRoleKey);
        userRole.getUsers().add(userJson);
        userRole.getRoles().add(role);
        userJson.getRoles().add(userRole);
        role.getUsers().add(userRole);

        userRoleRepository.save(userRole);
        medicalCardService.create(medicalCard);
        contactService.create(contact);
        userService.create(userJson);
        personDataService.create(personDataJson);
        return tokenService.generateToken(userJson);
    }
}
