package com.hopware.unirestprueba.service.dto;

import java.time.LocalDate;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Loan entity.
 */
public class LoanDTO implements Serializable{

    private Long id;

    private String nombre;

    private Double monto;

    private String banco;

    private LocalDate fecha;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }
    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public LocalDate getFecha() {
        return fecha;
    }
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        LoanDTO loanDTO = (LoanDTO) o;

        if ( ! Objects.equals(id, loanDTO.id)) { return false; }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "LoanDTO{" +
            "id=" + id +
            ", nombre='" + nombre + "'" +
            ", monto='" + monto + "'" +
            ", banco='" + banco + "'" +
            ", fecha='" + fecha + "'" +
            '}';
    }


//    public void settearObjectMapper() throws IOException, UnirestException{
//
//        //Agregar throw IOException y JsonProcessingException
//        Unirest.setObjectMapper(new ObjectMapper() {
//            private com.fasterxml.jackson.databind.ObjectMapper jacksonObjectMapper
//                = new com.fasterxml.jackson.databind.ObjectMapper();
//
//            public <T> T readValue(String value, Class<T> valueType) {
//                try {
//                    return jacksonObjectMapper.readValue(value, valueType);
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//
//
//
//            public String writeValue(Object value) {
//                try {
//                    return jacksonObjectMapper.writeValueAsString(value);
//                } catch (JsonProcessingException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//        });
//
//
////        try{
////        HttpResponse<String> response = Unirest.post("http://localhost:8080/api/loans")
////            .header("content-type", "application/json")
////            .header("cache-control", "no-cache")
////            .header("postman-token", "e7779dd3-6603-1633-0c61-f5c11788d325")
////            .body("  {\n    \"nombre\": \"tres\",\n    \"monto\": 12,\n    \"banco\": \"bcr\",\n    \"fecha\": \"2017-02-08\"\n  }")
////            .asString();
////
////
////            HttpResponse<String> response1 = Unirest.get("http://localhost:8080/api/loans")
////                .asString();
////            System.out.println(response1.getBody());
////        }catch (UnirestException o){
////            throw o;
////        }
//
//
//        // Response to Object
//        HttpResponse<Loan> loanResponse = Unirest.get("http://http://localhost:8080/#/loan/loan/2").asObject(Loan.class);
//        Loan loanObject = loanResponse.getBody();
//
////        HttpResponse<Loan> authorResponse = Unirest.get("http://httpbin.org/books/{id}/author")
////            .routeParam("id", loanObject.getId().toString())
////            .asObject(Loan.class);
////
////        Unirest.setObjectMapper(response1).writeValue(Loan.class);
//
//    }
}

