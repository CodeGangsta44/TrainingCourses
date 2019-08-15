<#import "/spring.ftl" as spring/>
<div class="panel panel-default">
    <div class="panel-heading"><@spring.message "label.finished.conferences"/></div>
    <div class="panel-body">
        <table class="table table-striped">
            <thead>
            <tr>
                <th><@spring.message "column.id"/></th>
                <th><@spring.message "label.topic"/></th>
                <th><@spring.message "label.date.and.time"/></th>
                <th><@spring.message "label.address"/></th>
                <th><@spring.message "label.percent.registered.guests"/></th>
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