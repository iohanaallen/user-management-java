package service;

import model.Usuario;
import java.util.ArrayList;
import java.util.List;
import java.io.*;

public class UsuarioService {

    private List<Usuario> usuarios = new ArrayList<>();
    private int contadorId = 1;

    private final String CAMINHO_ARQUIVO = "usuarios.txt";

    public UsuarioService() {
        carregarDoArquivo();
    }

    public void adicionar(String nome, String email) {
        usuarios.add(new Usuario(contadorId++, nome, email));
        salvarEmArquivo();
    }

    public List<Usuario> listar() {
        return usuarios;
    }

    public Usuario buscarPorId(int id) {
        for (Usuario u : usuarios) {
            if (u.getId() == id) return u;
        }
        return null;
    }

    public boolean atualizar(int id, String nome, String email) {
        Usuario u = buscarPorId(id);
        if (u != null) {
            u.setNome(nome);
            u.setEmail(email);
            salvarEmArquivo();
            return true;
        }
        return false;
    }

    public boolean remover(int id) {
        Usuario u = buscarPorId(id);
        if (u != null) {
            usuarios.remove(u);
            salvarEmArquivo();
            return true;
        }
        return false;
    }

    private void salvarEmArquivo() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CAMINHO_ARQUIVO))) {
            for (Usuario u : usuarios) {
                writer.write(u.getId() + ";" + u.getNome() + ";" + u.getEmail());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar.");
        }
    }

    private void carregarDoArquivo() {
        File arquivo = new File(CAMINHO_ARQUIVO);
        if (!arquivo.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
            String linha;

            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(";");

                int id = Integer.parseInt(dados[0]);
                String nome = dados[1];
                String email = dados[2];

                usuarios.add(new Usuario(id, nome, email));

                if (id >= contadorId) contadorId = id + 1;
            }

        } catch (IOException e) {
            System.out.println("Erro ao carregar.");
        }
    }
}
