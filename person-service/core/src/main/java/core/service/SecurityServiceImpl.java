package core.service;

import core.api.repository.RoleRepository;
import core.api.service.*;
import core.model.*;
import dto.internal.LoginRequest;
import dto.internal.SignUpRequest;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.Clock;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SecurityServiceImpl implements SecurityService {

    private UserService userService;
    private TokenService tokenService;
    private PersonDataService personDataService;
    private MedicalCardService medicalCardService;
    private ContactService contactService;
    private RoleRepository roleRepository;
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
    public String sighUp(SignUpRequest signUpRequest) {

        PersonData personData = new PersonData();
        Contact contact = new Contact();
        User user = new User();

        MedicalCard medicalCard = new MedicalCard( null, "REG", "H",
                Date.valueOf(LocalDate.now(Clock.systemDefaultZone())), "", null, new HashSet<>());

        contact.setPhoneNumber(signUpRequest.getPhoneNumber());
        contact.setEmail(signUpRequest.getEmail());
        contact.setProfileLink(signUpRequest.getProfileLink());

        personData.setLastName(signUpRequest.getLastName());
        personData.setFirstName(signUpRequest.getFirstName());
        personData.setBirthDt(signUpRequest.getBirthDt());
        personData.setAge(signUpRequest.getAge());
        personData.setSex(signUpRequest.getSex());
        personData.setContact(contact);
        personData.setMedicalCard(medicalCard);


        user.setUsername(signUpRequest.getUsername());
        user.setPassword(bCryptPasswordEncoder.encode(signUpRequest.getPassword()));
        user.setPersonData(personData);

        final Role role = roleRepository.findByRoleName("USER");
        user.getRoles().add(role);
        role.getUsers().add(user);

        medicalCardService.create(medicalCard);
        contactService.create(contact);
        userService.create(user);
        personDataService.create(personData);
        return tokenService.generateToken(user);
    }
}
