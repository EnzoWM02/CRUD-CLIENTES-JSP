package model;

public class Cliente {

    private int id;
    private String nome;
    private String email;
    private String pais;
    private String situacao;

    public Cliente() {

    }

    public Cliente(String nome, String email, String pais, String situacao) {
        this.nome = nome;
        this.email = email;
        this.pais = pais;
        this.situacao = situacao;
    }

    public Cliente(int id, String nome, String email, String pais, String situacao) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.pais = pais;
        this.situacao = situacao;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }
}
