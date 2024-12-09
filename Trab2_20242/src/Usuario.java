public class Usuario {
    protected String cpf, nome;
    private String senha;

    //Construtor do usuario
    public Usuario(String cpf, String nome, String senha) {
        this.cpf = cpf;
        this.nome = nome;
        this.senha = senha;
    }

    //Validar acesso do usuario
    public boolean validarAcesso(String s) {
        return s.equals(this.senha);
    }

    //Exibição do usuario
    public String toString() {
        return this.nome + " - CPF: " + this.cpf;
    }

    //Retornar CPF do usuario
    public String getCPF() {
        return this.cpf;
    }

    //Alterar senha do usuario
    public boolean alterarSenha(String atual, String nova){
        //Condição para a senha existir
        if(atual == null || nova == null){
            System.out.println("A senha não pode ser nula.");
            return false;
        }

        //Senha atual incorreta
        if(!atual.equals(senha)){
            System.out.println("Senha atual incorreta.");
            return false;
        }

        //Senha atual e nova iguais
        if(atual.equals(nova)){
            System.out.println("A nova senha não pode ser igual a atual.");
            return false;
        }

        //Senha alterada
        this.senha = nova;
        System.out.println("Senha alterada com sucesso!");
        return true;
    }

    //Retornar senha
    public String getSenha(){ return this.senha; }
}

