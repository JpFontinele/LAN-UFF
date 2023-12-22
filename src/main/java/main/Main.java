package main;

//Importações
import Interface.Menu;
import cadastros.Cadastro;
import cadastros.Computador;
import caracteristicas.Aluno;
import caracteristicas.Funcionario;
import caracteristicas.Professor;
import java.io.IOException;
import java.util.Scanner;

public class Main  {
    
    // Teclado statico, que podera ser utilizado em todas as funções
    static Scanner input = new Scanner(System.in); 
    
    // Menu que podera ser utilizado em todas as funções
    static Menu menu = new Menu() ;
    
    //Inicializa o cadastro para todas as funções
    static Cadastro cad = new Cadastro();
    
    
    public static void main(String[] args) throws IOException {
        
        //Nessa parte ira carregar lista com os computadores ja cadastrados
        cad.carregaComputadores();
        
        //Nessa parte ira carregar a lista de espera e tentar colocar esses usuarios em computadores
        cad.carregaListaDeEspera();
        
               
        //Booleano de saida do sistema
        boolean sairPrograma = false;
        do{
            //Booleano de saida do menu
            boolean sairMenu = false;
            
            //Pergunta ao usuario se deseja entrar como adm ou usuario
            int opcaoDeEntrada = menu.opcao(); 
            
            while(!sairMenu){
                // Chama a interface de escolha, levando a opção de entrada
                int opcaoDoMenu = menu.interfaceDoSistema(opcaoDeEntrada); 

                if (opcaoDeEntrada == 1){ /* Trabalha com o menu de adm*/
                    
                    switch(opcaoDoMenu){
                        
                        case 1: //Chama a função de cadastro de computadores
                            CadastroDeComputadores();
                            break;

                        case 2: //Chama a função de remoção de computadores
                            RemocaoDeComputadores();
                            break;
                            
                        case 3: //Chama a função de consulta de ocupantes
                            ConsultaOcupantes();
                            break;
                        case 4:
                            mostraHistorico();
                            break;
                        case 5:
                            ConsultaListaAdm();
                            break;
                        case 6: //Volta para o menu principal
                            sairMenu = true;
                            menu.setLiberado(false);
                            break; 

                        case 7: //Finaliza o programa e mostra os creditos finais
                            sairPrograma = true;
                            sairMenu = true;
                            
                            cad.liberarComputador();
                            cad.guardaComputadores();
                            cad.guardaListaOcupada();
                            cad.guardaListaDeEspera();
                            menu.creditos();
                            
                            break;   
                    }
                }

                else{ /* Trabalhar com o menu de aluno*/

                    switch(opcaoDoMenu){ //Chama a função de entrar na lista
                        case 1:  
                            EntraLista();
                            break;

                        case 2: //Chama a função de consultar lista de Espera
                            ConsultaLista();
                            break;
                            
                        case 3: //Volta para o menu principal
                            sairMenu = true;
                            break;

                        case 4: //Finaliza o programa e mostra os creditos finais
                            sairPrograma = true;
                            sairMenu = true;
                           
                            cad.liberarComputador();
                            cad.guardaListaDeEspera();
                            cad.guardaComputadores();
                            cad.guardaListaOcupada();
                            menu.creditos();
                            
                            break;       
                    }
                }
            }
        }while(!sairPrograma);
    
    }
    //Função para o cadastro de computadores
    private static void CadastroDeComputadores() throws IOException {
        
        Computador computador = new Computador();
        
        System.out.printf("\nDigite o codigo do computador: ");
        int codigo = menu.pegaOpcaoInteiro(); //Utilizado para pegar um numero inteiro valido
        computador.setCodigo(codigo);
        System.out.printf("\nComputador guardado na lista com sucesso\n");
        
        computador.setStatus(Computador.PcStatus.DESOCUPADO);
        cad.cadastroComputador(computador);
        cad.guardaComputadores();
    }
    
    private static void RemocaoDeComputadores() {
        
        System.out.printf("\nComputadores livres:\n");
        cad.mostraComputadores();
        
        System.out.printf("\nDigite o codigo do computador que deseja remover: ");
        int codigo = menu.pegaOpcaoInteiro();
 
        cad.removerComputador(codigo);   
    }
    
    private static void ConsultaOcupantes(){
        cad.mostraListaOcupada(); 
    }
    
    private static void ConsultaListaAdm(){
        int escolha = 0;
        do{
            System.out.printf("\nDeseja ver [0 = Alunos / 1 = Professores / 2 = Funcionarios / 3 = Todos]: ");
            escolha = menu.pegaOpcaoInteiro();
            if (escolha == 0 || escolha == 1 || escolha == 2 || escolha == 3){
                break;
            }
            System.out.printf("\nDigite uma das opcoes mostradas");
            
        }while(true);
        
        System.out.printf("\nLista de espera:\n");
        cad.mostraListaDeEsperaAdm(escolha); 
    }
    
    private static void mostraHistorico(){
        System.out.printf("\nHistorico:");
        cad.carregaHistorico();
    }
    
    private static void EntraLista() {
        int escolha = 0 ;       
        do{
            System.out.printf("\n[Aluno = 0, Professor = 1, Funcionario = 2]: ");
            escolha = menu.pegaOpcaoInteiro();
            if (escolha == 0 || escolha == 1 || escolha == 2){
                break;
            }
            System.out.printf("\nDigite uma das opcoes mostradas");
            
        }while(true);
        
        System.out.printf("\nInsira seu nome: ");
        String nome = input.nextLine();

        System.out.printf("\nInsira seu e-mail: ");
        String email = input.nextLine();

        System.out.printf("\nInforme a sua idade: ");
        int idade = menu.pegaOpcaoInteiro();
        
        switch(escolha){
            
            //Aluno
            case(0):
                Aluno aluno = new Aluno();
                aluno.Pessoa(nome, email, idade);
                System.out.printf("\nMatricula: ");
                aluno.setMatricula(input.nextLine());
             
                System.out.printf("\nCurso: ");
                aluno.setCurso(input.nextLine());
                cad.ocuparComputador(aluno);
     
                break;
                
            //Professor
            case(1):
                Professor professor = new Professor();
                professor.Pessoa(nome, email, idade);
                System.out.printf("\nCurso Ministrado: ");
                professor.setCursoMinistrado(input.nextLine());
                cad.ocuparComputador(professor);
                
                break;
            
            //Funcionario
            case(2):
                Funcionario funcionario = new Funcionario();
                funcionario.Pessoa(nome, email, idade);
                System.out.printf("\nArea de atuacao: ");
                funcionario.setAreaDeAtuacao(input.nextLine());
                cad.ocuparComputador(funcionario);
                
                break;
        }
 
    }
    private static void ConsultaLista() {
       
        System.out.printf("\nLista de espera:\n");
        cad.mostraListaDeEsperaUsuario(); 
    }
    
}
