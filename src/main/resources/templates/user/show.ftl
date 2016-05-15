<#import "../layout/defaultLayout.ftl" as layout>
<@layout.layout "Detail of the user">
  <h1>Detail of the user</h1>
  <dl class="dl-horizontal">
    <#include "_show.ftl">
  </dl>
  <a href="${urlFor('edit?id=' + user.id)}">Edit</a>
  <a href="${urlFor('delete?id=' + user.id + '&_method=delete')}">Remove</a>
</@layout.layout>
