
package caracteristicas;


import cadastros.Pessoa;

public class Aluno extends Pessoa{
    
    private String matricula;
    private String curso;

    public String getMatricula() {
        return matricula;
    }

    public String getCurso() {
        return curso;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }
    
    @Override
    public void Pessoa(String nome, String email, int idade){
      super.Pessoa(nome, email, idade);
    }
    
    
}
