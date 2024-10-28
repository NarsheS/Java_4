<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Lista de Produtos</title>
    
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</head>
<body class="container">
    <h1>Lista de Produtos</h1>

    <!-- Link para incluir um novo produto -->
    <a href="ServletFC?acao=formIncluir" class="btn btn-primary m-2">Incluir Novo Produto</a>


    <br/><br/>

    <!-- Verifica se há produtos na lista -->
    <c:if test="${not empty produtos}">
        <!-- Tabela para apresentação dos produtos -->
        <table class="table table-striped">
            <thead class="table-dark">
                <tr>
                    <th>ID</th>
                    <th>Nome</th>
                    <th>Quantidade</th>
                    <th>Preço</th>
                    <th>Opções</th>
                </tr>
            </thead>
            <tbody>
                <!-- Percorre a lista de produtos e apresenta os dados na tabela -->
                <c:forEach var="produto" items="${produtos}">
                    <tr>
                        <td>${produto.produtoId}</td> <!-- Corrigido de 'produto.id' para 'produto.produtoId' -->
                        <td>${produto.nome}</td>
                        <td>${produto.estoque}</td>
                        <td>${produto.preço}</td>
                        <td>
                            <a href="ServletFC?acao=formAlterar&id=${produto.produtoId}" class="btn btn-primary btn-sm">Alterar</a>
                            <a href="ServletFC?acao=excluir&id=${produto.produtoId}" class="btn btn-danger btn-sm" 
                               onclick="return confirm('Tem certeza que deseja excluir o produto ${produto.nome}?');">
                                Excluir
                            </a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:if>

    <!-- Mensagem caso não haja produtos na lista -->
    <c:if test="${empty produtos}">
        <p>Nenhum produto encontrado.</p>
    </c:if>

</body>
</html>
