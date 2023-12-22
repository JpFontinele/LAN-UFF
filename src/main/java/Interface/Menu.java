
package Interface;

import java.util.Scanner;

public class Menu {
    
    // Teclado statico, que podera ser utilizada em todas as funções
    static Scanner input = new Scanner(System.in); 
    
    //Liberação do sistema para o ADM
    private boolean liberado = false;
    
    //Senha de acesso
    private static int Senha = 1234;
    
    //Linha para uma questão de design
    public void linha(){ 
        System.out.printf("\n-=-==-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=-=-=-=-=-=-=-=-==-=-=\n");
    }
    
    //Creditos finais ao finalizar o programa
    public void creditos(){
        
        linha();
        System.out.printf("\n\t\tObrigado por utilizar nosso programa\t\t\t\n");
        System.out.printf("\n\t\tCaio Silva da Conceicao\t\t\t");
        System.out.printf("\n\t\tJoao Pedro da Silveira Fontinele\t\t\t");
        System.out.printf("\n\t\tValter dos Santos Teixeira Neto\t\t\t");
        linha();        
    }
    
    //Utilizado para pegar as opções numericas dos usuarios, mesmo se tal nao digitar um número válido
    public int pegaOpcaoInteiro(){
        
        int op;
        
        do{
            try{
                // Pega a opção do usuario 
                op = input.nextInt(); 
                return op;
            }
            catch(Exception ex){   
                
                System.out.printf("\nPor favor digite uma opcao valida!!!");  
            }
            
            input.nextLine(); //Corrige o erro da linha
            System.out.printf("\nOp: ");

        }while(true);         
    }
    
    //Utilizado para o ADM colocar sua senha e entrar no sistema
    public void seguranca(){
        
        do{
            System.out.printf("\nDigite a senha: ");
            int senha = pegaOpcaoInteiro();
            
            if (senha == Senha){ break ; }
                
            System.out.printf("\nSenha incorreta, por favor digite novamente!!!");
                
        }while(true);
    }
    
    //Utilizado para pegar a opção de entrada do usuário 
    public int opcao(){

        linha();
        System.out.printf("\nEntrar como ADM ou Usuario [ADM = 1 / Usuario = 2]");
         
        do{
            System.out.printf("\nDigite a opcao: ");
            int opcao = pegaOpcaoInteiro();
            
            if (opcao == 1 || opcao == 2){ return opcao; }
            
            System.out.printf("\nPor favor digite uma opcao valida [ADM = 1 / Usuario = 2]");
            
        }while(true);    
    }
    
    //Utilizado para a interface do sistema
    public int interfaceDoSistema(int op){
        
        linha();
        System.out.printf("\t\t\tMENU\t\t\t");
        linha();
        
        int op2;
       
        if (op == 1){ // Se o usuario for o administrador
            
            
            //Chama a função para a liberação do sistema
            if (liberado == false) seguranca();
            setLiberado(true);
            
            linha();
            System.out.printf("\t\t\tAcesso Liberado\t\t\t");
            linha();
            
            System.out.printf("\n\t[1]Cadastro de computadores\n");
            System.out.printf("\n\t[2]Remocao de computadores\n");
            System.out.printf("\n\t[3]Consultar alunos\n");
            System.out.printf("\n\t[4]Ver historico de computadores utilizados\n");
            System.out.printf("\n\t[5]Consultar lista de espera\n");
            System.out.printf("\n\t[6]Voltar para o menu principal\n");
            System.out.printf("\n\t[7]Sair\n");
     
        }
        // Se o usuario nao for administrador
        else if (op == 2) {
            
            System.out.printf("\n\t[1]Reservar computador\n");
            System.out.printf("\n\t[2]Consultar lista de espera\n");
            System.out.printf("\n\t[3]Voltar para o menu principal\n");
            System.out.printf("\n\t[4]Sair\n");
        }
        
        linha();
        
        //Utilizado para pegar a opção do menu
        do{
            System.out.printf("\nDigite a opcao: ");
            
            op2 = pegaOpcaoInteiro(); //Chama a função de pegar a opcao
            
            if (op == 1){ //Escolha do ADM
                if (op2 == 1 || op2 == 2 || op2 == 3 || op2 == 4 || op2 == 5 || op2 == 6 || op2 == 7) { return op2; }
            }
            //Escolha do usuario
            else if (op2 == 1 || op2 == 2 || op2 == 3 || op2 == 4) { return op2; }
               
            System.out.printf("\nPor favor digite uma das opcoes mostradas");
                  
        }while(true);
    }

    public void setLiberado(boolean liberado) {
        this.liberado = liberado;
    }
    
}
