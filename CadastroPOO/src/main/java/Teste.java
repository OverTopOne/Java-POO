package model;

import java.io.IOException;
import java.util.Scanner;

public class CadastroPOO {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PessoaFisicaRepo pfRepo = new PessoaFisicaRepo();
        PessoaJuridicaRepo pjRepo = new PessoaJuridicaRepo();
        
        int opcao;
        
        do {
            System.out.println("\nMENU");
            System.out.println("1. Incluir");
            System.out.println("2. Alterar");
            System.out.println("3. Excluir");
            System.out.println("4. Exibir pelo ID");
            System.out.println("5. Exibir todos");
            System.out.println("6. Salvar dados");
            System.out.println("7. Recuperar dados");
            System.out.println("0. Sair");
            System.out.println("Escolha uma opção:");
            opcao = scanner.nextInt();
            scanner.nextLine();
            
            
            switch (opcao) {
                case 1 -> incluir(scanner, pfRepo, pjRepo);
                case 2 -> alterar(scanner, pfRepo, pjRepo);
                case 3 -> excluir(scanner, pfRepo, pjRepo);
                case 4 -> exibirPeloId(scanner, pfRepo, pjRepo);
                case 5 -> exibirTodos(scanner, pfRepo, pjRepo);
                case 6 -> salvar(scanner, pfRepo, pjRepo);
                case 7 -> recuperar(scanner, pfRepo, pjRepo);
                case 0 -> System.out.println("Encerrar o programa.");
                default -> System.out.println("Opção inválida. Tente novamente");
                
            }
                     
            
        } while (opcao !=0);
        
        scanner.close();
    }
    
    
    private static void incluir(Scanner scanner, PessoaFisicaRepo pfRepo, PessoaJuridica pjRepo) {
        System.out.print("Escolha o tipo (1 - Física, 2 - Jurídica): ");
        int tipo = scanner.nextInt();
        scanner.nextLine();
        
        if (tipo == 1) {
            System.out.print("Digite o ID:");
            int id = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Digite o nome:");
            String nome = scanner.nextLine();
            System.out.print("Digite o CPF:");
            String cpf = scanner.nextLine();
            System.out.print("Digite a idade:");
            int idade = scanner.nextInt();
            pfRepo.inserir(new PessoaFisica(id, nome, cpf, idade));               
        }   else if (tipo == 2) {
            System.out.print("Digite o ID:");
            int id = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Digite o nome:");
            String nome = scanner.nextLine();
            System.out.print("Digite o CNPJ:");
            String cnpj = scanner.nextLine();
            pjRepo.inserir(new PessoaJuridica(id, nome, cnpj));
        }   else {
                System.out.println("Tipo inválido");
        }
    
    }
    
    private static void alterar(Scanner scanner, PessoaFisicaRepo pfRepo, PessoaJuridica pjRepo) {
        System.out.print("Escolha o tipo(1 - Física, 2 -Jurídica):" );
        int tipo = scanner.nextInt();
        scanner.nextLine();
        
        System.out.print("Digite o ID para alterar:");
        int id = scanner.nextInt();
        scanner.nextLine();
        
        
        if (tipo == 1) {
            PessoaFisica pessoa = pfRepo.obter(id);
            if (pessoa != null) {
                System.out.println("Dados atuais:" + pessoa);
                System.out.print("Digite o novo nome:");
                pessoa.setNome(scanner.nextLine());
                System.out.print("Digite o novo CPF:");
                pessoa.setCpf(scanner.nextLine());
                System.out.print("Digite a nova idade:");
                pessoa.setIdade(scanner.nextInt());
                pfRepo.alterar(pessoa);
            } else {
                System.out.println("PessoalFísica não encontrada");
                
            }
            
        } else if (tipo == 2) {
            PessoaJuridica  pessoa = pjRepo.obter(id);
            if (pessoa != null) {
                System.out.println("Dados atuais:" + pessoa);
                System.out.print("Digite o novo nome:");
                pessoa.setNome(scanner.nextLine());
                System.out.print("Digite o novo CNPJ: ");
                pessoa.setCnpj(scanner.nextLine());
                pJRepo.alterar(pessoa);
            } else {
                System.out.println("Pessoa Jurídica não encontrada.");
                
            }
        } else {
            System.out.println("Tipo inválido");
            
        }
    }
    
    private static void excluir(Scanner scanner, PessoaFisicaRepo pfRepo, PessoaJuridicaRepo pjRepo) {
        System.out.print("Escolha o tipo (1- Física, 2- Jurídica):");
        int tipo = scanner.nextInt();
        scanner.nextLine();
        
        System.out.print("Digite o ID para excluir:");
        int id = scanner.nextInt();
        scanner.nextLine();
        
        
        if (tipo == 1) {
            pfRepo.excluir(id);
        } else if (tipo == 2) {
            pjRepo.excluir(id);
        } else {
            System.out.println("Tipo inválido.");
        }
    }
    
    private static void exibirPeloId(Scanner scanner, PessoaFisicaRepo pfRepo, PessoaJuridicaRepo pjRepo) {
        System.out.print("Escolha o tipo (1- Física, 2 - Jurídica): ");
        int tipo = scanner.nextInt();
        scanner.nextLine();
        
        
        System.out.print("Diite o ID para exibir:");
        int id = scanner.nextInt();
        scanner.nextLine();
        
        if ( tipo == 1) {
            PessoaFisica pessoa = pfRepo.obter(id);
            if(pessoa != null ) {
                pessoa.exibir();
                
            } else {
                System.out.println("Pessoa Física não encontrada.");
            }
            
        } else if ( tipo == 2 ) {
            PessoaJuridica pessoa = pjRepo.obter(id);
            if (pessoa != null) {
                pessoa.exibir();
                
            }  else {
                System.out.println("Pessoa Jurídica não encontrada.");
                
            } 
        } else {
            System.out.println("Tipo inválido");
            
        }
        
    }
    
    private static void exibirTodos(Scanner scanner, PessoaFisicaRepo pfRepo, PessoaJuridicaRepo pjRepo) {
        System.out.print("Escolha o tipo (1 - Física, 2 - Jurídica): ");
        int tipo = scanner.nextInt();
        scanner.nextLine();
        
        
        if (tipo == 1 ){
            pfRepo.obterTodos().forEach(PessoaFísica::exibir);
            
        } else if (tipo == 2) {
            pjRepo.obterTodos().forEach(PessoaJuridica::exibir);
        } else {
            System.out.println("Tipo inválido.");
        }
    }
    
    private static void salvar(Scanner scanner, PessoaFisicaRepo pfRepo, PessoaJuridicaRepo pjRepo) {
        System.out.print("Digite o prefixo do arquivo");
        String prefixo = scanner.nextLine();
        
        
        try {
            pfRepo.persistir(prefixo + ".fisica.bin");
            pjRepo.persistir(prefixo = ".juridica.bin");
            System.out.println("Dados salvos com sucesso.");
            
        } catch (IOException e ) {
            System.out.println("Erro ao salvar os dados:" + e.getMessage());
            
        }
    }
    
    private static void recuperar(Scanner scanner, PessoaFisicaRepo pfRepo, PessoaJuridicaRepo pjRepo) {
        System.out.print("Digite o prefixo do arquivo: ");
        String prefixo = scanner.nextLine();
        
        try {
            pfRepo.recuperar(prefixo + ".fisica.bin");
            pjRepo.recuperar(prefixo + ".juridica.bin");
            System.out.println("Dados recuperados com sucesso");
            
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro ao recuperar os dados: " + e.getMessage());
        }
    }
    
}

    
