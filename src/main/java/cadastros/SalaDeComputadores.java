
package cadastros;

public interface SalaDeComputadores {
  
    //Carrega a lista de computadores gravadas anteriormente
    public void carregaComputadores();
    
    //Carrega o historico de usuarios que ocuparam computadores
    public void carregaHistorico();
    
    //Carrega a lista de esperada 
    public void carregaListaDeEspera(); 
   
}
