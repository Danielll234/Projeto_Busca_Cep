package one.digitalinnovation.gof;


public class Endereco {
    private String cep;
    private String logradouro;
    private String bairro;
    private String localidade; // Cidade
    private String uf;         // Estado

    // Getters
    public String getCep() { return cep; }
    public String getLogradouro() { return logradouro; }
    public String getBairro() { return bairro; }
    public String getLocalidade() { return localidade; }
    public String getUf() { return uf; }

    @Override
    public String toString() {
        return "Endereço Encontrado:\n" +
               "Rua: " + logradouro + "\n" +
               "Bairro: " + bairro + "\n" +
               "Cidade: " + localidade + " - " + uf + "\n" +
               "CEP: " + cep;
    }
}