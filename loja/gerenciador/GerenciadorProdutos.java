
package com.loja.gerenciador;

import com.loja.modelo.Produto;
import java.util.ArrayList;
import java.util.List;

public class GerenciadorProdutos {
    private List<Produto> produtos = new ArrayList<>();
    private int proximoId = 1;

    public void criar(Produto produto) {
        produto.setId(proximoId);
        proximoId++;
        produtos.add(produto);
    }

    public Produto buscarPorId(int id) {
        for (Produto produto : produtos) {
            if (produto.getId() == id) {
                return produto;
            }
        }
        return null;
    }

    public List<Produto> listarTodos() {
        return new ArrayList<>(produtos);
    }

    public boolean atualizar(Produto produtoAtualizar) {
        for (Produto produto : produtos){
            if (produto.equals(produtoAtualizar)){
                int index = produtos.indexOf(produto);
               produtos.set(index, produto);
               return true;
            }
        }
        return false;
    }

    public boolean deletar(int id) {

        for (Produto produto : produtos){
            if (produto.getId() == id){
                produtos.remove(produto);
                return true;
            }
        }
        return false;
    }
}
