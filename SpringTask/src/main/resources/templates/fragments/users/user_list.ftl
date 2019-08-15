<#import "/spring.ftl" as spring/>
<link rel="stylesheet" type="text/css" href="css/user_list.css">
<div class="panel panel-default">
    <div class="panel-heading"><@spring.message "table.all_users.name"/></div>
    <div class="panel-body">
        <table class="table table-striped">
            <thead>
            <tr>
                <th><@spring.message "column.id"/></th>
                <th><@spring.message "label.photo"/></th>
                <th><@spring.message "label.surname"/></th>
                <th><@spring.message "label.name"/></th>
                <th><@spring.message "label.patronymic"/></th>
                <th><@spring.message "label.login"/></th>
                <th><@spring.message "label.email"/></th>
                <th><@spring.message "label.roles"/></th>
                <th><@spring.message "label.actions"/></th>
            </tr>
            </thead>
            <tbody>
            <tr ng-repeat="item in users | orderBy:'id'">
                <td class="align-middle">{{item.id}}</td>
                <td class="align-middle">
                    <img ng-src="/img/{{item.avatarFileName}}" alt=""
                         style="object-fit: cover; height: 50px; width: 50px; border-radius:50%">
                </td>
                <td class="align-middle">{{item.surname}}</td>
                <td class="align-middle">{{item.name}}</td>
                <td class="align-middle">{{item.patronymic}}</td>
                <td class="align-middle">{{item.login}}</td>
                <td class="align-middle">{{item.email}}</td>

                <td class="align-middle">
                    {{item.roles.join(" | ")}}
                </td>

                <td class="align-middle">
                    <div class="btn-group" role="group" aria-label="Basic example">
                        <button type="button"
                                class="btn btn-secondary"
                                data-toggle="modal"
                                data-target="#editModal"
                                ng-click="editUser(item)">
                            <@spring.message "button.edit"/>
                        </button>
                        <button type="button"
                                class="btn btn-secondary"
                                data-toggle="modal"
                                data-target="#proposeReportModal"
                                ng-click="prepareToProposeReport(item)">
                            <@spring.message "button.propose.report"/>
                        </button>
                        <button class="btn btn-danger"
                                type="button"
                                data-toggle="modal"
                                data-target="#deleteModal"
                                ng-click="prepareToDeleteUser(item.id)">
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
                        <h5 class="modal-title" id="exampleModalLabel"><@spring.message "label.edit.profile"/></h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <div class="alert alert-success" role="alert" id="resultMessage" style="visibility: hidden"></div>
                        <div class="form-group">
                            <label id="inputSurnameLabel" for="exampleInputSurname"><@spring.message "label.surname"/></label>
                            <input type="text"
                                   class="form-control"
                                   id="exampleInputSurname"
                                   placeholder="<@spring.message "label.surname"/>"
                                   required
                                   ng-model="editForm.surname">

                        </div>
                        <div class="form-group">
                            <label id="inputNameLabel" for="exampleInputName"><@spring.message "label.name"/></label>
                            <input type="text"
                                   class="form-control"
                                   id="exampleInputName"
                                   placeholder="<@spring.message "label.name"/>"
                                   required
                                   ng-model="editForm.name">
                        </div>
                        <div class="form-group">
                            <label id="inputPatronymicLabel" for="exampleInputPatronymic"><@spring.message "label.patronymic"/></label>
                            <input type="text"
                                   class="form-control"
                                   id="exampleInputPatronymic"
                                   placeholder="<@spring.message "label.patronymic"/>"
                                   required
                                   ng-model="editForm.patronymic">
                        </div>
                        <div class="form-group">
                            <label id="inputLoginLabel" for="exampleInputLogin"><@spring.message "label.login"/></label>
                            <input type="text"
                                   class="form-control"
                                   id="exampleInputLogin"
                                   placeholder="<@spring.message "label.login"/>"
                                   required
                                   ng-model="editForm.login">
                        </div>
                        <div class="form-group">
                            <label id="inputEmailLabel" for="exampleInputEmail"><@spring.message "label.email"/></label>
                            <input type="text"
                                   class="form-control"
                                   id="exampleInputEmail"
                                   placeholder="<@spring.message "label.email"/>"
                                   required
                                   ng-model="editForm.email">
                        </div>

                        <div class="form-group">
                            <label id="inputRolesLabel" for="exampleInputRoles"><@spring.message "label.roles"/></label>
                            <ul ng-repeat="role in roles" style="list-style: none; padding-left: 0">
                                <li>
                                    <input type="checkbox"
                                           name="selectedRoles[]"
                                           value="{{role}}"
                                           ng-checked="editForm.roles.indexOf(role) > -1"
                                           ng-click="toggleRoleSelection(role)"> {{role}}
                                </li>
                            </ul>
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
                                ng-click="cancelUserDeleting()">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <@spring.message "label.deleting.question"/>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary"
                                data-dismiss="modal"
                                ng-click="cancelUserDeleting()">
                            <@spring.message "button.cancel"/>
                        </button>
                        <button class="btn btn-danger" data-dismiss="modal" ng-click="deleteUser()">
                            <@spring.message "button.delete"/>
                        </button>
                    </div>
                </div>
            </div>
        </div>

        <!-- ProposeReportModal -->
        <div class="modal fade" id="proposeReportModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel"><@spring.message "label.report.proposition"/></h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"
                                ng-click="cancelUserDeleting()">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <form name="form_name" autocomplete="off" novalidate ng-submit="sendForm(form)">
                            <div class="form-group">

                                <div class="form-group">
                                    <label id="inputConferenceLabel" for="conferenceSelect"><@spring.message "label.conference"/></label>
                                    <select class="form-control"
                                            name="conferenceSelect"
                                            id="conferenceSelect"
                                            ng-model="chosenConferenceId">
                                        <option ng-repeat="conference in conferences | orderBy: id " value="{{conference.id}}">
                                            {{conference.topic}}
                                        </option>
                                    </select>
                                </div>

                                <div class="form-group">
                                    <label id="inputTopicLabel" for="topicInput"><@spring.message "label.topic"/></label>
                                    <input type="text"
                                           class="form-control"
                                           id="topicInput"
                                           placeholder="Topic"
                                           required
                                           ng-model="reportProposeForm.topic">
                                </div>
                            </div>
                        </form>

                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary"
                                data-dismiss="modal"
                                ng-click="cancelUserDeleting()">
                            <@spring.message "button.cancel"/>
                        </button>
                        <button class="btn btn-success" data-dismiss="modal" ng-click="proposeReport()">
                            <@spring.message "button.propose.report"/>
                        </button>
                    </div>
                </div>
            </div>
        </div>


    </div>
</div>