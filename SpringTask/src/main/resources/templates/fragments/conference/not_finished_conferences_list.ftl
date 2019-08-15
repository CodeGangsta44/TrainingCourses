<#import "/spring.ftl" as spring/>
<div class="panel panel-default">
    <div class="panel-heading"><@spring.message "label.not.finished.conferences"/></div>
    <div class="panel-body">
        <table class="table table-striped">
            <thead>
            <tr>
                <th><@spring.message "column.id"/></th>
                <th><@spring.message "label.topic"/></th>
                <th><@spring.message "label.date.and.time"/></th>
                <th><@spring.message "label.address"/></th>
                <th><@spring.message "label.actions"/></th>
            </tr>
            </thead>
            <tbody>
            <tr ng-repeat="item in conferences | orderBy: 'id'">
                <td>{{item.id}}</td>
                <td><a ng-href="#!{{item.id}}">{{item.topic}}</a></td>
                <td>{{item.eventDateTime}}</td>
                <td>{{item.eventAddress}}</td>

                <td>
                    <button type="button"
                            class="btn btn-secondary"
                            data-toggle="modal"
                            data-target="#finishModal"
                            ng-click="prepareForConferenceFinishing(item.id)">
                        <@spring.message "button.finish"/>
                    </button>
                </td>
            </tr>
            </tbody>
        </table>

        <!-- FinishModal -->
        <div class="modal fade" id="finishModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel"><@spring.message "label.conference.finishing"/></h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"
                                ng-click="cancelConferenceFinishing()">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <div class="form-group">
                            <label id="inputNumberOfVisitedGuestsLabel" for="exampleInputNumberOfVisitedGuests"><@spring.message "label.name"/></label>
                            <input type="text"
                                   class="form-control"
                                   id="exampleInputNumberOfVisitedGuests"
                                   placeholder="<@spring.message "label.attended.guests"/>"
                                   required
                                   ng-model="numberOfVisitedGuests">
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary"
                                data-dismiss="modal"
                                ng-click="cancelConferenceFinishing()">
                            <@spring.message "button.cancel"/>
                        </button>
                        <button class="btn btn-danger" data-dismiss="modal" ng-click="finishConference()">
                            <@spring.message "button.finish"/>
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>