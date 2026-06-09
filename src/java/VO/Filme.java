package VO;

public class Filme {
    private int id;
    private String nome;
    private String descricao;
    private String autor;
    private int indicacaoEtaria;
    private String capaLink; 
    private String filmeLink;    
    private int ano;          
    private String duracao;
    private boolean disponibilidade;
    private int generoId;      
    private double tubCusto;    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getIndicacaoEtaria() {
        return indicacaoEtaria;
    }

    public void setIndicacaoEtaria(int indicacaoEtaria) {
        this.indicacaoEtaria = indicacaoEtaria;
    }

    public String getCapaLink() {
        return capaLink;
    }

    public void setCapaLink(String capaLink) {
        this.capaLink = capaLink;
    }

    public String getFilmeLink() {
        return filmeLink;
    }

    public void setFilmeLink(String filmeLink) {
        this.filmeLink = filmeLink;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getDuracao() {
        return duracao;
    }

    public void setDuracao(String duracao) {
        this.duracao = duracao;
    }

    public boolean getDisponibilidade() {
        return disponibilidade;
    }

    public void setDisponibilidade(boolean disponibilidade) {
        this.disponibilidade = disponibilidade;
    }

    public int getGeneroId() {
        return generoId;
    }

    public void setGeneroId(int generoId) {
        this.generoId = generoId;
    }

    public double getTubCusto() {
        return tubCusto;
    }

    public void setTubCusto(double tubCusto) {
        this.tubCusto = tubCusto;
    }
}