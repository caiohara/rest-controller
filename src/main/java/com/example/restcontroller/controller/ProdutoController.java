package com.example.restcontroller.controller;

import com.example.restcontroller.model.Produto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private List<Produto> produtos = new ArrayList<>();
    private AtomicLong contador = new AtomicLong(1);

    public ProdutoController() {
        // Dados estáticos iniciais
        produtos.add(new Produto(contador.getAndIncrement(), "Notebook", 3500.00, "Eletrônicos"));
        produtos.add(new Produto(contador.getAndIncrement(), "Mouse", 50.00, "Eletrônicos"));
        produtos.add(new Produto(contador.getAndIncrement(), "Cadeira Gamer", 1200.00, "Móveis"));
        produtos.add(new Produto(contador.getAndIncrement(), "Mesa", 800.00, "Móveis"));
        produtos.add(new Produto(contador.getAndIncrement(), "Teclado Mecânico", 450.00, "Eletrônicos"));
    }

    // POST - Criar novo produto
    @PostMapping
    public ResponseEntity<Produto> criarProduto(@RequestBody Produto produto) {
        produto.setId(contador.getAndIncrement());
        produtos.add(produto);
        return ResponseEntity.status(HttpStatus.CREATED).body(produto);
    }

    // GET - Buscar todos os produtos
    @GetMapping
    public ResponseEntity<List<Produto>> listarTodos() {
        return ResponseEntity.ok(produtos);
    }

    // GET - Buscar produto por ID
    @GetMapping("/{id}")
    public ResponseEntity<Produto> buscarPorId(@PathVariable Long id) {
        Optional<Produto> produto = produtos.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();

        return produto.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // GET - Buscar produtos por categoria (query/filtro)
    @GetMapping("/search")
    public ResponseEntity<List<Produto>> buscarPorCategoria(@RequestParam String categoria) {
        List<Produto> resultado = produtos.stream()
                .filter(p -> p.getCategoria().equalsIgnoreCase(categoria))
                .collect(Collectors.toList());

        return ResponseEntity.ok(resultado);
    }

    // DELETE - Apagar produto por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarProduto(@PathVariable Long id) {
        boolean removido = produtos.removeIf(p -> p.getId().equals(id));

        if (removido) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
