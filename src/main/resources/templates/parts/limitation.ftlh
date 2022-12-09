<#macro limitation path>

<#list user.roles as role>
<#if role == "${path}" || role == "ADMIN">

<#nested>
<#break>
</#if>
</#list>

</#macro>