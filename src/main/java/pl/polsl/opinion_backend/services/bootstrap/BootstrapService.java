package pl.polsl.opinion_backend.services.bootstrap;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.polsl.opinion_backend.entities.bootstrap.BootstrapStatus;
import pl.polsl.opinion_backend.entities.role.RoleGroup;
import pl.polsl.opinion_backend.entities.user.User;
import pl.polsl.opinion_backend.enums.role.RoleGroupEnum;
import pl.polsl.opinion_backend.services.role.RoleGroupService;
import pl.polsl.opinion_backend.services.user.UserService;

import java.util.Arrays;
import java.util.HashSet;

@Service
@RequiredArgsConstructor
@Slf4j
public class BootstrapService {
    private final PasswordEncoder passwordEncoder;
    private final RoleGroupService roleGroupService;
    private final UserService userService;
    private final BootstrapStatusService bootstrapStatusService;


    public void setup() {
        if (bootstrapStatusService.isDone()) {
            log.info("Bootstrap already done");
        } else {
            createRoleGroups();
            createDefaultAdmin();
            createDefaultUser();
            bootstrapStatusService.save(new BootstrapStatus(true));
        }
    }


    @Transactional
    public void createRoleGroups() {
        Arrays.stream(RoleGroupEnum.values()).forEach(roleGroupName -> {
            RoleGroup roleGroup = new RoleGroup(roleGroupName.name());
            roleGroupService.save(roleGroup);
        });
    }


    public void createDefaultAdmin() {
        User user = new User(
                "admin@onet.pl",
                passwordEncoder.encode("ADMIN"),
                new HashSet<>(),
                true
        );
        user.getRoleGroups().add(roleGroupService.getByRoleName("ADMIN"));
        userService.save(user);
    }

    public void createDefaultUser() {
        User user = new User(
                "user@onet.pl",
                passwordEncoder.encode("PATIENT"),
                new HashSet<>(),
                true
        );
        user.getRoleGroups().add(roleGroupService.getByRoleName("PATIENT"));
        userService.save(user);
    }

}