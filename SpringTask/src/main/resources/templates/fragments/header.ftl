<#import "/spring.ftl" as spring/>
<link rel="stylesheet" type="text/css" href="css/styles.css">
<nav class="navbar navbar-expand-lg navbar-light bg-light" id="header">
    <a class="navbar-brand" href="/">
        <img src="/img/logo/conf.hub.png" alt="..." width="50" height="50">
    </a>

    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link user-option" href="/users#!/me"><h6><@spring.message "label.home"/></h6><span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item user-option">
                <a class="nav-link" href="/notifications"><h6><@spring.message "label.notifications"/></h6></a>
            </li>

            <li class="nav-item dropdown user-option">
                <a class="nav-link" href="" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    <h6><@spring.message "label.conferences"/></h6>
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                    <a class="dropdown-item user-option" href="/conferences"><@spring.message "label.all.conferences"/></a>
                    <a class="dropdown-item user-option" href="/conferences#!/me"><@spring.message "label.planned.conferences"/></a>
                    <a class="dropdown-item moder-option" href="/conferences#!/notFinished"><@spring.message "label.not.finished.conferences"/></a>
                    <a class="dropdown-item moder-option" href="/conferences#!/finished"><@spring.message "label.finished.conferences"/></a>
                    <a class="dropdown-item moder-option" href="/conferences#!/requests"><@spring.message "label.conferences.requests"/></a>
                </div>
            </li>

            <li class="nav-item dropdown speaker-option moder-option">
                <a class="nav-link" href="" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    <h6><@spring.message "label.reports"/></h6>
                </a>
                <div class="dropdown-menu " aria-labelledby="navbarDropdown">
                    <a class="dropdown-item moder-option" href="/reports#!/"><@spring.message "label.all.reports"/></a>
                    <a class="dropdown-item speaker-option" href="/reports#!/me"><@spring.message "label.planned.reports"/></a>
                    <a class="dropdown-item speaker-option" href="/reportRequests#!/me"><@spring.message "label.report.propositions"/></a>
                    <a class="dropdown-item moder-option" href="/reportRequests"><@spring.message "label.report.requests"/></a>
                </div>
            </li>

            <li class="nav-item admin-option">
                <a class="nav-link" href="/users"><h6><@spring.message "label.all.users"/></h6></a>
            </li>


        </ul>

        <span class="user-option" aria-hidden="true" style="margin-left: 5px; float: right">
            <a href="/logout"><@spring.message "label.logout"/></a>
        </span>
    </div>
</nav>
<script type="text/javascript" src="/js/header.js"></script>