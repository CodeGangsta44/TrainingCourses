<#import "/spring.ftl" as spring/>
<div class="panel panel-default">
    <div class="panel-heading"><@spring.message "table.all_users.name"/></div>
    <div class="panel-body">
        <table class="table table-striped">
            <thead>
            <tr>
                <th><@spring.message "column.id"/></th>
                <th><@spring.message "label.surname"/></th>
                <th><@spring.message "label.name"/></th>
                <th><@spring.message "label.patronymic"/></th>
                <th><@spring.message "label.login"/></th>
                <th><@spring.message "label.email"/></th>
            </tr>
            </thead>
            <tbody>
            <tr ng-repeat="item in users | orderBy:'id'">
                <td>{{item.id}}</td>
                <td>{{item.surname}}</td>
                <td>{{item.name}}</td>
                <td>{{item.patronymic}}</td>
                <td>{{item.login}}</td>
                <td>{{item.email}}</td>
            </tr>
            </tbody>
        </table>
    </div>
</div>