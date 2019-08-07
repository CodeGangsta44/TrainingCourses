<#import "/spring.ftl" as spring/>
<div class="panel panel-default">
    <div class="panel-heading">Report requests</div>
    <div class="panel-body">
        <table class="table table-striped">
            <thead>
            <tr>
                <th>ID</th>
                <th>Topic</th>
                <th>Speaker</th>
                <th>Conference</th>
                <th>Actions</th>
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
                        Approve
                    </button>

                    <button class="btn btn-danger" style="margin-top:5px" ng-click="processRequest(item.id, false)">
                        Reject
                    </button>
                </td>
            </tr>
            </tbody>
        </table>
    </div>
</div>