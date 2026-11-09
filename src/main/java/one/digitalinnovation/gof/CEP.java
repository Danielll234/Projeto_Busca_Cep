package one.digitalinnovation.gof;

import com.google.gson.Gson;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class CEP {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("=== CONSULTOR DE CEP EM JAVA ===");
        System.out.print("Digite o CEP (apenas números): ");
        String cep = scanner.nextLine();

        // Validação básica se tem 8 dígitos
        if (cep.length() != 8) {
            System.out.println("CEP inválido! O CEP deve conter exatamente 8 dígitos.");
            scanner.close();
            return;
        }

        String url = "https://viacep.com.br/ws/" + cep + "/json/";

        try {
            // Configurando o cliente HTTP para fazer a requisição
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .build();

            // Enviando a requisição e aguardando a resposta
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                // Convertendo o JSON recebido para o nosso objeto Endereco usando o Gson
                Gson gson = new Gson();
                Endereco endereco = gson.fromJson(response.body(), Endereco.class);

                if (endereco.getCep() != null) {
                    System.out.println("\n--------------------------------");
                    System.out.println(endereco);
                    System.out.println("--------------------------------");
                } else {
                    System.out.println("\n[Aviso] O CEP informado não foi encontrado na base de dados.");
                }
            } else {
                System.out.println("\n[Erro] Erro ao conectar com o serviço. Status: " + response.statusCode());
            }

        } catch (Exception e) {
            System.out.println("\n[Erro crítico] Ocorreu um erro ao consultar o CEP: " + e.getMessage());
        }
        
        scanner.close();
    }

}
