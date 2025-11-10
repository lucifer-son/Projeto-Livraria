package main; // Pacote correto

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

// Importações para as classes do pacote model
import model.*;

// Importações para as classes do pacote cadastro
import cadastro.*;

// Importa a Fachada
// import main.Fachada; // Não precisa mais, pois está no mesmo pacote

// Importações para as classes do pacote excecoes
import excecoes.*; // Adicionado novamente para resolver o erro

public class Programa {

    public static void main(String[] args) {
        boolean houveErros = false;
        System.out.println("Iniciando testes...");

        // Obtém a instância única da Fachada
        Fachada fachada = Fachada.getInstance();

        System.out.println("\n--- Testes de Sucesso (Caminho Feliz) ---");

        try {
            // --- Cadastro de Livro ---
            Livro novoLivro = new Livro("LIV001", "O Código Limpo", List.of("Robert C. Martin"), "Alta Books", "978-85-7608-267-2", 2009, "Um clássico.", 89.90, 50, 0.8, "Programação", "/img/clean_code.jpg", 464);
            fachada.cadastrarLivro(novoLivro);
            System.out.println("OK: Livro cadastrado com sucesso.");

            // --- Cadastro de Cliente ---
            Cliente novoCliente = new Cliente("CLI001", "Ana Pereira", "ana.p@email.com", "senha123", new ArrayList<>(), new ArrayList<>(), new Date());
            fachada.cadastrarCliente(novoCliente);
            System.out.println("OK: Cliente cadastrado com sucesso.");

            // --- Cadastro de Pedido ---
            Endereco enderecoParaPedido = new Endereco("1", "Rua Teste", "123", "", "Cidade", "UF", "12345-678", "Brasil", "");
            Pedido novoPedido = new Pedido.Builder("PED001", novoCliente.getId(), new Date()).withStatus(Pedido.StatusPedido.PROCESSANDO).withEndereco(enderecoParaPedido).build();
            fachada.cadastrarPedido(novoPedido);
            System.out.println("OK: Pedido cadastrado com sucesso.");

            // --- Cadastro de Usuario ---
            Usuario novoUsuario = new Usuario("USR001", "admin", "admin123");
            fachada.cadastrarUsuario(novoUsuario);
            System.out.println("OK: Usuário cadastrado com sucesso.");

            // --- Cadastro de Carrinho ---
            Carrinho novoCarrinho = new Carrinho("CAR001", new ArrayList<>());
            fachada.cadastrarCarrinho(novoCarrinho);
            System.out.println("OK: Carrinho cadastrado com sucesso.");

            // --- Cadastro de WishList ---
            WishList novaWishList = new WishList("WSH001");
            fachada.cadastrarWishList(novaWishList);
            System.out.println("OK: WishList cadastrada com sucesso.");

            // --- Cadastro de Avaliacao ---
            Avaliacao novaAvaliacao = new Avaliacao("AV001", 5, "Excelente!", new Date(), true);
            fachada.cadastrarAvaliacao(novaAvaliacao);
            System.out.println("OK: Avaliação cadastrada com sucesso.");

            // --- Cadastro de Devolucao ---
            Devolucao novaDevolucao = new Devolucao("DEV001", "Produto errado", new Date(), "EM_ANALISE");
            fachada.cadastrarDevolucao(novaDevolucao);
            System.out.println("OK: Devolução cadastrada com sucesso.");

            // --- Cadastro de Pagamento ---
            Pagamento novoPagamento = new Pagamento("PAG001", "Cartão", "****1234", "APROVADO", new Date());
            fachada.cadastrarPagamento(novoPagamento);
            System.out.println("OK: Pagamento cadastrado com sucesso.");

            // --- Cadastro de Notificacao ---
            Notificacao novaNotificacao = new Notificacao("NOT001", "PEDIDO_ENVIADO", "Seu pedido foi enviado!", new Date(), "ENVIADA");
            fachada.cadastrarNotificacao(novaNotificacao);
            System.out.println("OK: Notificação cadastrada com sucesso.");

            // --- Cadastro de CupomPromocional ---
            CupomPromocional novoCupom = new CupomPromocional("CUP001", "PROMO10", "PORCENTAGEM", new Date(), new Date(), 10.0, 100);
            fachada.cadastrarCupomPromocional(novoCupom);
            System.out.println("OK: Cupom cadastrado com sucesso.");

            // --- Testes de Listagem ---
            System.out.println("\n--- Listagem de Entidades ---");
            System.out.println("Clientes cadastrados:");
            fachada.listarClientes();
            System.out.println("\nLivros cadastrados:");
            fachada.listarLivros();
            System.out.println("\nPedidos cadastrados:");
            fachada.listarPedidos();
            System.out.println("\nUsuários cadastrados:");
            fachada.listarUsuarios();
            System.out.println("\nCarrinhos cadastrados:");
            fachada.listarCarrinhos();
            System.out.println("\nWishLists cadastradas:");
            fachada.listarWishLists();
            System.out.println("\nAvaliações cadastradas:");
            fachada.listarAvaliacoes();
            System.out.println("\nDevoluções cadastradas:");
            fachada.listarDevolucoes();
            System.out.println("\nPagamentos cadastrados:");
            fachada.listarPagamentos();
            System.out.println("\nNotificações cadastradas:");
            fachada.listarNotificacoes();
            fachada.listarCuponsPromocionais();

            // --- Testes de Busca e Atualização ---
            System.out.println("\n--- Testes de Busca e Atualização ---");

            // --- Cliente ---
            try {
                System.out.println("Buscando cliente CLI001...");
                Cliente clienteEncontrado = fachada.buscarClientePorId("CLI001");
                System.out.println("OK: Cliente encontrado: " + clienteEncontrado.getNome());

                System.out.println("Atualizando nome do cliente para 'Ana Pereira Silva'...");
                clienteEncontrado.setNome("Ana Pereira Silva");
                fachada.atualizarCliente(clienteEncontrado);
                Cliente clienteAtualizado = fachada.buscarClientePorId("CLI001");
                if (!clienteAtualizado.getNome().equals("Ana Pereira Silva")) throw new Exception("Nome do cliente não foi atualizado.");
                System.out.println("OK: Cliente atualizado. Novo nome: " + clienteAtualizado.getNome());
            } catch (Exception e) {
                System.out.println("ERRO no teste de busca/atualização de Cliente: " + e.getMessage());
                houveErros = true;
            }

            // --- Livro ---
            try {
                System.out.println("\nBuscando livro LIV001...");
                Livro livroEncontrado = fachada.buscarLivroPorId("LIV001");
                System.out.println("OK: Livro encontrado: " + livroEncontrado.getTitulo());

                System.out.println("Atualizando preço do livro para 99.90...");
                livroEncontrado.setPreco(99.90);
                fachada.atualizarLivro(livroEncontrado);
                Livro livroAtualizado = fachada.buscarLivroPorId("LIV001");
                if (livroAtualizado.getPreco() != 99.90) throw new Exception("Preço do livro não foi atualizado.");
                System.out.println("OK: Livro atualizado. Novo preço: " + livroAtualizado.getPreco());
            } catch (Exception e) {
                System.out.println("ERRO no teste de busca/atualização de Livro: " + e.getMessage());
                houveErros = true;
            }

            // --- Pedido ---
            try {
                System.out.println("\nBuscando pedido PED001...");
                Pedido pedidoEncontrado = fachada.buscarPedidoPorId("PED001");
                System.out.println("OK: Pedido encontrado: " + pedidoEncontrado.getId());
                pedidoEncontrado.setStatus(Pedido.StatusPedido.ENVIADO);
                fachada.atualizarPedido(pedidoEncontrado);
                Pedido pedidoAtualizado = fachada.buscarPedidoPorId("PED001");
                if (pedidoAtualizado.getStatus() != Pedido.StatusPedido.ENVIADO) throw new Exception("Status do pedido não foi atualizado.");
                System.out.println("OK: Pedido atualizado. Novo status: " + pedidoAtualizado.getStatus());
            } catch (Exception e) {
                System.out.println("ERRO no teste de busca/atualização de Pedido: " + e.getMessage());
                houveErros = true;
            }

            // --- Usuario ---
            try {
                System.out.println("\nBuscando usuário USR001...");
                Usuario usuarioEncontrado = fachada.buscarUsuarioPorId("USR001");
                System.out.println("OK: Usuário encontrado: " + usuarioEncontrado.getLogin());
                usuarioEncontrado.setSenha("novaSenha123");
                fachada.atualizarUsuario(usuarioEncontrado);
                System.out.println("OK: Senha do usuário atualizada (verificação visual).");
            } catch (Exception e) {
                System.out.println("ERRO no teste de busca/atualização de Usuario: " + e.getMessage());
                houveErros = true;
            }

            // --- Carrinho ---
            try {
                System.out.println("\nBuscando carrinho CAR001...");
                Carrinho c = fachada.buscarCarrinhoPorId("CAR001");
                System.out.println("OK: Carrinho encontrado: " + c.getId());
                fachada.atualizarCarrinho(c);
                System.out.println("OK: Chamada de atualização do carrinho executada.");
            } catch (Exception e) {
                System.out.println("ERRO no teste de busca/atualização de Carrinho: " + e.getMessage());
                houveErros = true;
            }

            // --- WishList ---
            try {
                System.out.println("\nBuscando WishList WSH001...");
                WishList w = fachada.buscarWishListPorId("WSH001");
                System.out.println("OK: WishList encontrada: " + w.getId());
                fachada.atualizarWishList(w);
                System.out.println("OK: Chamada de atualização da WishList executada.");
            } catch (Exception e) {
                System.out.println("ERRO no teste de busca/atualização de WishList: " + e.getMessage());
                houveErros = true;
            }

            // --- Avaliacao ---
            try {
                System.out.println("\nBuscando Avaliacao AV001...");
                Avaliacao a = fachada.buscarAvaliacaoPorId("AV001");
                System.out.println("OK: Avaliação encontrada: " + a.getComentario());
                a.setComentario("Muito bom, recomendo!");
                fachada.atualizarAvaliacao(a);
                Avaliacao a2 = fachada.buscarAvaliacaoPorId("AV001");
                if (!a2.getComentario().equals("Muito bom, recomendo!")) throw new Exception("Comentário não foi atualizado.");
                System.out.println("OK: Avaliação atualizada. Novo comentário: " + a2.getComentario());
            } catch (Exception e) {
                System.out.println("ERRO no teste de busca/atualização de Avaliacao: " + e.getMessage());
                houveErros = true;
            }

            // --- Devolucao ---
            try {
                System.out.println("\nBuscando Devolucao DEV001...");
                Devolucao d = fachada.buscarDevolucaoPorId("DEV001");
                System.out.println("OK: Devolução encontrada: " + d.getMotivo());
                d.setStatus("APROVADA");
                fachada.atualizarDevolucao(d);
                Devolucao d2 = fachada.buscarDevolucaoPorId("DEV001");
                if (!d2.getStatus().equals("APROVADA")) throw new Exception("Status da devolução não foi atualizado.");
                System.out.println("OK: Devolução atualizada. Novo status: " + d2.getStatus());
            } catch (Exception e) {
                System.out.println("ERRO no teste de busca/atualização de Devolucao: " + e.getMessage());
                houveErros = true;
            }

            // --- Pagamento ---
            try {
                System.out.println("\nBuscando Pagamento PAG001...");
                Pagamento p = fachada.buscarPagamentoPorId("PAG001");
                System.out.println("OK: Pagamento encontrado: " + p.getStatus());
                p.setStatus("CONFIRMADO");
                fachada.atualizarPagamento(p);
                Pagamento p2 = fachada.buscarPagamentoPorId("PAG001");
                if (!p2.getStatus().equals("CONFIRMADO")) throw new Exception("Status do pagamento não foi atualizado.");
                System.out.println("OK: Pagamento atualizado. Novo status: " + p2.getStatus());
            } catch (Exception e) {
                System.out.println("ERRO no teste de busca/atualização de Pagamento: " + e.getMessage());
                houveErros = true;
            }

            // --- Notificacao ---
            try {
                System.out.println("\nBuscando Notificacao NOT001...");
                Notificacao n = fachada.buscarNotificacaoPorId("NOT001");
                System.out.println("OK: Notificação encontrada: " + n.getMensagem());
                n.setStatus("LIDA");
                fachada.atualizarNotificacao(n);
                Notificacao n2 = fachada.buscarNotificacaoPorId("NOT001");
                if (!n2.getStatus().equals("LIDA")) throw new Exception("Status da notificação não foi atualizado.");
                System.out.println("OK: Notificação atualizada. Novo status: " + n2.getStatus());
            } catch (Exception e) {
                System.out.println("ERRO no teste de busca/atualização de Notificacao: " + e.getMessage());
                houveErros = true;
            }

            // --- CupomPromocional ---
            try {
                System.out.println("\nBuscando Cupom CUP001...");
                CupomPromocional c = fachada.buscarCupomPromocionalPorId("CUP001");
                System.out.println("OK: Cupom encontrado: " + c.getCodigo());
                c.setAplicacaoMinima(15.0);
                fachada.atualizarCupomPromocional(c);
                CupomPromocional c2 = fachada.buscarCupomPromocionalPorId("CUP001");
                if (c2.getAplicacaoMinima() != 15.0) throw new Exception("Valor do cupom não foi atualizado.");
                System.out.println("OK: Cupom atualizado. Novo valor: " + c2.getAplicacaoMinima());
            } catch (Exception e) {
                System.out.println("ERRO no teste de busca/atualização de Cupom: " + e.getMessage());
                houveErros = true;
            }

        } catch (Exception e) {
            System.out.println("ERRO INESPERADO NO CAMINHO FELIZ: " + e.getMessage()); // Alterado para System.out
            houveErros = true;
        }


        System.out.println("\n--- Testes de Cenários de Falha (Exceções Esperadas) ---");

        // Teste: Tentar cadastrar livro duplicado
        try {
            Livro livroDuplicado = new Livro("LIV001", "Outro Livro", List.of("Autor"), "Editora", "ISBN", 2020, "Desc.", 10.0, 10, 0.1, "Cat.", "/img/img.jpg", 100);
            fachada.cadastrarLivro(livroDuplicado);
            System.out.println("FALHA: Livro duplicado cadastrado (NÃO DEVERIA ACONTECER).");
            houveErros = true;
        } catch (EntidadeJaExistenteExcecao e) { System.out.println("OK: Erro esperado ao cadastrar livro duplicado: " + e.getMessage()); // Alterado para System.out
        } catch (Exception e) { System.out.println("ERRO INESPERADO no teste de livro duplicado: " + e.getMessage()); houveErros = true; } // Alterado para System.out

        // Teste: Tentar cadastrar cliente duplicado
        try {
            Cliente clienteDuplicado = new Cliente("CLI001", "Ana P. Duplicada", "ana.p@email.com", "senha456", new ArrayList<>(), new ArrayList<>(), new Date());
            fachada.cadastrarCliente(clienteDuplicado);
            System.out.println("FALHA: Cliente duplicado cadastrado (NÃO DEVERIA ACONTECER).");
            houveErros = true;
        } catch (EntidadeJaExistenteExcecao e) { System.out.println("OK: Erro esperado ao cadastrar cliente duplicado: " + e.getMessage()); // Alterado para System.out
        } catch (Exception e) { System.out.println("ERRO INESPERADO no teste de cliente duplicado: " + e.getMessage()); houveErros = true; } // Alterado para System.out

        // Teste: Tentar cadastrar pedido duplicado
        try {
            Pedido pedidoDuplicado = new Pedido.Builder("PED001", "CLI001", new Date()).build();
            fachada.cadastrarPedido(pedidoDuplicado);
            System.out.println("FALHA: Pedido duplicado cadastrado (NÃO DEVERIA ACONTECER).");
            houveErros = true;
        } catch (EntidadeJaExistenteExcecao e) { System.out.println("OK: Erro esperado ao cadastrar pedido duplicado: " + e.getMessage()); // Alterado para System.out
        } catch (Exception e) { System.out.println("ERRO INESPERADO no teste de pedido duplicado: " + e.getMessage()); houveErros = true; } // Alterado para System.out

        // Teste: Tentar cadastrar usuário duplicado
        try {
            Usuario usuarioDuplicado = new Usuario("USR001", "admin_dup", "outrasenha");
            fachada.cadastrarUsuario(usuarioDuplicado);
            System.out.println("FALHA: Usuário duplicado cadastrado (NÃO DEVERIA ACONTECER).");
            houveErros = true;
        } catch (EntidadeJaExistenteExcecao e) { System.out.println("OK: Erro esperado ao cadastrar usuário duplicado: " + e.getMessage()); // Alterado para System.out
        } catch (Exception e) { System.out.println("ERRO INESPERADO no teste de usuário duplicado: " + e.getMessage()); houveErros = true; } // Alterado para System.out

        // Teste: Tentar cadastrar carrinho duplicado
        try {
            Carrinho carrinhoDuplicado = new Carrinho("CAR001", new ArrayList<>());
            fachada.cadastrarCarrinho(carrinhoDuplicado);
            System.out.println("FALHA: Carrinho duplicado cadastrado (NÃO DEVERIA ACONTECER).");
            houveErros = true;
        } catch (EntidadeJaExistenteExcecao e) { System.out.println("OK: Erro esperado ao cadastrar carrinho duplicado: " + e.getMessage()); // Alterado para System.out
        } catch (Exception e) { System.out.println("ERRO INESPERADO no teste de carrinho duplicado: " + e.getMessage()); houveErros = true; } // Alterado para System.out

        // Teste: Tentar cadastrar WishList duplicada
        try {
            WishList wishListDuplicada = new WishList("WSH001");
            fachada.cadastrarWishList(wishListDuplicada);
            System.out.println("FALHA: WishList duplicada cadastrada (NÃO DEVERIA ACONTECER).");
            houveErros = true;
        } catch (EntidadeJaExistenteExcecao e) { System.out.println("OK: Erro esperado ao cadastrar WishList duplicada: " + e.getMessage()); // Alterado para System.out
        } catch (Exception e) { System.out.println("ERRO INESPERADO no teste de WishList duplicada: " + e.getMessage()); houveErros = true; } // Alterado para System.out

        // Teste: Tentar cadastrar avaliação duplicada
        try {
            Avaliacao avaliacaoDuplicada = new Avaliacao("AV001", 1, "Ruim", new Date(), false);
            fachada.cadastrarAvaliacao(avaliacaoDuplicada);
            System.out.println("FALHA: Avaliação duplicada cadastrada (NÃO DEVERIA ACONTECER).");
            houveErros = true;
        } catch (EntidadeJaExistenteExcecao e) { System.out.println("OK: Erro esperado ao cadastrar avaliação duplicada: " + e.getMessage()); // Alterado para System.out
        } catch (Exception e) { System.out.println("ERRO INESPERADO no teste de avaliação duplicada: " + e.getMessage()); houveErros = true; } // Alterado para System.out

        // Teste: Tentar cadastrar devolução duplicada
        try {
            Devolucao devolucaoDuplicada = new Devolucao("DEV001", "Outro motivo", new Date(), "EM_ANALISE");
            fachada.cadastrarDevolucao(devolucaoDuplicada);
            System.out.println("FALHA: Devolução duplicada cadastrada (NÃO DEVERIA ACONTECER).");
            houveErros = true;
        } catch (EntidadeJaExistenteExcecao e) { System.out.println("OK: Erro esperado ao cadastrar devolução duplicada: " + e.getMessage()); // Alterado para System.out
        } catch (Exception e) { System.out.println("ERRO INESPERADO no teste de devolução duplicada: " + e.getMessage()); houveErros = true; } // Alterado para System.out

        // Teste: Tentar cadastrar pagamento duplicado
        try {
            Pagamento pagamentoDuplicado = new Pagamento("PAG001", "Boleto", "5678", "PENDENTE", new Date());
            fachada.cadastrarPagamento(pagamentoDuplicado);
            System.out.println("FALHA: Pagamento duplicado cadastrado (NÃO DEVERIA ACONTECER).");
            houveErros = true;
        } catch (EntidadeJaExistenteExcecao e) { System.out.println("OK: Erro esperado ao cadastrar pagamento duplicado: " + e.getMessage()); // Alterado para System.out
        } catch (Exception e) { System.out.println("ERRO INESPERADO no teste de pagamento duplicado: " + e.getMessage()); houveErros = true; } // Alterado para System.out

        // Teste: Tentar cadastrar notificação duplicada
        try {
            Notificacao notificacaoDuplicada = new Notificacao("NOT001", "PEDIDO_ENTREGUE", "Seu pedido foi entregue!", new Date(), "LIDA");
            fachada.cadastrarNotificacao(notificacaoDuplicada);
            System.out.println("FALHA: Notificação duplicada cadastrada (NÃO DEVERIA ACONTECER).");
            houveErros = true;
        } catch (EntidadeJaExistenteExcecao e) { System.out.println("OK: Erro esperado ao cadastrar notificação duplicada: " + e.getMessage()); // Alterado para System.out
        } catch (Exception e) { System.out.println("ERRO INESPERADO no teste de notificação duplicada: " + e.getMessage()); houveErros = true; } // Alterado para System.out

        // Teste: Tentar cadastrar cupom duplicado
        try {
            CupomPromocional cupomDuplicado = new CupomPromocional("CUP001", "PROMO20", "FIXO", new Date(), new Date(), 20.0, 50);
            fachada.cadastrarCupomPromocional(cupomDuplicado);
            System.out.println("FALHA: Cupom duplicado cadastrado (NÃO DEVERIA ACONTECER).");
            houveErros = true;
        } catch (EntidadeJaExistenteExcecao e) { System.out.println("OK: Erro esperado ao cadastrar cupom duplicado: " + e.getMessage()); // Alterado para System.out
        } catch (Exception e) { System.out.println("ERRO INESPERADO no teste de cupom duplicado: " + e.getMessage()); houveErros = true; } // Alterado para System.out

        // Teste: Estoque Insuficiente
        try {
            Livro livroEstoque = new Livro("LIV-ESTOQUE", "Livro de Teste", List.of("Autor"), "Editora", "ISBN", 2023, "Desc", 50.0, 10, 0.5, "Cat", "img", 100);
            livroEstoque.diminuirEstoque(5); // Operação válida
            livroEstoque.diminuirEstoque(10); // Deve falhar
            System.out.println("FALHA: Estoque diminuído além do limite (NÃO DEVERIA ACONTECER).");
            houveErros = true;
        } catch (EstoqueInsuficienteExcecao e) {
            System.out.println("OK: Erro esperado ao tentar diminuir estoque além do disponível: " + e.getMessage()); // Alterado para System.out
        } catch (Exception e) {
            System.out.println("ERRO INESPERADO no teste de estoque insuficiente: " + e.getMessage()); // Alterado para System.out
            houveErros = true;
        }

        // Teste: Operação Não Permitida
        try {
            Devolucao devolucao = new Devolucao("DEV-TESTE", "Produto com defeito", new Date(), "EM_ANALISE");
            devolucao.aprovar(); // Operação válida
            devolucao.recusar(); // Deve falhar
            System.out.println("FALHA: Devolução recusada após aprovação (NÃO DEVERIA ACONTECER).");
            houveErros = true;
        } catch (OperacaoNaoPermitidaExcecao e) {
            System.out.println("OK: Erro esperado ao tentar uma operação não permitida: " + e.getMessage()); // Alterado para System.out
        } catch (Exception e) {
            System.out.println("ERRO INESPERADO no teste de operação não permitida: " + e.getMessage()); // Alterado para System.out
            houveErros = true;
        }

        // --- Testes de Remoção ---
        System.out.println("\n--- Testes de Remoção ---");
        try {
            System.out.println("Removendo Cupom CUP001...");
            fachada.removerCupomPromocional("CUP001");
            if (fachada.buscarCupomPromocionalPorId("CUP001") != null) throw new Exception("Cupom não foi removido.");
            System.out.println("OK: Cupom removido.");

            System.out.println("Removendo Notificacao NOT001...");
            fachada.removerNotificacao("NOT001");
            if (fachada.buscarNotificacaoPorId("NOT001") != null) throw new Exception("Notificação não foi removida.");
            System.out.println("OK: Notificação removida.");

            System.out.println("Removendo Pagamento PAG001...");
            fachada.removerPagamento("PAG001");
            if (fachada.buscarPagamentoPorId("PAG001") != null) throw new Exception("Pagamento não foi removido.");
            System.out.println("OK: Pagamento removido.");

            System.out.println("Removendo Devolucao DEV001...");
            fachada.removerDevolucao("DEV001");
            if (fachada.buscarDevolucaoPorId("DEV001") != null) throw new Exception("Devolução não foi removida.");
            System.out.println("OK: Devolução removida.");

            System.out.println("Removendo Avaliacao AV001...");
            fachada.removerAvaliacao("AV001");
            if (fachada.buscarAvaliacaoPorId("AV001") != null) throw new Exception("Avaliação não foi removida.");
            System.out.println("OK: Avaliação removida.");

            System.out.println("Removendo WishList WSH001...");
            fachada.removerWishList("WSH001");
            if (fachada.buscarWishListPorId("WSH001") != null) throw new Exception("WishList não foi removida.");
            System.out.println("OK: WishList removida.");

            System.out.println("Removendo Carrinho CAR001...");
            fachada.removerCarrinho("CAR001");
            if (fachada.buscarCarrinhoPorId("CAR001") != null) throw new Exception("Carrinho não foi removido.");
            System.out.println("OK: Carrinho removido.");

            System.out.println("Removendo Pedido PED001...");
            fachada.removerPedido("PED001");
            if (fachada.buscarPedidoPorId("PED001") != null) throw new Exception("Pedido não foi removido.");
            System.out.println("OK: Pedido removido.");
            
            System.out.println("Removendo Usuario USR001...");
            fachada.removerUsuario("USR001");
            if (fachada.buscarUsuarioPorId("USR001") != null) throw new Exception("Usuário não foi removido.");
            System.out.println("OK: Usuário removido.");

            System.out.println("Removendo Cliente CLI001...");
            fachada.removerCliente("CLI001");
            if (fachada.buscarClientePorId("CLI001") != null) throw new Exception("Cliente não foi removido.");
            System.out.println("OK: Cliente removido.");

            System.out.println("Removendo Livro LIV001...");
            fachada.removerLivro("LIV001");
            if (fachada.buscarLivroPorId("LIV001") != null) throw new Exception("Livro não foi removido.");
            System.out.println("OK: Livro removido.");

        } catch (Exception e) {
            System.out.println("ERRO durante a fase de remoção: " + e.getMessage());
            houveErros = true;
        }

        // --- Mensagem Final ---
        if (!houveErros) {
            System.out.println("\nTodos os testes foram concluídos com sucesso!");
        } else {
            System.err.println("\n!!! Testes concluídos COM ERROS. Por favor, verifique as mensagens de erro acima. !!!"); // Alterado para System.err
        }
    }
}
