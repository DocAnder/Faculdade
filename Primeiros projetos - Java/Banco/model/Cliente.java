package model;

import java.util.Date;

import helper.Utils;

public class Cliente {
    
    //Toda vez que instanciar um objeto Cliente, esse contador será incrementado.
    private static int contador = 101;


    private int codigo;
    private String nome;
    private String email;
    private String cpf;
    private Date dataNascimento;
    private Date dataCadastro;

    


    public Cliente(String nome, String email, String cpf, Date dataNascimento) {
        this.codigo = Cliente.contador;
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.dataCadastro = new Date();
        Cliente.contador += 1;
    }

    public int getCodigo(){
        return this.codigo;
    }

    public Date getDataCadastr(){
        return this.dataCadastro;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    public Date getDataNascimento() {
        return dataNascimento;
    }
    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    @Override
    public String toString() {
        return "Codigo do cliente: " + this.getCodigo() +
                "\nNome: " + this.getNome() +
                "\nEmail: " + this.getEmail() + 
                "\nCPF: " + this.getCpf() +
                "\nData de nascimento: " + Utils.dateParaString(this.getDataNascimento()) + 
                "\nData de cadastro: " + Utils.dateParaString(this.getDataCadastr());
    }



}
