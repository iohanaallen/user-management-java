package app;

import service.UsuarioService;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        UsuarioService service = new UsuarioService();
        Scanner sc = new Scanner(System.in);

        int opcao;

        do {
            System.out.println("\n=== MENU ===");
            System.out.println("1 - Cadastrar");
            System.out.println("2 - Listar");
            System.out.println("3 - Atualizar");
            System.out.println("4 - Remover");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");

            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {

                case 1:
                    System.out.print("Nome: ");
                    String nome = sc.nextLine();

                    System.out.print("Email: ");
                    String email = sc.nextLine();

                    service.adicionar(nome, email);
                    System.out.println("Usuário cadastrado!");
                    break;

                case 2:
                    if (service.listar().isEmpty()) {
                        System.out.println("Nenhum usuário cadastrado.");
                    } else {
                        service.listar().forEach(System.out::println);
                    }
                    break;

                case 3:
                    System.out.print("ID: ");
                    int idAtt = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Novo nome: ");
                    String novoNome = sc.nextLine();

                    System.out.print("Novo email: ");
                    String novoEmail = sc.nextLine();

                    if (service.atualizar(idAtt, novoNome, novoEmail)) {
                        System.out.println("Atualizado com sucesso!");
                    } else {
                        System.out.println("Usuário não encontrado.");
                    }
                    break;

                case 4:
                    System.out.print("ID: ");
                    int idRem = sc.nextInt();
                    sc.nextLine();

                    if (service.remover(idRem)) {
                        System.out.println("Removido com sucesso!");
                    } else {
                        System.out.println("Usuário não encontrado.");
                    }
                    break;
            }

        } while (opcao != 0);

        sc.close();
    }
}
