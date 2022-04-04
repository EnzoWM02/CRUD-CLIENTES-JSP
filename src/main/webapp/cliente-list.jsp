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
            <a class="navbar-brand"> Gerenciamento de clientes </a>
        </div>

        <ul class="navbar-nav">
            <li><a href="<%=request.getContextPath()%>/list"
                   class="nav-link">Clientes</a></li>
        </ul>
    </nav>
</header>
<br>

<div class="row">
    <!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

    <div class="container">
        <h3 class="text-center">Lista de clientes</h3>
        <hr>
        <div class="container text-left">

            <a href="<%=request.getContextPath()%>/new" class="btn btn-success">Adicionar novo usuário</a>
        </div>
        <br>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>ID</th>
                <th>Nome</th>
                <th>Email</th>
                <th>Pais</th>
                <th>Situação</th>
                <th>Ações</th>
            </tr>
            </thead>
            <tbody>

            <c:forEach var="Cliente" items="${listarCliente}">
                <option>
                <tr>
                    <td><c:out value="${Cliente.id}" /></td>
                    <td><c:out value="${Cliente.nome}" /></td>
                    <td><c:out value="${Cliente.email}" /></td>
                    <td><c:out value="${Cliente.pais}" /></td>
                    <td><c:out value="${Cliente.situacao}" /></td>
                    <td><a href="edit?id=<c:out value='${Cliente.id}'   />">Editar</a>
                        &nbsp;&nbsp;&nbsp;&nbsp; <a
                                onclick="return confirm('Tem certeza que deseja deletar o cliente ' + '${Cliente.nome}' + '?')"
                                href="delete?id=<c:out value='${Cliente.id}' />">Deletar</a></td>
                </tr>
                </option>
            </c:forEach>
            </tbody>

        </table>
    </div>
</div>
</body>
</html>