package model;

import java.io.*;
import java.util.ArrayList;

public class PessoaJuridicaRepo {
    private ArrayList<PessoaJuridica> lista = new ArrayList<>();

    
    public void inserir(PessoaJuridica pessoa) {
        lista.add(pessoa);
    }

    
    public void alterar(PessoaJuridica pessoa) {
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getId() == pessoa.getId()) {
                lista.set(i, pessoa);
                return;
            }
        }
        
        System.out.println("Pessoa jurídica com ID " + pessoa.getId() + " não encontrada.");
    }

    
    public void excluir(int id) {
        lista.removeIf(p -> p.getId() == id);
    }

   
    public PessoaJuridica obter(int id) {
        return lista.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
    }

    
    public ArrayList<PessoaJuridica> obterTodos() {
        return new ArrayList<>(lista);
    }

    
    public void persistir(String nomeArquivo) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nomeArquivo))) {
            oos.writeObject(lista);
        } catch (IOException e) {
            System.out.println("Erro ao persistir os dados: " + e.getMessage());
            throw e; 
        }
    }

    
    public void recuperar(String nomeArquivo) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nomeArquivo))) {
            lista = (ArrayList<PessoaJuridica>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro ao recuperar os dados: " + e.getMessage());
            throw e; 
        }
    }
}
