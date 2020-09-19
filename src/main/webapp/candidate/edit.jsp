<%--
  Created by IntelliJ IDEA.
  User: cns
  Date: 20.08.2020
  Time: 19:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.time.LocalDateTime" %>
<%@ page import="ru.job4j.dreamjob.model.Candidate" %>
<%@ page import="ru.job4j.dreamjob.store.PsqlStore" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
            integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
            integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
            integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
            crossorigin="anonymous"></script>
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    <script>
        function validate() {
            let name = $('#name').val();
            let pos = $('#position').val();
            let ad = $('#address').val();
            switch ('') {
                case name:
                    alert('You need to fill in the field name');
                    break;
                case pos:
                    alert('You need to fill in the field position');
                    break;
                case ad:
                    alert('You need to fill in the field address');
                    break;
            }
            return name !== '' && pos !== '' && ad !== '';
        }

        $(document).ready(function () {
            $.ajax({
                type: 'GET',
                url: 'http://localhost:8080/job4j_dreamjob/city',
                dataType: 'json'
            }).done(function (data) {
                $.each(data, function (index, value) {
                    $('#city').append('<option value="' + index + '">' + value + '</option>')
                });
            }).fail(function (err) {
                alert(err);
            });
        });
    </script>

    <title>Работа мечты</title>
</head>
<body>
<%
    String id = request.getParameter("id");
    Candidate candidate = new Candidate(0, "", "", "", LocalDateTime.now(), "", 0);
    if (id != null) {
        candidate = PsqlStore.instOf().findCandidateById(Integer.parseInt(id));
    }
%>
<div class="container pt-3">
    <div class="row">
        <ul class="nav">
            <li class="nav-item">
                <a class="nav-link" href="<%=request.getContextPath()%>/post/posts.do">Вакансии</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<%=request.getContextPath()%>/post/edit.jsp">Добавить вакансию</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<%=request.getContextPath()%>/candidate/candidates.do">Кандидаты</a>
            </li>

            <li class="nav-item">
                <a class="nav-link" href="<%=request.getContextPath()%>/candidate/edit.jsp">Добавить кандидата</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<%=request.getContextPath()%>/login.jsp"> <c:out value="${user.name}"/> |
                    Выйти</a>
            </li>
        </ul>
    </div>
    <div class="row">
        <div class="card" style="width: 100%">
            <div class="card-header">
                <% if (id == null) { %>
                Новый кандидат.
                <% } else { %>
                Редактирование данных кандидата.
                <% } %></div>
            <div class="card-body">
                <form action="<%=request.getContextPath()%>/candidate/candidates.do?id=<%=candidate.getId()%>"
                      method="post" enctype="multipart/form-data">
                    <div class="form-group">
                        <label>Имя</label>
                        <input type="text" class="form-control" name="name" id="name" value="<%=candidate.getName()%>">
                        <label>Позиция</label>
                        <input type="text" class="form-control" name="position" id="position"
                               value="<%=candidate.getPosition()%>">
                        <label>Адрес</label>
                        <input type="text" class="form-control" name="address" id="address"
                               value="<%=candidate.getAddress()%>">
                        <label>Город</label>
                        <select class="form-control" id="city" name="city"></select>
                        <label>Фото</label>
                        <% if (id != null) { %>
                        <img class="m-3" src="<%=request.getContextPath()%>/download?name=<%=candidate.getPhotoId()%>"
                             width="100px"
                             height="100px" alt="Нет фото"/>
                        <% } %>
                    </div>
                    <div>
                        <div class="checkbox mb-4">
                            <input type="file" name="file">
                        </div>
                        <button type="submit" class="btn btn-primary" onclick="return validate()">Сохранить</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
