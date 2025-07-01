package br.edu.utfpr.pb.pw44s.server.controller;

import br.edu.utfpr.pb.pw44s.server.dto.SuperFreteResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/shipping")
@CrossOrigin(origins = "*")
public class ShippingController {

    private static final String SUPERFRETE_API_URL = "https://api.superfrete.com/api/v1/shipping-calculate";
    private static final String SUPERFRETE_API_TOKEN = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpYXQiOjE3NTExNDc5OTgsInN1YiI6Im1pWkhkNXhQWnlhS0tONFY1eEVJeTVrT1A0aDEifQ.yURroeGw63HyFqxHl-z2ufKXwNNNv8CuvaKXWtQaXeo";
    private static final String ORIGIN_CEP = "85501000";

    private static final Logger logger = LoggerFactory.getLogger(ShippingController.class);

    @GetMapping("/calculate")
    public ResponseEntity<SuperFreteResponseDTO[]> calculateShipping(@RequestParam String cep) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + SUPERFRETE_API_TOKEN);
        headers.set("Content-Type", "application/json");
        headers.set("User-Agent", "Aplicacao pw44s (arthurcristiano248@gmail.com)");

        Map<String, Object> requestBody = new HashMap<>();
        Map<String, String> from = new HashMap<>();
        from.put("postal_code", ORIGIN_CEP);

        Map<String, String> to = new HashMap<>();
        to.put("postal_code", cep);

        requestBody.put("services", "1,2");

        Map<String, Object> packageDetails = new HashMap<>();
        packageDetails.put("height", 20);
        packageDetails.put("width", 20);
        packageDetails.put("length", 20);
        packageDetails.put("weight", 1);

        requestBody.put("from", from);
        requestBody.put("to", to);
        requestBody.put("package", packageDetails);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers);

        try {
            ResponseEntity<SuperFreteResponseDTO[]> response = restTemplate.exchange(
                    SUPERFRETE_API_URL,
                    HttpMethod.POST,
                    entity,
                    SuperFreteResponseDTO[].class
            );
            return ResponseEntity.ok(response.getBody());

        } catch (HttpClientErrorException e) {
            logger.error("Erro ao chamar a API da SuperFrete. Status: {}", e.getStatusCode());
            logger.error("Resposta da API (que provavelmente é HTML): {}", e.getResponseBodyAsString());
            return ResponseEntity.status(e.getStatusCode()).build();
        } catch (Exception e) {
            logger.error("Ocorreu um erro inesperado", e);
            return ResponseEntity.internalServerError().build();
        }
    }
}
