<#macro functions status comment>


<#switch status>
    <#case "REJECTED">
        <div class="alert alert-danger" role="alert">
            Исправление было отклонено "${comment}".
        </div>
        <#break>
    <#case "ACCEPTED">
        <div class="alert alert-success" role="alert">
            Исправление было принято "${comment}".
        </div>
        <#break>
    <#default>

</#switch>

</#macro>

<#macro border_color status>

<#switch status>
    <#case "REJECTED">
        border-bottom-danger
        <#break>
    <#case "ACCEPTED">
        border-bottom-success
        <#break>
    <#case "PENDING">
        border-bottom-warning
        <#break>
    <#default>
        border-bottom-secondary
</#switch>

</#macro>