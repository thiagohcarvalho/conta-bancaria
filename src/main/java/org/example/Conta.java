package org.example;

import java.math.BigDecimal;

public class Conta {
    private final int numero;

    private BigDecimal saldo;

    private final Cliente cliente;

    public Conta(int numero, Cliente cliente) {
        this.numero = numero;
        this.saldo = BigDecimal.ZERO;
        this.cliente = cliente;
    }

    public int getNumero() {
        return numero;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void depositar(BigDecimal valorADepositar) {
        if (valorADepositar.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("O valor deve ser positivo.");
        }
        saldo = saldo.add(valorADepositar);
    }

    public void sacar(BigDecimal valorASacar) {
        if (valorASacar.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("O valor deve ser positivo.");
        }
        if (valorASacar.compareTo(saldo) > 0) {
            throw new IllegalArgumentException("Saldo não suficiente para saque! Verifique seu saldo.");
        }
        saldo = saldo.subtract(valorASacar);
    }
}
