package cadastroee.servlets;

import cadastroee.controller.ProdutosFacadeLocal;
import cadastroee.model.Produtos;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ServletFC", urlPatterns = {"/ServletFC"})
public class ServletFC extends HttpServlet {

    @jakarta.ejb.EJB
    private ProdutosFacadeLocal facade;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String acao = request.getParameter("acao");
        String destino = "ProdutoDados.jsp";

        if (acao == null) {
            acao = "listar"; // Define a ação padrão como listar
        }

        try {
            switch (acao) {
                case "listar":
                    List<Produtos> produtos = facade.findAll();
                    request.setAttribute("produtos", produtos);
                    destino = "ProdutoLista.jsp";
                    break;

                case "formIncluir":
                    destino = "ProdutoDados.jsp";
                    break;

                case "formAlterar":
                    //aqui tem um erro
                    String idAlterar = request.getParameter("id");
                    if (idAlterar != null) {
                        Produtos produtoAlterar = facade.find(Integer.parseInt(idAlterar));
                        request.setAttribute("produto", produtoAlterar);
                    }
                    destino = "ProdutoDados.jsp";
                    break;

                case "incluir":
                    Produtos novoProduto = new Produtos();
                    novoProduto.setProdutoId(facade.getLastProdutoId() + 1);
                    novoProduto.setNome(request.getParameter("nome"));

                    String quantidadeStr = request.getParameter("quantidade");
                    if (quantidadeStr != null && !quantidadeStr.isEmpty()) {
                        novoProduto.setEstoque(Integer.parseInt(quantidadeStr));
                    } else {
                        throw new NumberFormatException("Quantidade não pode ser nula ou vazia.");
                    }

                    String precoStr = request.getParameter("preco"); // Corrigido o nome do parâmetro
                    if (precoStr != null && !precoStr.isEmpty()) {
                        novoProduto.setPreço(Float.parseFloat(precoStr));
                    } else {
                        throw new NumberFormatException("Preço não pode ser nulo ou vazio.");
                    }

                    facade.create(novoProduto);
                    request.setAttribute("produtos", facade.findAll());
                    destino = "ProdutoLista.jsp";
                    break;

                case "alterar":
                    String idAlterarPost = request.getParameter("id");
                    
                    if (idAlterarPost != null) {
                        Produtos produtoAlterarPost = facade.find(Integer.parseInt(idAlterarPost));
                        produtoAlterarPost.setProdutoId(Integer.parseInt(idAlterarPost));
                        produtoAlterarPost.setNome(request.getParameter("nome"));

                        String quantidadeAlterarStr = request.getParameter("quantidade");
                        if (quantidadeAlterarStr != null && !quantidadeAlterarStr.isEmpty()) {
                            produtoAlterarPost.setEstoque(Integer.parseInt(quantidadeAlterarStr));
                        } else {
                            throw new NumberFormatException("Quantidade não pode ser nula ou vazia.");
                        }

                        String precoAlterarStr = request.getParameter("preco"); // Corrigido o nome do parâmetro
                        if (precoAlterarStr != null && !precoAlterarStr.isEmpty()) {
                            produtoAlterarPost.setPreço(Float.parseFloat(precoAlterarStr));
                        } else {
                            throw new NumberFormatException("Preço não pode ser nulo ou vazio.");
                        }

                        facade.edit(produtoAlterarPost);
                        request.setAttribute("produtos", facade.findAll());
                        destino = "ProdutoLista.jsp";
                    }
                    break;

                case "excluir":
                    String idExcluir = request.getParameter("id");
                    if (idExcluir != null) {
                        Produtos produtoExcluir = facade.find(Integer.parseInt(idExcluir));
                        facade.remove(produtoExcluir);
                    }
                    request.setAttribute("produtos", facade.findAll());
                    destino = "ProdutoLista.jsp";
                    break;

                default:
                    request.setAttribute("mensagem", "Ação não reconhecida.");
                    destino = "erro.jsp";
                    break;
            }
        } catch (NumberFormatException e) {
            request.setAttribute("mensagem", "Erro ao processar os dados: " + e.getMessage());
            destino = "erro.jsp";
        } catch (Exception e) {
            request.setAttribute("mensagem", "Erro ao executar a operação: " + e.getMessage());
            destino = "erro.jsp";
        }

        request.getRequestDispatcher(destino).forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Servlet Produto Front Controller";
    }
}
