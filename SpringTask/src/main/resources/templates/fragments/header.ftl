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
                <a class="nav-link" href="/home"><h6><@spring.message "label.home"/></h6><span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/notifications"><h6><@spring.message "label.notifications"/></h6></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/conferences"><h6><@spring.message "label.conferences"/></h6></a>
            </li>
        </ul>

        <span class="glyphicon glyphicon-log-out" aria-hidden="true" style="margin-left: 5px">
            <a href="/logout"><@spring.message "label.logout"/></a>
        </span>
    </div>
</nav>