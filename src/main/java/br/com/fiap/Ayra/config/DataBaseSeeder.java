package br.com.fiap.Ayra.config;

import br.com.fiap.Ayra.model.*;
import br.com.fiap.Ayra.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.time.ZonedDateTime;

@Configuration
public class DataBaseSeeder implements CommandLineRunner {

    @Autowired
    private CoordinatesRepository coordinatesRepository;

    @Autowired
    private MapMarkerRepository mapMarkerRepository;

    @Autowired
    private AlertRepository alertRepository;

    @Autowired
    private SafeRoutesRepository safeRoutesRepository;

    @Autowired
    private SafeLocationRepository safeLocationRepository;

    @Autowired
    private SafeTipRepository safeTipsRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {

        // 1. Coordenadas
        Coordinates coord1 = Coordinates.builder()
                .latitude(-23.550520)
                .longitude(-46.633308)
                .dateCoordinate(LocalDate.now().minusDays(1))
                .build();

        Coordinates coord2 = Coordinates.builder()
                .latitude(-23.551000)
                .longitude(-46.634000)
                .dateCoordinate(LocalDate.now())
                .build();

        coordinatesRepository.save(coord1);
        coordinatesRepository.save(coord2);

        // 2. MapMarker
        MapMarker marker1 = MapMarker.builder()
                .title("Água represada")
                .description("Água represada após chuvas fortes na região")
                .intensity("high")
                .radius(150.0)
                .coordinates(coord1)
                .build();

        MapMarker marker2 = MapMarker.builder()
                .title("Área de risco")
                .description("Área com possibilidade de deslizamento")
                .intensity("medium")
                .radius(100.0)
                .coordinates(coord2)
                .build();

        mapMarkerRepository.save(marker1);
        mapMarkerRepository.save(marker2);

        // 3. Alertas
        Alert alert1 = Alert.builder()
                .title("Inundação em bairro X")
                .description("Inundação severa próxima ao rio com risco para moradores")
                .intensity("high")
                .alertDatetime(ZonedDateTime.now().minusHours(2))
                .location("Bairro X")
                .radius(200.0)
                .coordinates(coord1)
                .mapMarker(marker1)
                .build();

        Alert alert2 = Alert.builder()
                .title("Deslizamento de terra")
                .description("Possível deslizamento de terra em encosta")
                .intensity("medium")
                .alertDatetime(ZonedDateTime.now().minusHours(5))
                .location("Bairro Y")
                .radius(120.0)
                .coordinates(coord2)
                .mapMarker(marker2)
                .build();

        alertRepository.save(alert1);
        alertRepository.save(alert2);

        // 4. SafeRoutes
        SafeRoutes safeRoute1 = SafeRoutes.builder()
                .route("Rua segura 1 -> Rua segura 2 -> Avenida Principal")
                .alert(alert1)
                .build();

        SafeRoutes safeRoute2 = SafeRoutes.builder()
                .route("Rua alternativa -> Rua do Parque")
                .alert(alert2)
                .build();

        safeRoutesRepository.save(safeRoute1);
        safeRoutesRepository.save(safeRoute2);

        // 5. SafeLocation (estrutura igual ao SafeRoutes)
        SafeLocation safeLocation1 = SafeLocation.builder()
                .location("Ponto seguro próximo ao mercado")
                .alert(alert1)
                .build();

        SafeLocation safeLocation2 = SafeLocation.builder()
                .location("Abrigo comunitário")
                .alert(alert2)
                .build();

        safeLocationRepository.save(safeLocation1);
        safeLocationRepository.save(safeLocation2);

        // 6. SafeTips (estrutura igual ao SafeRoutes)
        SafeTip safeTip1 = SafeTip.builder()
                .tip("Evite áreas próximas ao rio em dias de chuva forte")
                .alert(alert1)
                .build();

        SafeTip safeTip2 = SafeTip.builder()
                .tip("Não transite por encostas instáveis após chuvas")
                .alert(alert2)
                .build();

        safeTipsRepository.save(safeTip1);
        safeTipsRepository.save(safeTip2);

        // 7. User com senha criptografada
        User user1 = User.builder()
                .name("Dias Silva")
                .email("dias@example.com")
                .password(passwordEncoder.encode("12345"))
                .phone("11999999999")
                .coordinates(coord1)
                .build();

        User user2 = User.builder()
                .name("Maria Souza")
                .email("maria@example.com")
                .password(passwordEncoder.encode("senha123"))
                .phone("11988888888")
                .coordinates(coord2)
                .build();

        userRepository.save(user1);
        userRepository.save(user2);

        System.out.println("Database seeded with sample data!");
    }
}

