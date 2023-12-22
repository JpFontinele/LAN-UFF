
package cadastros;

import java.io.Serializable;


public class Computador extends Cadastro implements Serializable{

    private Integer codigo;
    private PcStatus status;
    
    //Enumeração para saber se o Pc está ocupado ou desocupado
    public enum PcStatus{
        DESOCUPADO,
        OCUPADO;
    }
    
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setStatus(PcStatus status) {
        this.status = status;
    }
    
    public Integer getCodigo() {
        return codigo;
    }

    public String getStatus() {
        return  status.name();
    }
 
}
