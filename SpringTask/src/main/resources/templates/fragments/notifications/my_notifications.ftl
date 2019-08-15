<#import "/spring.ftl" as spring/>
<div class="panel panel-default">
    <div class="panel-heading"><@spring.message "label.notifications"/></div>
    <div class="panel-body">
        <ul style="list-style: none; padding-left: 0">
            <li ng-repeat="item in notifications | orderBy: 'notificationDateTime'">
                <div class="alert alert-success" role="alert">
                    <div style="display: inline-block">
                        <h4 class="alert-heading">{{item.topic}}</h4>
                    </div>
                    <div style="float: right">
                        <button type="button"
                                class="btn btn-secondary"
                                style="float: right"
                                ng-click="deleteNotification(item)">
                            <@spring.message "label.delete"/>
                        </button>
                    </div>

                    <p>{{item.notificationDateTime}}</p>
                    <hr>
                    <p class="mb-0">{{item.message}}</p>
                </div>
            </li>
        </ul>
    </div>
</div>