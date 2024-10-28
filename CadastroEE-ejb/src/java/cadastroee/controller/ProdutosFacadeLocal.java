package cadastroee.controller;

import cadastroee.model.Produtos;
import jakarta.ejb.Local;
import java.util.List;

@Local
public interface ProdutosFacadeLocal {

    void create(Produtos produtos);

    void edit(Produtos produtos);

    void remove(Produtos produtos);

    Produtos find(Object id);

    List<Produtos> findAll();

    List<Produtos> findRange(int[] range);

    int count();
    
    // Adicionando o método para obter o último ID
    Integer getLastProdutoId();
}
