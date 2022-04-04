<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Gerenciamento de clientes</title>
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
</head>
<body>
<header>
    <nav class="navbar navbar-expand-md navbar-dark"
         style="background-color: tomato">
        <div>
            <a class="navbar-brand">Gerenciamento de clientes</a>
        </div>

        <ul class="navbar-nav">
            <li><a href="<%=request.getContextPath()%>/list"
                   class="nav-link">Clientes</a></li>
        </ul>
    </nav>
</header>
<br>
<script>
    function validateEmail() {

        let regex = new RegExp('[a-z0-9]+@[a-z]+\.[a-z]{2,3}');
        if (!regex.test(document.getElementById("forms").elements["email"].value)) {
            let styles = "#email { background-color: #ff5e5e; }";
            var styleSheet = document.createElement("style");
            styleSheet.innerText = styles;
            document.head.appendChild(styleSheet);
            return false;
        } else {
            let styles = "#cpf { background-color: white; }";
            var styleSheet = document.createElement("style");
            styleSheet.innerText = styles;
            document.head.appendChild(styleSheet);
            return true;
        }
    }
</script>
<div class="container col-md-5">
    <div class="card">
        <div class="card-body">
            <c:if test="${Cliente != null}">
            <form action="update" method="post" id="forms" onsubmit="return validateEmail();">
                </c:if>
                <c:if test="${Cliente == null}">
                <form action="insert" method="post" id="forms" onsubmit="return validateEmail();">
                    </c:if>

                    <caption>
                        <h2>
                            <c:if test="${Cliente != null}">
                                Editar o cliente
                            </c:if>
                            <c:if test="${Cliente == null}">
                                Adicionar novo cliente
                            </c:if>
                        </h2>
                    </caption>

                    <c:if test="${Cliente != null}">
                        <input type="hidden" name="id" value="<c:out value='${Cliente.id}' />" />
                    </c:if>

                    <fieldset class="form-group">
                        <label>Nome do cliente</label> <input type="text"
                                                        value="<c:out value='${Cliente.nome}' />" class="form-control"
                                                        name="nome" required="required">
                    </fieldset>

                    <fieldset class="form-group">
                        <label>Email do cliente</label> <input type="text"
                                                         value="<c:out value='${Cliente.email}' />" class="form-control"
                                                         name="email" id="email" required="required">
                    </fieldset>

                    <fieldset class="form-group">
                        <label>País do cliente</label> <input type="text"
                                                           value="<c:out value='${Cliente.pais}' />" class="form-control"
                                                           name="pais" required="required">
                    </fieldset>

                    <fieldset class="form-group">
                        <label>Situação do cliente</label> <select type="text"
                                                              value="<c:out value='${Cliente.situacao}' />" class="form-control"
                                                              name="situacao">
                        <option value="Ativo">Ativo</option>
                        <option value="Inativo">Inativo</option>
                    </select>
                    </fieldset>

                    <button type="submit" class="btn btn-success">Salvar</button>
                </form>
        </div>
    </div>
</div>
</body>
</html>