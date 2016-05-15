    <div class="form-group<#if user.hasErrors("name")> has-error</#if>">
      <label for="name">Name</label>
      <input id="name" class="form-control" type="text" name="name" value="${user.name!''}"/>
      <span class="help-block"><#if user.hasErrors("name")>${user.getErrors("name")?join(",")}</#if></span>
    </div>
