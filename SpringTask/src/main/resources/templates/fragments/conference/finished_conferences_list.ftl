<#import "/spring.ftl" as spring/>
<div class="panel panel-default">
    <div class="panel-heading">Conferences</div>
    <div class="panel-body">
        <table class="table table-striped">
            <thead>
            <tr>
                <th>ID</th>
                <th>Topic</th>
                <th>Date and Time</th>
                <th>Address</th>
                <th>Percent of registered people</th>
            </tr>
            </thead>
            <tbody>
            <tr ng-repeat="item in conferences | orderBy: 'id'">
                <td>{{item.id}}</td>
                <td><a ng-href="#!{{item.id}}">{{item.topic}}</a></td>
                <td>{{item.eventDateTime}}</td>
                <td>{{item.eventAddress}}</td>
                <td>{{item.numberOfVisitedGuests / item.numberOfRegisteredGuests * 100 + '%'}}</td>
            </tr>
            </tbody>
        </table>
    </div>
</div>