package upkeepxpteam.usuario.usuarioactivity;

/**
 * Created by Cleiton on 25/01/2018.
 * classe que valida o cadastro do usuário
 */

public class ValidarCadastroUsuario {

    public Boolean validarNome(String nome){

        return nome.matches("^(?![ ])(?!.*[ ]{2})((?:e|da|do|das|dos|de|d'|D'|la|las|el|los)" +
                "\\s*?|(?:[A-Z][^\\s]*\\s*?)(?!.*[ ]$))+$");
    }
    public Boolean validarEmail(String email){

        return email.matches("^[A-Za-z0-9\\._-]+@[A-Za-z]+\\.[A-Za-z]$");
    }
    public Boolean validarSobrenome(String sobreNome){

        return (sobreNome.matches("^(?![ ])(?!.*[ ]{2})((?:e|da|do|das|dos|de|d'|D'|la|las|el|los)" +
                "\\s*?|(?:[A-Z][^\\s]*\\s*?)(?!.*[ ]$))+$"));
    }
    public Boolean validarNascimento(String Nascimento){

        return Nascimento.matches("^((0[1-9]|[12]\\d)\\/(0[1-9]|1[0-2])|30\\/(0[13-9]|1[0-2])|31\\/(0[13578]|1[02]))\\/\\d{4}$");
    }

    public Boolean validarSexo2(String sexo){

        return sexo.matches("^(M|F)$");
    }

    public Boolean validarTelefone(String telefone) {

        return telefone.matches("^[1-9]{2}\\-[2-9][0-9]{7,8}$");
    }
    public Boolean validarEspecialidade(String especialidade){

        return true;
    }
    public Boolean validarNumero(String numero){

        return numero.matches("^[0-9]{0,5}+$");
    }
    public Boolean validarUF(String uf){

        return uf.matches("^(AC|AL|AP|AM|BA|CE|DF|ES|GO|MA|MT|MS|MG|PA|PB|PR|PE|PI|RJ|RN|RS|RO|RR|SC|SP|SE|TO)+$");
    }
    public Boolean validarFuncao(String funcao){

        return funcao.matches("[A-Za-z]");
    }
    public void validarLocal(String local) {
        boolean result = true;
    }

}
