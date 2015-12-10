<%@ page contentType="text/html; charset=UTF-8" language="java" %>

<c:if test="${!_page.first || !_page.last}">
  <nav>
    <ul class="pager">
      <c:if test="${!_page.first}">
        <li class="previous"><a href="?page=${_page.number}"><span aria-hidden="true">&larr;</span> Newer</a></li>
      </c:if>
      <c:if test="${!_page.last}">
        <li class="next"><a href="?page=${_page.number + 2}">Older <span aria-hidden="true">&rarr;</span></a></li>
      </c:if>
    </ul>
  </nav>
</c:if>