
package caracteristicas;
import cadastros.Pessoa;

public class Professor extends Pessoa {
    
    private String cursoMinistrado;

    public String getCursoMinistrado() {
        return cursoMinistrado;
    }

    public void setCursoMinistrado(String cursoMinistrado) {
        this.cursoMinistrado = cursoMinistrado;
    }
    
    @Override
    public void Pessoa(String nome, String email, int idade){
      super.Pessoa(nome, email, idade);
    }
    
  
      
}
