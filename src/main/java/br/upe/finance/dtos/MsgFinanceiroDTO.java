package br.upe.finance.dtos;

import java.time.LocalDate;

public record MsgFinanceiroDTO(
    Integer id,       
    Integer idMedico,      
    Double valorConsulta,  
    LocalDate dataConsulta 
) {}