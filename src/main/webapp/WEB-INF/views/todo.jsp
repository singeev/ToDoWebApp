<%@ include file="common/header.jspf"%>

<%----- NAVIGATION BAR -----%>

<%@ include file="common/navigation.jspf"%>


<div class="container">
<h1>Add a ToDo</h1>
<form:form method="post" commandName="todo">
  <form:hidden path="id"/>
  <fieldset class="form-group">
    <form:label path="desc">Description</form:label>
    <form:input path="desc" type="text" class="form-control" required="required"/>
    <form:errors path="desc" cssClass="test-warning"/>
  </fieldset>
  <fieldset class="form-group">
    <form:label path="targetDate">Target date</form:label>
    <form:input path="targetDate" type="text" class="form-control" required="required"/>
    <form:errors path="targetDate" cssClass="test-warning"/>
  </fieldset>
  <input class="btn btn-success" type="submit" value="Submit"/>
  <a class="btn btn-warning" href="/todo-list">Cancel</a>
</form:form>
</div>

<%@ include file="common/footer.jspf"%>
