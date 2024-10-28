package cadastroee.controller;

import cadastroee.model.Produtos;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

/**
 *
 * @author pc
 */
@Stateless
public class ProdutosFacade extends AbstractFacade<Produtos> implements ProdutosFacadeLocal {

    @PersistenceContext(unitName = "CadastroEE-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProdutosFacade() {
        super(Produtos.class);
    }
    
    @Override
    public Integer getLastProdutoId() {
        try {
            TypedQuery<Integer> query = em.createQuery("SELECT MAX(p.produtoId) FROM Produtos p", Integer.class);
            Integer lastId = query.getSingleResult();
            return lastId != null ? lastId : 0; // Retorna 0 se não houver produtos
        } catch (Exception e) {
            // Trate a exceção conforme necessário
            e.printStackTrace();
            return null; // ou lançar uma exceção
        }
    }
}
