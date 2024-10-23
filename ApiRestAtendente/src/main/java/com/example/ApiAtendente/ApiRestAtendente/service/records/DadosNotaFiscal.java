package com.example.ApiAtendente.ApiRestAtendente.service.records;

import java.util.List;

public record DadosNotaFiscal(
        String cliente,
        List<DadosItensNota> itensNota,
        Double precoTotal
) {

}
