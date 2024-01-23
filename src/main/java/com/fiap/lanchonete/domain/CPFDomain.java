package com.fiap.lanchonete.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CPFDomain {

    private String numero;

    public CPFDomain(String numero) {
        if (validarCPF(numero)) {
            this.numero = numero;
        } else {
            throw new IllegalArgumentException("CPF inválido");
        }
    }

    private boolean validarCPF(String cpf) {
        // Remover caracteres não numéricos do CPF
        cpf = cpf.replaceAll("\\D", "");

        // Verificar se o CPF possui 11 dígitos
        if (cpf.length() != 11) {
            return false;
        }

        // Verificar se todos os dígitos são iguais
        if (cpf.matches("(\\d)\\1{10}")) {
            return false;
        }

        // Validar os dígitos verificadores
        int digito1 = calcularDigitoVerificador(cpf.substring(0, 9), 10);
        int digito2 = calcularDigitoVerificador(cpf.substring(0, 9) + digito1, 11);

        // Verificar se os dígitos verificadores calculados são iguais aos informados
        return cpf.equals(cpf.substring(0, 9) + digito1 + digito2);
    }

    private int calcularDigitoVerificador(String cpf, int peso) {
        int soma = 0;
        for (int i = 0; i < cpf.length(); i++) {
            soma += Character.getNumericValue(cpf.charAt(i)) * peso--;
        }
        int resto = soma % 11;
        return resto < 2 ? 0 : 11 - resto;
    }
}
