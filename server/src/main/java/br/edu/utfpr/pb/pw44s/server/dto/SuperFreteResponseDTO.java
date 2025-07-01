package br.edu.utfpr.pb.pw44s.server.dto;

import lombok.Data;

@Data
public class SuperFreteResponseDTO {
    private int id;
    private String name;
    private double price;
    private int delivery_time;
}
