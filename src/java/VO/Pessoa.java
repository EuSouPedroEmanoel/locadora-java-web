
package VO;

import java.util.Date;


public class Pessoa {
    private String cpf;
    private String nome;
    private String dataNascimento;
    private String tubMoney;
    private String sexo;
    private String senha;
    private String email;
    private boolean super_user;

    public boolean setSuper_user(boolean super_user) {
        return this.super_user = super_user;
    }

    public boolean getSuper_user() {
        return this.super_user;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getTubMoney() {
        return tubMoney;
    }

    public void setTubMoney(String tubMoney) {
        this.tubMoney = tubMoney;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    
    
}
