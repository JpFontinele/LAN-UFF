
package caracteristicas;


import cadastros.Pessoa;

public class Funcionario extends Pessoa {
    
    private String areaDeAtuacao;

    public String getAreaDeAtuacao() {
        return areaDeAtuacao;
    }

    public void setAreaDeAtuacao(String areaDeAtuacao) {
        this.areaDeAtuacao = areaDeAtuacao;
    }
    
    
    @Override
    public void Pessoa(String nome, String email, int idade){
        super.Pessoa(nome, email, idade);
        
    }
    
}
