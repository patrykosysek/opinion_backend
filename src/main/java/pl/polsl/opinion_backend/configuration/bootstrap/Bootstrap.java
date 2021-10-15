package pl.polsl.opinion_backend.configuration.bootstrap;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import pl.polsl.opinion_backend.services.bootstrap.BootstrapService;


@Component
@Slf4j
@RequiredArgsConstructor
public class Bootstrap {
    private final BootstrapService bootstrapService;

    @EventListener(ApplicationReadyEvent.class)
    public void init() {
        try {
            bootstrapService.setup();
            log.info("Bootstrap success");
        } catch (Exception e) {
            log.error("Bootstrap failed," + e);
            e.printStackTrace();
            throw e;
        }
    }

}