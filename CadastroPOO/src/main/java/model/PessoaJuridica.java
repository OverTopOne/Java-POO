package model;

import java.io.Serializable;

public class PessoaJuridica extends Pessoa implements Serializable {
    private String cnpj;

   
    public PessoaJuridica() {
        super(); 
    }

    
    public PessoaJuridica(int id, String nome, String cnpj) {
        super(id, nome);  
        setCnpj(cnpj);    
    }

    
    public String getCnpj() {
        return cnpj;
    }

    
    public void setCnpj(String cnpj) {
        if (cnpj == null || cnpj.trim().isEmpty()) {
            throw new IllegalArgumentException("CNPJ não pode ser vazio.");
        }
        
        this.cnpj = cnpj;
    }

    
    @Override
    public void exibir() {
        System.out.println("ID:" + getId() + ", Nome:" + getNome() + ", CNPJ:" + cnpj);
    }
}
