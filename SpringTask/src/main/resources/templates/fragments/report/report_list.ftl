<#import "/spring.ftl" as spring/>
<div class="panel panel-default">
    <div class="panel-heading"><@spring.message "label.reports"/></div>
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
            <tr ng-repeat="report in reports | orderBy: 'id'">
                <td>{{report.id}}</td>
                <td>{{report.topic}}</td>
                <td>{{report.speaker.surname}} {{report.speaker.name}}</td>
                <td>{{report.conference.topic}}</td>

                <td class="align-middle">
                    <div class="btn-group" role="group" aria-label="Basic example">
                        <button type="button"
                                class="btn btn-secondary"
                                data-toggle="modal"
                                data-target="#editModal"
                                ng-click="editReport(report)">
                            <@spring.message "button.edit"/>
                        </button>
                        <button class="btn btn-danger"
                                type="button"
                                data-toggle="modal"
                                data-target="#deleteModal"
                                ng-click="prepareToDeleteReport(report.id)">
                            <@spring.message "button.delete"/>
                        </button>
                    </div>
                </td>
            </tr>
            </tbody>
        </table>

        <!-- EditModal -->
        <div class="modal fade" id="editModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel"><@spring.message "label.edit.report"/></h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <div class="alert alert-success" role="alert" id="resultMessage" style="visibility: hidden"></div>
                        <div class="form-group">
                            <label id="inputSurnameLabel" for="exampleInputSurname"><@spring.message "label.topic"/></label>
                            <input type="text"
                                   class="form-control"
                                   id="exampleInputSurname"
                                   placeholder="<@spring.message "label.topic"/>"
                                   required
                                   ng-model="editForm.topic">

                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">
                            <@spring.message "button.close"/>
                        </button>
                        <button class="btn btn-success" ng-click="saveChanges()">
                            <@spring.message "button.save"/>
                        </button>
                    </div>
                </div>
            </div>
        </div>

        <!-- DeleteModal -->
        <div class="modal fade" id="deleteModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel"><@spring.message "label.confirm.deleting"/></h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"
                                ng-click="cancelReportDeleting()">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <@spring.message "label.deleting.question"/>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary"
                                data-dismiss="modal"
                                ng-click="cancelReportDeleting()">
                            <@spring.message "button.cancel"/>
                        </button>
                        <button class="btn btn-danger" data-dismiss="modal" ng-click="deleteReport()">
                            <@spring.message "button.delete"/>
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>