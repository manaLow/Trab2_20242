import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Locale;
import java.util.Scanner;


public class Entrada {
    /**
     * Classe com as rotinas de entrada e saída do projeto
     * @author Hilario Seibel Junior e <seu nome aqui>
     */

    public Scanner input;

    /**
     * Construtor da classe InputOutput
     * Se houver um arquivo input.txt, define que o Scanner vai ler deste arquivo.
     * Se o arquivo não existir, define que o Scanner vai ler da entrada padrão (teclado)
     */
    public Entrada() {
        try {
            // Se houver um arquivo input.txt na pasta corrente, o Scanner vai ler dele.
            this.input = new Scanner(new FileInputStream("input.txt")).useLocale(Locale.US);
            // NAO ALTERE A LOCALICAZÃO DO ARQUIVO!!
        } catch (FileNotFoundException e) {
            // Caso contrário, vai ler do teclado.
            this.input = new Scanner(System.in).useLocale(Locale.US);
        }
    }

    /**
     * Faz a leitura de uma linha inteira
     * Ignora linhas começando com #, que vão indicar comentários no arquivo de entrada:
     * @param msg: Mensagem que será exibida ao usuário
     * @return Uma String contendo a linha que foi lida
     */
    private String lerLinha(String msg) {
        // Imprime uma mensagem ao usuário, lê uma e retorna esta linha
        System.out.print(msg);
        String linha = this.input.nextLine();

        // Ignora linhas começando com #, que vão indicar comentários no arquivo de entrada:
        while (linha.charAt(0) == '#') linha = this.input.nextLine();
        return linha;
    }

    /**
     * Faz a leitura de um número inteiro
     * @param msg: Mensagem que será exibida ao usuário
     * @return O número digitado pelo usuário convertido para int
     */
    private int lerInteiro(String msg) {
        // Imprime uma mensagem ao usuário, lê uma linha contendo um inteiro e retorna este inteiro
        String linha = this.lerLinha(msg);
        return Integer.parseInt(linha);
    }

    /**
     * Faz a leitura de um ponto flutuante
     * @param msg: Mensagem que será exibida ao usuário
     * @return O número digitado pelo usuário convertido para double
     */
    private double lerDouble(String msg) {
        // Imprime uma mensagem ao usuário, lê uma linha contendo um ponto flutuante e retorna este número
        String linha = this.lerLinha(msg);
        return Double.parseDouble(linha);
    }

    /**********************/
    /** MENUS DO SISTEMA **/
    /**********************/

    /**
     * Exibe o menu principal até que o usuário opte por sair do programa.
     * @param s: Objeto a classe Sistema.
     */
    public void menu(Sistema s) {
        if (s.sistemaVazio()) {
            System.out.println("** Inicializando o sistema **");
            this.cadAdmin(s);
        }

        String msg = "\n*********************\n" +
                "Escolha uma opção:\n" +
                "1) Login.\n" +
                "0) Sair.\n";

        int op = this.lerInteiro(msg);

        while (op != 0) {
            if (op == 1) login(s);
            else System.out.println("Opção inválida. Tente novamente: ");

            op = this.lerInteiro(msg);
        }

    }

    /**
     * Exibe o menu do administrador até que o usuário deslogue.
     * @param a: Objeto a classe Admin.
     * @param s: Objeto a classe Sistema.
     */
    public void menu(Admin a, Sistema s) {
        String msg = "\n*********************\n" +
                "Escolha uma opção:\n" +
                "1) Cadastrar novo administrador.\n" +
                "2) Cadastrar aluno.\n" +
                "3) Cadastrar produto.\n" +
                "4) Cadastrar sala.\n" +
                "0) Logout.\n";

        int op = this.lerInteiro(msg);

        while (op != 0) {
            if (op == 1) cadAdmin(s);
            if (op == 2) {cadAluno(s);}
            if (op == 3) {cadProduto(s);}
            if (op == 4) {cadSala(s);}
            if (op < 0 || op > 4) System.out.println("Opção inválida. Tente novamente: ");

            op = this.lerInteiro(msg);
        }
    }

    /**
     * Exibe o menu do aluno até que o usuário deslogue.
     * @param a: Objeto a classe Aluno.
     * @param s: Objeto a classe Sistema.
     */
    public void menu(Aluno a, Sistema s) {
        String msg = "\n*********************\n" +
                "Escolha uma opção:\n" +
                "1) Fazer pedido.\n" +
                "2) Fazer entrega.\n" +
                "3) Meus pedidos.\n" +
                "4) Inserir crédito.\n" +
                "0) Logout.\n";

        int op = this.lerInteiro(msg);

        while (op != 0) {
            if (op == 1) {fazerPedido(a, s);}
            if (op == 2) {entregarPedido(a, s);}
            if (op == 3) {listarPedidos(a, s);}
            if (op == 4) {inserirCredito(a, s);}
            if (op < 0 || op > 4) System.out.println("Opção inválida. Tente novamente: ");

            op = this.lerInteiro(msg);
        }
    }

    public void login(Sistema s) {
        System.out.println("\nBem vindo! Digite seus dados de login:");
        String cpf = this.lerLinha("CPF:");
        String senha = this.lerLinha("Senha:");

        Admin adm = s.getAdmin(cpf);
        if (adm != null) {
            if (adm.validarAcesso(senha)) {
                this.menu(adm, s);
            }
            else System.out.println("Senha inválida.");
        }
        else {
            Aluno a = s.getAluno(cpf);
            if (a != null) {
                if (a.validarAcesso(senha)) {
                    this.menu(a, s);
                }
                else System.out.println("Senha inválida");
            }
            else {
                System.out.println("Usuário inexistente");
            }
        }
    }

    /***************/
    /** CADASTROS **/
    /***************/

    /**
     * Lê os dados de um novo administrador e cadastra-a no sistema.
     * @param s: Um objeto da classe Sistema
     */
    public void cadAdmin(Sistema s) {
        System.out.println("\n** Cadastrando um novo administrador **\n");
        String cpf = this.lerLinha("Digite o cpf: ");

        while (s.getAdmin(cpf) != null) {
            cpf = this.lerLinha("Usuário já existente. Escolha outro cpf: ");
        }

        String nome = this.lerLinha("Digite o nome: ");
        String senha = this.lerLinha("Digite a senha: ");
        String email = this.lerLinha("Digite o email: ");

        Admin a = new Admin(cpf, nome, senha, email);
        s.addAdmin(a);

        System.out.println("Usuário " + a.toString() +" criado com sucesso.");
    }


    public void cadAluno (Sistema s) {
        System.out.println("\n** Cadastrando um novo aluno **\n");
        String cpf = this.lerLinha("Digite o CPF: ");

        while (s.getAluno(cpf) != null) {
            cpf = this.lerLinha("Usuário já existente. Escolha outro CPF: ");
        }

        String nome = this.lerLinha("Digite o nome: ");
        String senha = this.lerLinha("Digite a senha: ");
        Aluno al = new Aluno(cpf, nome, senha);
        s.addAluno(al);

        System.out.println("Usuário"+ al.toString() +"criado com sucesso.");
    }


    public void cadProduto (Sistema s){
        System.out.println("\n** Cadastrando um novo produto **\n");
        String codigo = s.gerarCodigoProduto(); //CRIAR MET NO SISTEMA.

        while (s.getProduto(codigo) != null) {
            codigo = s.gerarCodigoProduto();
        }

        String nome = this.lerLinha("Digite o nome do produto: \n"); //Verificar se existe o mesmo nome em outro produto?
        int qtd = this.lerInteiro("Digite a quantidade desse produto no estoque: \n");
        double valor = this.lerDouble("Digite o valor do produto: \n");

        Produto prod = new Produto(codigo, nome, qtd, valor); //CONSTRUTOR CRIADO
        s.addProd(prod); //CRIAR MET NO SISTEMA.

        System.out.println("Produto " + prod.toString() + " criado com sucesso");
    }


    public void cadSala (Sistema s){
        System.out.println("\n** Cadastrando uma nova sala **\n");

        String bloco = this.lerLinha("Digite o bloco da sala: ");
        String sala = this.lerLinha("Digite o número da sala: ");
        String andar = this.lerLinha("Digite o andar da sala: ");

        Sala cad_sala = new Sala(bloco, sala, andar);

        while (s.salas.contains(cad_sala)) {
            System.out.println("Sala já cadastrada. Insira dados diferentes.");
            bloco = this.lerLinha("Digite o bloco da sala: ");
            sala = this.lerLinha("Digite o número da sala: ");
            andar = this.lerLinha("Digite o andar da sala: ");

            cad_sala = new Sala(bloco, sala, andar);
        }

        Sala sal = new Sala(bloco, sala, andar);
        s.addSala(sal);

        System.out.println("Sala " + sal + " criada com sucesso");
    }

    public void fazerPedido (Aluno a, Sistema s){
        System.out.println("\n** Cadastrando um novo pedido **\n");
        String cod = s.gerarCodigoPedido();
        Pedido p = new Pedido(cod, a);

        s.listarSalas();
        Sala sal = lerSala(s);
        p.setS(sal);

        String msg = "Escolha uma opção:\n" +
                "1) Inserir produto no carrinho." +
                "2) Finalizar pedido.";
        int op = lerInteiro(msg);

        while (op == 1) {
            s.listarProdutos(); // Mostra os produtos disponíveis
            Item item = this.lerItem(s);
            if(p.valorTotal() <= a.getSaldo()){
                p.adicionarItem(item);
                System.out.println("Produto adicionado ao carrinho: " + item.toString());
            }
            else{
                System.out.println("Você não possui saldo o suficiente. Tente novamente.");
            }

            op = lerInteiro(msg); // Reexibe o menu
        }

        if (op == 2) {
            s.addPedido(p);
            p.confirmar();
            System.out.println("Pedido finalizado.");

        } else {
            System.out.println("Opção inválida.");
        }
    }

    public void entregarPedido(Aluno a, Sistema s){

        //Pedidos disponíveis
        Pedido [] pedidosDisponiveis = s.filtrarPedidos(true);

        //Verificar se há pedidos disponíveis
        if(pedidosDisponiveis.length == 0){
            System.out.println("Não há pedidos disponíveis para entregar.");
            return;
        }

        //Exibir pedidos disponíveis
        System.out.println("Pedidos disponíveis para entrega:");
        for(Pedido pedido : pedidosDisponiveis){
            System.out.println(pedido.toString());
        }

        //Solicitar o código do pedido
        String codigoEscolhido = lerLinha("Digite o código do pedido: ");

        //Verificar e atualizar o pedido solicitado
        for(Pedido pedido : pedidosDisponiveis){
            if(pedido.getCod().equals(codigoEscolhido)){
                pedido.atribuirEntregador(a);
                pedido.marcarComoEntregue();
                System.out.println("Pedido " + codigoEscolhido + " entregue.");
                //Atualizar o saldo do entregador

                return;
            }
        }


        System.out.println("Código do pedido indisponível.");
    }

    public void inserirCredito (Aluno a, Sistema s){
        double valor = this.lerInteiro("Digite o valor de crédito: ");
        a.inserirSaldo(valor);
    }

    public void listarPedidos(Aluno a, Sistema s){
        Pedido [] pedidosDisponiveis = s.filtrarPedidos(a);

        //Verificar se há pedidos disponíveis
        if(pedidosDisponiveis.length == 0){
            System.out.println("Não há pedidos relacionados a este aluno.");
            return;
        }

        //Exibir pedidos disponíveis
        System.out.println("Pedidos:");
        for(Pedido pedido : pedidosDisponiveis){
            System.out.println(pedido.toString());
        }
    }

    private Produto buscarProdutoPorCodigo(Sistema s, String cod_prod) {
        for (Produto prod : s.prods) {
            if (prod.getCodigo().equals(cod_prod)) {
                return prod; // Retorna o produto se encontrar
            }
        }
        System.out.println("Produto não encontrado.");
        return null; // Retorna null se não encontrar
    }

    // Solicitando uma quantidade válida
    private int solicitarQuantidade(Produto prod) {
        int qtd;
        while (true) {
            qtd = lerInteiro("Digite a quantidade de "+ prod.toString() +" no pedido: ");
            if (qtd > prod.getQtd()) {
                System.out.println("Quantidade não disponível no estoque. Possuímos " + prod.getQtd() + ". Tente novamente.");
            }
            else {
                return qtd; // Retorna a quantidade válida
            }
        }

    }

    private Sala lerSala (Sistema s){
        String sala = this.lerLinha("Digite a sala: ");
        for (Sala sal : s.salas) {
            if (sal.toString().equals(sala)) {
                return sal;
            }
        }
        return null;
    }

    private Item lerItem (Sistema s){
        String cod_prod = this.lerLinha("Digite o código do produto: ");
        Produto prod = buscarProdutoPorCodigo(s, cod_prod);
        if(prod != null){
            int qtd = solicitarQuantidade(prod);
            return new Item(qtd, prod);
        }
        else{
            System.out.println("Produto não encontrado.");
            return null;
        }
    }


}



