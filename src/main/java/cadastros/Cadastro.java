
package cadastros;

import caracteristicas.Aluno;
import caracteristicas.Funcionario;
import caracteristicas.Professor;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;
import java.util.Map;


public class Cadastro implements SalaDeComputadores{
    
    List<Pessoa> listaDeEspera = new ArrayList<>();
    List<Computador> listaDeComputadores = new ArrayList<>();
    Map<Pessoa,Computador> listaOcupada = new HashMap<>();
    
    //Guarda os computadores na lista
    public void cadastroComputador(Computador cad){
        listaDeComputadores.add(cad);   
    }
    
    //Mostra os computadores que estao sendo utilizados pelas as pessoas
    public void mostraListaOcupada(){
        if (listaOcupada.isEmpty()){
            System.out.printf("\nNao ha pessoas utilizando computadores\n");
        }
        else{
            for (Map.Entry<Pessoa, Computador> cad : listaOcupada.entrySet()) {
                System.out.printf("\nUsuario: %s", cad.getKey().getNome());
                System.out.printf("\nE-mail: %s", cad.getKey().getEmail());
                System.out.printf("\nCodigo do computador: %d\n", cad.getValue().getCodigo());
            }
        }
    }
    
    //Retorna a lista de espera para o usuario
    public void mostraListaDeEsperaUsuario(){
        if (listaDeEspera.isEmpty()){
            System.out.printf("\nNao ha pessoas na fila de espera\n");
        }
        else{
            for (Pessoa p : listaDeEspera){

                System.out.printf("\nNome: %s\n", ((Pessoa) p).getNome() );      
            }
        }
    }
    
    //Retorna a lista de espera para o adm
    public void mostraListaDeEsperaAdm(int pessoa){
        if (listaDeEspera.isEmpty()){
            System.out.printf("\nNao ha pessoas na fila de espera\n");
        }
        else{
            for (Pessoa p : listaDeEspera){

                switch (pessoa) {
                    case 0:
                        if (p instanceof Aluno){
                            System.out.printf("\nNome: %s", ((Aluno) p).getNome());
                            System.out.printf("\nMatricula: %s", ((Aluno) p).getMatricula());
                            System.out.printf("\nCurso: %s\n", ((Aluno) p).getCurso());
                        }   
                        break;

                    case 1:
                        if (p instanceof Professor){
                            System.out.printf("\nNome: %s", ((Professor) p).getNome() );
                            System.out.printf("\nMateria: %s\n", ((Professor) p).getCursoMinistrado());   
                        }   
                        break;

                    case 2:
                        if (p instanceof Funcionario){
                            System.out.printf("\nNome: %s", ((Funcionario) p).getNome() );
                            System.out.printf("\nArea de atuacao: %s\n", ((Funcionario) p).getAreaDeAtuacao());
                        }   
                        break;

                    default:
                        if (p instanceof Pessoa){
                            System.out.printf("\nNome: %s\n", ((Pessoa) p).getNome() );
                        }   
                        break;
                }   
            }
        }
    }
    
    //Guarda a lista de espera em um arquivo
    public void guardaListaDeEspera() {
        try{
            FileOutputStream fluxoOut = new FileOutputStream("espera.ser");
            ObjectOutputStream fOut = new ObjectOutputStream(fluxoOut);
            fOut.writeObject(listaDeEspera);
            fOut.close();
            System.out.printf("\nLista de espera gravada com sucesso\n");
        }
        catch(FileNotFoundException ex){
            System.out.println(ex.getMessage());   
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }   
           
    }
    
    //Guarda todos os computadores cadastrados em um arquivo
    public void guardaComputadores() {
        try{
            FileOutputStream fluxoOut = new FileOutputStream("computadores.ser");
            ObjectOutputStream fOut = new ObjectOutputStream(fluxoOut);
            fOut.writeObject(listaDeComputadores);
            fOut.close();
            System.out.printf("\nLista de computadores gravada com sucesso\n");
        }
        catch(FileNotFoundException ex){
            System.out.println(ex.getMessage());   
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }   
           
    }
    
    //Guarda a lista ocupada em um arquivo
    public void guardaListaOcupada(){
        try{
            FileOutputStream fluxoOut = new FileOutputStream("historico.ser");
            ObjectOutputStream fOut = new ObjectOutputStream(fluxoOut);
            fOut.writeObject(listaOcupada);
            fOut.close();
            System.out.printf("\nHistorico gravado com sucesso\n");
        }
        catch(FileNotFoundException ex){
            System.out.println(ex.getMessage());   
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }   
        
    }
    
    //Utilizado no final do programa para liberar todos os computadores
    public void liberarComputador(){
        if (listaDeComputadores.isEmpty()){
            System.out.printf("\nNao ha computadores no banco de dados\n");
        }
        
        else{
            System.out.printf("\nComputadores desocupados\n");
            for (Computador pc : listaDeComputadores){

                if (pc.getStatus().equals("OCUPADO") ){
                    pc.setStatus(Computador.PcStatus.DESOCUPADO);     
                }
            }  
        }
    }
    
    //Utilizado para colocar um computador Desocupado na lista
    public void ocuparComputador(Pessoa pessoa){
        
        boolean acrescentado = false;
        
        if (listaDeComputadores.isEmpty()){
            System.out.printf("\nNao ha computadores no banco de dados\n");
        }
        else{
            
            for (Computador pc : listaDeComputadores){

                if (pc.getStatus().equals("DESOCUPADO") ){
                    listaOcupada.put(pessoa, pc); //Acrescenta o usuario a um computador 
                    System.out.printf("\nO usuario foi colocado em um computador!!!");
                    pc.setStatus(Computador.PcStatus.OCUPADO);
                    acrescentado = true;
                    break;
                }
            }  
            
        }
        if (acrescentado == false){
                //Coloca as pessoas na fila de espera
                System.out.printf("\nInfelizmente nao ha computadores disponiveis, voce sera colocado na fila de espera\n");
                listaDeEspera.add(pessoa);      
            }
    }
    
    //utilizado para mostrar os computadores desocupados
    public void mostraComputadores(){
        
        boolean disponivel = false;
        if (listaDeComputadores.isEmpty()){
            System.out.printf("\nNao ha computadores no banco de dados\n");
        }
        else{
            for (Computador pc : listaDeComputadores){
                if (pc.getStatus().equals("DESOCUPADO") ){
                    System.out.printf("Computador: %d\n", pc.getCodigo());
                    disponivel = true;
                }
            }
        }
        if (disponivel == false){
            System.out.printf("\nNao ha computadores livres\n");
        }
    } 
    
    //Remove o computador na lista
    public void removerComputador(int codigo){
        
        if (listaDeComputadores.isEmpty()){
            System.out.printf("\nNao ha computadores no banco de dados\n");
        }
        else{
            boolean encontrado = false;
            for (Computador pc : listaDeComputadores){
                if (pc.getStatus().equals("DESOCUPADO")){
                    //Caso o codigo digitado pelo usuario for igual ao codigo da maquina, remove ele da lista
                    if (pc.getCodigo() == codigo){
                        listaDeComputadores.remove(pc);
                        System.out.printf("\nComputador removido com sucesso\n");
                        encontrado = true;
                        break;
                    }  
                }
            }

            if (encontrado == false){
                System.out.printf("\nO computador digitado nao foi encontrado no  banco de dados\n");
            }
        }
    }
    //Carrega a lista de computadores gravadas anteriormente
    @Override
    public void carregaComputadores() {
        try{
            FileInputStream fluxoIn = new FileInputStream("computadores.ser");
            ObjectInputStream fIn = new ObjectInputStream(fluxoIn);
            listaDeComputadores = (List<Computador>) fIn.readObject();
            fIn.close();
            System.out.printf("\nLista De computadores carregada");
            if (listaDeComputadores.isEmpty()){
                System.out.printf("\nLista de computadores vazia");
            }
        }
        catch(FileNotFoundException ex){
            System.out.println(ex.getMessage());  
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());  
        }
        catch(ClassNotFoundException ex){
            System.out.println(ex.getMessage());
        }
    }
    
    @Override
    public void carregaListaDeEspera() {
         try{
            FileInputStream fluxoIn = new FileInputStream("espera.ser");
            ObjectInputStream fIn = new ObjectInputStream(fluxoIn);
            List <Pessoa> listaDeEsperaG = (List<Pessoa>) fIn.readObject();
            fIn.close();
            System.out.printf("\nLista De espera anterior carregada");
            if (listaDeEsperaG.isEmpty()){
                System.out.printf("\nNao ha lista de espera");
            }
            else{
                for (Pessoa p : listaDeEsperaG){
                    ocuparComputador(p);
                 }   
            }
        }
        catch(FileNotFoundException ex){
            System.out.println(ex.getMessage());  
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());  
        }
        catch(ClassNotFoundException ex){
            System.out.println(ex.getMessage());
        }
    }
     //Carrega o historico de usuarios que ocuparam computadores 
    @Override
    public void carregaHistorico() {
         try{
            FileInputStream fluxoIn = new FileInputStream("historico.ser");
            ObjectInputStream fIn = new ObjectInputStream(fluxoIn);
            Map<Pessoa,Computador> listaOcupadaG = (Map) fIn.readObject();
            fIn.close();
            if (listaOcupadaG.isEmpty()){
                System.out.printf("\nNao ha historicos disponiveis\n");
            }
            else{
                for (Map.Entry<Pessoa, Computador> cad : listaOcupadaG.entrySet()) {
                    System.out.printf("\nUsuario %s", cad.getKey().getNome());
                    System.out.printf("\nE-mail %s", cad.getKey().getEmail());
                    System.out.printf("\nCodigo do computador %d\n", cad.getValue().getCodigo());
                }
            }
        }
        catch(FileNotFoundException ex){
            System.out.println(ex.getMessage());  
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());  
        }
        catch(ClassNotFoundException ex){
            System.out.println(ex.getMessage());
        }
    }

   
}
