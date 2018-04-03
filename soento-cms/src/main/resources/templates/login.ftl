<#import "components/spring.ftl" as spring/>
<!DOCTYPE html>
<html lang="en">
<head>
<#include "components/head.ftl">
</head>
<body class="login-layout">
<div class="main-container">
    <div class="main-content">
        <!-- MAIN CONTENT BEGINS -->
    <#include "page/${name}.ftl">
        <!-- MAIN CONTENT ENDS -->
    </div>
</div>
<#include "components/script.ftl">
</body>
</html>