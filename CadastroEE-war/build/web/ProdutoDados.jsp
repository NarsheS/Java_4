<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Cadastro de Produto</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</head>
<body class="container">
    <h1>${produto != null ? "Alterar Produto" : "Incluir Novo Produto"}</h1>

    <form class="form" action="ServletFC" method="post">
        <input class="form-control" type="hidden" name="acao" value="${produto != null ? 'alterar' : 'incluir'}"/>
        
        <c:if test="${produto != null}">
            <input class="form-control" type="hidden" name="id" value="${produto.produtoId}"/>
        </c:if>

        <div class="mb-3">
            <label class="form-label" for="nome">Nome:</label>
            <input class="form-control" type="text" id="nome" name="nome" value="${produto != null ? produto.nome : ''}" required/>
        </div>

        <div class="mb-3">
            <label class="form-label" for="quantidade">Quantidade:</label>
            <input class="form-control" type="number" id="quantidade" name="quantidade" value="${produto != null ? produto.estoque : ''}" required/>
        </div>

        <div class="mb-3">
            <label class="form-label" for="preco">Preço:</label>
            <input class="form-control" type="number" id="preco" name="preco" value="${produto != null ? produto.preço : ''}" step="0.01" required/>
        </div>

        <div>
            <input class="btn btn-primary" type="submit" value="${produto != null ? 'Alterar' : 'Incluir'}"/>
        </div>
    </form>

    <br>
    <a href="ServletFC?acao=listar">Voltar para Lista de Produtos</a>
</body>
</html>
