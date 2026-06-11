package VO;

import java.time.LocalDate;

public class PessoaFilme {

    private int id;
    private String cpf;
    private int filmeId;
    private String dataEmprestimo;
    private LocalDate dataDevolucaoPrev;
    private LocalDate dataDevolucaoReal;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public int getFilmeId() {
        return filmeId;
    }

    public void setFilmeId(int filmeId) {
        this.filmeId = filmeId;
    }

    public String getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(String dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public LocalDate getDataDevolucaoPrev() {
        return dataDevolucaoPrev;
    }

    public void setDataDevolucaoPrev(LocalDate dataDevolucaoPrev) {
        this.dataDevolucaoPrev = dataDevolucaoPrev;
    }

    public LocalDate getDataDevolucaoReal() {
        return dataDevolucaoReal;
    }

    public void setDataDevolucaoReal(LocalDate dataDevolucaoReal) {
        this.dataDevolucaoReal = dataDevolucaoReal;
    }
}