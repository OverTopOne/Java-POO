package model;

import java.io.*;
import java.util.ArrayList;

public class PessoaFisicaRepo {
    private ArrayList<PessoaFisica> lista = new ArrayList<>();

    
    public void inserir(PessoaFisica pessoa) {
        lista.add(pessoa);
    }

    
    public void alterar(PessoaFisica pessoa) {
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getId() == pessoa.getId()) {
                lista.set(i, pessoa);
                return;
            }
        }
        System.out.println("Pessoa com ID " + pessoa.getId() + " não encontrada.");
    }

    /
    public void excluir(int id) {
        boolean removed = lista.removeIf(p -> p.getId() == id);
        if (!removed) {
            System.out.println("Pessoa com ID " + id + " não encontrada.");
        }
    }

    
    public PessoaFisica obter(int id) {
        return lista.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
    }

    
    public ArrayList<PessoaFisica> obterTodos() {
        return new ArrayList<>(lista); 
    }

    public void persistir(String nomeArquivo) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nomeArquivo))) {
            oos.writeObject(lista);
        } catch (IOException e) {
            System.out.println("Erro ao salvar dados no arquivo: " + e.getMessage());
            throw e;
        }
    }

    public void recuperar(String nomeArquivo) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nomeArquivo))) {
            lista = (ArrayList<PessoaFisica>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado: " + nomeArquivo);
            throw e;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro ao carregar dados do arquivo: " + e.getMessage());
            throw e;
        }
    }
}
