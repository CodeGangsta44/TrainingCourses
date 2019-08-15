<#import "/spring.ftl" as spring/>
<div class="container" style="margin-top: 30px">
    <div style="display: flex; margin-top: 20px">
        <div style="flex: 1;  align-self:flex-start">
            <button type="button" data-toggle="modal" data-target="#avatarModal" style="background-color: transparent; border: none">
                <img ng-src="/img/{{user.avatarFileName}}" alt=""
                     style="object-fit: cover; height: 350px; width: 350px; border-radius:50%">
            </button>
        </div>

        <div class="card" style="flex: 1; align-self:flex-start">
            <div class="card-header" >
                <div style="display: inline-block"><h5><@spring.message "label.info"/></h5></div>
                <button type="button" class="btn btn-success"  data-toggle="modal" data-target="#editModal"
                        style="float: right"><@spring.message "button.edit"/></button>
            </div>
            <div class="card-body">
                <div>
                    <label id="surnameLabel"><@spring.message "label.surname"/>: {{user.surname}} </label>
                </div>

                <div>
                    <label id="nameLabel"><@spring.message "label.name"/>: {{user.name}}</label>
                </div>

                <div>
                    <label id="patronymicLabel"><@spring.message "label.patronymic"/>: {{user.patronymic}}</label>
                </div>

                <div>
                    <label id="loginLabel"><@spring.message "label.login"/>: {{user.login}}</label>
                </div>

                <div>
                    <label id="emailLabel"><@spring.message "label.email"/>: {{user.email}}</label>
                </div>
            </div>
        </div>
    </div>

    <!-- AvatarModal -->
    <div class="modal fade" id="avatarModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel"><@spring.message "label.change.photo"/></h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <input type="file"  file="file" required>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal"><@spring.message "button.close"/></button>
                    <button class="btn btn-success" ng-click="changeAvatar()">
                        <@spring.message "button.upload"/>
                    </button>
                </div>
            </div>
        </div>
    </div>

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
                               ng-model="user.surname">

                    </div>
                    <div class="form-group">
                        <label id="inputNameLabel" for="exampleInputName"><@spring.message "label.name"/></label>
                        <input type="text"
                               class="form-control"
                               id="exampleInputName"
                               placeholder="<@spring.message "label.name"/>"
                               required
                               ng-model="user.name">
                    </div>
                    <div class="form-group">
                        <label id="inputPatronymicLabel" for="exampleInputPatronymic"><@spring.message "label.patronymic"/></label>
                        <input type="text"
                               class="form-control"
                               id="exampleInputPatronymic"
                               placeholder="<@spring.message "label.patronymic"/>"
                               required
                               ng-model="user.patronymic">
                    </div>
                    <div class="form-group">
                        <label id="inputLoginLabel" for="exampleInputLogin"><@spring.message "label.login"/></label>
                        <input type="text"
                               class="form-control"
                               id="exampleInputLogin"
                               placeholder="<@spring.message "label.login"/>"
                               required
                               ng-model="user.login">
                    </div>
                    <div class="form-group">
                        <label id="inputEmailLabel" for="exampleInputEmail"><@spring.message "label.email"/></label>
                        <input type="text"
                               class="form-control"
                               id="exampleInputEmail"
                               placeholder="<@spring.message "label.email"/>"
                               required
                               ng-model="user.email">
                    </div>

                    <div class="form-group">
                        <label id="inputIsSpeakerLabel" for="exampleInputIsSpeaker"><@spring.message "label.speaker"/></label>
                        <input type="checkbox"
                               class="form-control"
                               id="exampleInputIsSpeaker"
                               placeholder="<@spring.message "label.speaker"/>"
                               ng-model="isSpeaker"
                               ng-checked="isSpeaker"
                               ng-click="toggleSpeakerSelection()">
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal"><@spring.message "button.close"/></button>
                    <button class="btn btn-success" ng-click="editProfile()">
                        <@spring.message "button.save"/>
                    </button>
                </div>
            </div>
        </div>
    </div>

    <div class="card" style="flex: 1; margin-top: 20px">
        <div class="card-header"><h4><@spring.message "label.roles"/></h4></div>
        <div class="card-body">
            <div>
                <ul style="list-style: none; padding-left: 0">
                    <li ng-repeat="role in user.roles">
                        <div class="alert alert-success" role="alert">
                            {{role}}
                        </div>
                    </li>
                </ul>

            </div>

        </div>
    </div>
</div>
