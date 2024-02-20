package info.petrsabata.beereview;

import info.petrsabata.beereview.service.StartupService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BeereviewApplication {

    private final StartupService startupService;

    public BeereviewApplication(
            @Autowired StartupService startupService
    ) {
        this.startupService = startupService;
    }

    public static void main(String[] args) {
        SpringApplication.run(BeereviewApplication.class, args);
    }

    @PostConstruct
    public void init() {
        startupService.initResourcesOnStartup();
    }

}
