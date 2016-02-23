<%@ include file="common/header.jspf"%>

<%----- NAVIGATION BAR -----%>

<%@ include file="common/navigation.jspf"%>

<%----- TODOS TABLE -----%>

<div class="container">
  <table class="table table-striped">
    <caption><spring:message code="todo.caption"/></caption>
    <thead>
      <th>ID</th>
      <th>Description</th>
      <th>Target Date</th>
      <th>Is Complete?</th>
      <th></th>
    </thead>
    <tbody>
      <c:forEach items="${todos}" var="todo">
        <tr>
          <td>${todo.id}</td>
          <td>${todo.desc}</td>
          <td>
            <fmt:formatDate pattern="dd/MM/yyyy" value="${todo.targetDate}"/>
          </td>
          <td>${todo.done}</td>
          <td>
            <a class="btn btn-success" href="/update-todo?id=${todo.id}"><span class="glyphicon glyphicon-pencil"></span></a>
            <a class="btn btn-danger" href="/delete-todo?id=${todo.id}"><span class="glyphicon glyphicon-trash"></span></a>
          </td>
        </tr>
      </c:forEach>
    </tbody>
  </table>

  <%----- ADD BUTTON -----%>
  <div>
    <a class="btn btn-success" href="/add-todo">Add new</a>
  </div>
</div>

<%@ include file="common/footer.jspf"%>