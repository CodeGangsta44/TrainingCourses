<#import "/spring.ftl" as spring/>
<div class="panel panel-default">
    <div class="panel-heading"><@spring.message "label.report.propositions"/></div>
    <div class="panel-body">
        <table class="table table-striped">
            <thead>
            <tr>
                <th><@spring.message "column.id"/></th>
                <th><@spring.message "label.topic"/></th>
                <th><@spring.message "label.speaker"/></th>
                <th><@spring.message "label.conference"/></th>
                <th><@spring.message "label.actions"/></th>
            </tr>
            </thead>
            <tbody>
            <tr ng-repeat="item in report_requests | orderBy: 'id'">
                <td>{{item.id}}</td>
                <td>{{item.topic}}</td>
                <td>{{item.speaker.surname}} {{item.speaker.name}}</td>
                <td>{{item.conference.topic}}</td>
                <td>
                    <button class="btn btn-success" style="margin-top:5px" ng-click="processRequest(item.id, true)">
                        <@spring.message "button.approve"/>
                    </button>

                    <button class="btn btn-danger" style="margin-top:5px" ng-click="processRequest(item.id, false)">
                        <@spring.message "button.reject"/>
                    </button>
                </td>
            </tr>
            </tbody>
        </table>
    </div>
</div>