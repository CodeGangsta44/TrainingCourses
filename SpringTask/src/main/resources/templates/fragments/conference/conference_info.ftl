<#import "/spring.ftl" as spring/>
<div class="panel panel-default">
    <div class="panel-body">
        <div class="card" style="flex: 1; align-self:flex-start; margin-top: 20px">
            <div class="card-header"><h2>{{ topic }}</h2></div>
            <div class="card-body">
                <div style="display: flex; margin-top: 20px">

                    <div class="card" style="flex: 1; margin-right: 10px">
                        <div class="card-header"><h5><@spring.message "label.info"/></h5></div>
                        <div class="card-body">
                            <p><@spring.message "label.when"/>: {{ eventDateTime }}</p>
                            <p><@spring.message "label.where"/>: {{ eventAddress }}</p>
                        </div>
                    </div>

                    <div class="card" style="flex: 1; margin-left: 10px; margin-right: 10px">
                        <div class="card-header"><h5><@spring.message "label.reports"/></h5></div>
                        <div class="card-body">
                            <ul>
                                <li ng-repeat="report in reports">
                                    {{report.topic}} -
                                    <a href="/users#!/{{report.speaker.id}}">
                                        {{report.speaker.name}} {{report.speaker.surname}}
                                    </a>
                                </li>
                            </ul>
                            <button type="button"
                                    class="btn btn-success"
                                    data-toggle="modal"
                                    data-target="#proposeModal"
                                    ng-click="prepareToProposeReport()">
                                <@spring.message "button.propose.report"/>
                            </button>
                        </div>
                    </div>

                    <div class="card" style="flex: 1; margin-left: 10px">
                        <div class="card-header"><h5><@spring.message "label.description"/></h5></div>
                        <div class="card-body">
                            <span> {{ description }} </span>
                        </div>
                    </div>
                </div>

                <form method="get">
                    <button id="registerToConference" type="submit" class="btn btn-success" style="margin-top:20px">{{ registrationAction }}</button>
                </form>

            </div>
            <div class="card-footer">
                <div class="card">
                    <div class="card-header"><h5><@spring.message "label.registered.guests"/></h5></div>
                    <div class="card-body">
                        <ol>
                            <li ng-repeat="user in registeredGuests">{{user.login}}</li>
                        </ol>
                    </div>
                </div>
            </div>
        </div>


        <div class="modal fade" id="proposeModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel"><@spring.message "label.report.proposition"/></h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <form style="margin-bottom: 30px" name="form_name" autocomplete="off" novalidate ng-submit="sendForm(form)">
                            <div class="form-group">
                                <label id="inputTopicLabel" for="topicInput"><@spring.message "label.topic"/></label>
                                <input type="text"
                                       class="form-control"
                                       id="topicInput"
                                       placeholder="Topic"
                                       required
                                       ng-model="form.topic">
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">
                            <@spring.message "button.close"/>
                        </button>
                        <button type="submit" class="btn btn-success" ng-disabled="form.$invalid"
                                ng-click="proposeReport()"
                                data-dismiss="modal">
                            <@spring.message "button.propose.report"/>
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>