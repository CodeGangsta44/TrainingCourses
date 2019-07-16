<div class="panel panel-default">
    <div class="panel-heading">Conference</div>
    <div class="panel-body">
        <h2>{{ topic }}</h2>

        <p>When: {{ eventDateTime }}</p>
        <p>Where: {{ eventAddress }}</p>

        <h3>Description</h3>
        <span> {{ description }} </span>

        <h3>Reports</h3>
        <ul>
            <li ng-repeat="report in reports"><a ng-href="/reports/{{report.id}}">{{report.topic}}</a></li>
        </ul>

        <a ng-href="#!{{id}}/addReport"> <button id="proposeReport" type="submit" class="btn btn-success" style="margin-top:30px">Propose report</button></a>

        <form method="get">
            <button id="registerToConference" type="submit" class="btn btn-success" style="margin-top:30px">{{ registrationAction }}</button>
        </form>

        <h3>Registered guests</h3>
        <ol>
            <li ng-repeat="user in registeredGuests">{{user.login}}</li>
        </ol>

    </div>
</div>