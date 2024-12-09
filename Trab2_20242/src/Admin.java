public class Admin extends Usuario{
    private String email;

    //Construtor do admin.
    public Admin(String cpf, String nome, String senha, String email) {
        super(cpf, nome, senha);
        this.email = email;
    }

    //Exibição do admin
    public String toString() {
        return super.toString() + " (ADMIN)";
    }

    //Validar acesso do admin por meio do password
    public boolean validarAcesso(String pwd){
        if(!pwd.equals(getSenha())){
            System.out.println("Acesso negado.");
            return false;
        }
        else{
            System.out.println("Acesso autorizado.");
            return true;
        }
    }
}

