<#import "/spring.ftl" as spring/>
<div class="container" style="margin-top: 20px">
    <div style="display: flex">
        <nav aria-label="Page navigation example" style="flex: 1">
            <ul id="topPageButtonGroup" class="pagination form-inline" style="float: left" hidden>
                <li class="page-item previous-page-button">
                    <a class="page-link" href="" ng-click="previousPage()"><@spring.message "label.page.previous"/></a>
                </li>
                <li id="pageButton-top-{{$index + 1}}" class="page-item" ng-repeat="x in [].constructor(numberOfPages) track by $index">
                    <a class="page-link" href="" ng-click="selectPage($index + 1)">{{$index + 1}}</a>
                </li>
                <li class="page-item next-page-button">
                    <a class="page-link" href="" ng-click="nextPage()"><@spring.message "label.page.next"/></a>
                </li>
            </ul>
            <div class="form-inline" style="float: right">
                <label class="my-1 mr-2" for="TopInlineFormCustomSelect" ><@spring.message "label.items.on.page"/>: </label>
                <select class="custom-select my-1 mr-sm-2" id="TopInlineFormCustomSelect"
                        ng-model="currentCapacity"
                        ng-change="updateButtons()">
                    <option selected value="All"><@spring.message "label.items.all"/></option>
                    <option value="5">5</option>
                    <option value="10">10</option>
                    <option value="15">15</option>
                </select>
            </div>
        </nav>
    </div>
    <div class="panel-body">
        <table class="table table-striped">
            <thead>
            <tr>
                <th><@spring.message "column.id"/></th>
                <th><@spring.message "label.topic"/></th>
                <th><@spring.message "label.date.and.time"/></th>
                <th><@spring.message "label.address"/></th>
            </tr>
            </thead>
            <tbody>
            <tr ng-repeat="item in conferences | orderBy: 'id'">
                <td>{{item.id}}</td>
                <td><a ng-href="#!{{item.id}}">{{item.topic}}</a></td>
                <td>{{item.eventDateTime}}</td>
                <td>{{item.eventAddress}}</td>
            </tr>
            </tbody>
        </table>
    </div>
    <div style="display: flex">
        <nav aria-label="Page navigation example" style="flex: 1">
            <ul id="bottomPageButtonGroup" class="pagination form-inline" style="float: left" hidden>
                <li class="page-item previous-page-button">
                    <a class="page-link" href="" ng-click="previousPage()"><@spring.message "label.page.previous"/></a>
                </li>
                <li id="pageButton-bottom-{{$index + 1}}" class="page-item" ng-repeat="x in [].constructor(numberOfPages) track by $index">
                    <a class="page-link" href="" ng-click="selectPage($index + 1)">{{$index + 1}}</a>
                </li>
                <li class="page-item next-page-button">
                    <a class="page-link" href="" ng-click="nextPage()"><@spring.message "label.page.next"/></a>
                </li>
            </ul>
            <div class="form-inline" style="float: right">
                <label class="my-1 mr-2" for="TopInlineFormCustomSelect" ><@spring.message "label.items.on.page"/>: </label>
                <select class="custom-select my-1 mr-sm-2" id="BottomInlineFormCustomSelect"
                        ng-model="currentCapacity"
                        ng-change="updateButtons()">
                    <option selected value="All"><@spring.message "label.items.all"/></option>
                    <option value="5">5</option>
                    <option value="10">10</option>
                    <option value="15">15</option>
                </select>
            </div>
        </nav>
    </div>

    <button type="button"
            class="btn btn-success"
            data-toggle="modal"
            data-target="#proposeModal"
            ng-click="prepareToProposeConference()">
        <@spring.message "button.propose.conference"/>
    </button>

    <!-- ProposeModal -->
    <div class="modal fade" id="proposeModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel"><@spring.message "label.conference.proposing"/></h5>
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
                                   placeholder=<@spring.message "label.topic"/>
                                   required
                                   ng-model="form.topic">
                        </div>
                        <div class="form-group">
                            <label id="inputNameLabel" for="dateTimeInput"><@spring.message "label.date.and.time"/></label>
                            <input type="text"
                                   class="form-control"
                                   id="dateTimeInput"
                                   placeholder=<@spring.message "label.date.and.time"/>
                                   required
                                   ng-model="form.eventDateTime">
                        </div>
                        <div class="form-group">
                            <label id="inputPatronymicLabel" for="addressInput"><@spring.message "label.address"/></label>
                            <input type="text"
                                   class="form-control"
                                   id="addressInput"
                                   placeholder=<@spring.message "label.address"/>
                                   required
                                   ng-model="form.eventAddress">
                        </div>
                        <div class="form-group">
                            <label id="inputLoginLabel" for="descriptionInput"><@spring.message "label.description"/></label>
                            <input type="text"
                                   class="form-control"
                                   id="descriptionInput"
                                   placeholder=<@spring.message "label.description"/>
                                   required
                                   ng-model="form.description">
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">
                        <@spring.message "button.close"/>
                    </button>
                    <button type="submit" class="btn btn-success" ng-disabled="form.$invalid"
                            ng-click="proposeConference()"
                            data-dismiss="modal">
                        <@spring.message "button.propose.conference"/>
                    </button>
                </div>
            </div>
        </div>
    </div>
</div>