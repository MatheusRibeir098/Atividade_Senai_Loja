
package com.loja.ui;

import com.loja.gerenciador.GerenciadorProdutos;
import com.loja.modelo.Produto;

import java.util.List;
import java.util.Scanner;

public class MenuProdutos {
    private Scanner scanner = new Scanner(System.in);
    private GerenciadorProdutos gerenciador = new GerenciadorProdutos();

    public void exibirMenu() {
        int opcao = 0;
        while (opcao != 6) {
            System.out.println("1. Cadastrar Produto");
            System.out.println("2. Buscar Produto por ID");
            System.out.println("3. Listar Todos os Produtos");
            System.out.println("4. Atualizar Produto");
            System.out.println("5. Deletar Produto");
            System.out.println("6. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = Integer.parseInt(scanner.nextLine());

            if (opcao == 1) {
                cadastrarProduto();
            } else if (opcao == 2) {
                buscarProduto();
            } else if (opcao == 3) {
                listarProdutos();

            } else if (opcao == 4) {
                atualizarProduto();

            } else if (opcao == 5) {
                deletarProduto();

            } else if (opcao != 6) {
                System.out.println("Opção inválida!");
            }
        }
        System.out.println("Saindo...");
    }

    private void cadastrarProduto() {
        System.out.print("Digite o nome: ");
        String nome = scanner.nextLine();
        if(nome == null || nome.length() < 2){
            System.out.println("Nome do Produto inválido");
            return;
        }
        System.out.print("Digite o preço: ");
        double preco = Double.parseDouble(scanner.nextLine());
        if(preco <= 0){
            System.out.println("Preco do Produto inválido");
            return;
        }
        System.out.print("Digite a quantidade: ");
        int quantidade = Integer.parseInt(scanner.nextLine());
        if (quantidade < 0){
            System.out.println("Quantidade do Produto inválido");
            return;
        }
        System.out.print("Digite a categoria: ");
        String categoria = scanner.nextLine();
        if (categoria.equals("") || categoria == null){
            System.out.println("Categoria do Produto inválido");
            return;
        }

        Produto produto = new Produto(nome, preco, quantidade, categoria);
        gerenciador.criar(produto);
        System.out.println("Produto cadastrado com sucesso!");
    }

    private void buscarProduto() {
        System.out.print("Digite o ID: ");
        int id = Integer.parseInt(scanner.nextLine());
        Produto produto = gerenciador.buscarPorId(id);
        if (produto != null) {
            System.out.println(produto);
        } else {
            System.out.println("Produto não encontrado.");
        }
    }

    private void listarProdutos() {
        List<Produto> produtos = gerenciador.listarTodos();
        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
        } else {
            for (Produto produto : produtos) {
                System.out.println(produto);
            }
        }
    }

    private void atualizarProduto() {
        System.out.print("Digite o ID do produto: ");
        int id = Integer.parseInt(scanner.nextLine());
        Produto produtoExistente = gerenciador.buscarPorId(id);
        if (produtoExistente != null) {
            System.out.print("Digite o novo nome: ");
            produtoExistente.setNome(scanner.nextLine());
            System.out.print("Digite o novo preço: ");
            produtoExistente.setPreco(Double.parseDouble(scanner.nextLine()));
            System.out.print("Digite a nova quantidade: ");
            produtoExistente.setQuantidadeEstoque(Integer.parseInt(scanner.nextLine()));
            System.out.print("Digite a nova categoria: ");
            produtoExistente.setCategoria(scanner.nextLine());

            gerenciador.atualizar(produtoExistente);
            System.out.println("Produto atualizado com sucesso!");
        } else {
            System.out.println("Produto não encontrado.");
        }
    }

    private void deletarProduto() {
        System.out.print("Digite o ID: ");
        int resp;
        int id = Integer.parseInt(scanner.nextLine());
        System.out.println("Tem certeza que deseja excluir esse Produto?  1 - Sim | 2 - Não");
        resp = Integer.parseInt(scanner.nextLine());
        if (resp == 1){
            if (gerenciador.deletar(id)) {
                System.out.println("Produto deletado com sucesso!");
            } else {
                System.out.println("Produto não encontrado.");
            }
        } else if(resp==2) {
            System.out.println("Operação cancelada");
        } else {
            System.out.println("Opção invalida");
        }

    }
}
