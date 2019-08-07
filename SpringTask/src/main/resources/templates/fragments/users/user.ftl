<#import "/spring.ftl" as spring/>
<link rel="stylesheet" type="text/css" href="css/rating.css">
<div class="container" style="margin-top: 30px">
    <div style="display: flex">
        <div id="mainUserInformation" style="flex: 2">
            <div style="display: flex; margin-top: 20px">
                <div style="flex: 1;  align-self:flex-start">
                    <img ng-src="/img/{{user.avatarFileName}}" alt=""
                         style="object-fit: cover; height: 350px; width: 350px; border-radius:50%">
                </div>

                <div class="card" style="flex: 1; align-self:flex-start">
                    <div class="card-header" >
                        <div style="display: inline-block"><h5>Info</h5></div>
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

        <div id="additionalSpeakerInformation" style="flex: 1;" hidden>
            <div class="card" style="margin-top: 20px; margin-left: 20px;">
                <div class="card-header"><h5>Additional speaker info</h5></div>

                <div class="card-body">
                    <div id="stars" class="alert alert-success" style="margin: auto; text-align: center">
                        <a href="" class="star"
                           style="font-size: 40pt;"
                           ng-repeat="x in [].constructor(5) track by $index"
                           id="star-{{$index + 1}}"
                           ng-click="clickStar($index + 1)">★</a>
                    </div>

                    <div class="alert alert-success" style="margin-top: 20px">
                        Your mark: {{currentUserMark}}
                    </div>

                    <div class="alert alert-success" style="margin-top: 20px">
                        Average rating: {{averageRating}}
                    </div>

                    <div id="lines" class="alert alert-secondary" style="margin-top: 20px">
                        <span><b>Marks</b></span>
                        <span style="float: right"><b>Votes</b></span>
                        <ul style="list-style: none; padding-left: 0; margin-bottom: 0">
                            <li ng-repeat="x in [].constructor(5) track by $index" style="display: flex; justify-content: center; align-items: center;">
                                {{5 - $index}}
                                <div class="line"
                                     id="line-{{5 - $index}}"
                                     style="display: inline-block; width: 50%; height: 10px; background-color: #28a745; margin: auto">
                                </div>
                                <span id="line-span-{{5 - $index}}" style="float: right"></span>
                            </li>
                        </ul>
                    </div>

                    <div class="alert alert-secondary" style="margin-top: 20px">
                        Total reports: {{speakerStatistics.totalReports}}
                    </div>

                    <div class="alert alert-secondary" style="margin-top: 20px">
                        Total conferences: {{speakerStatistics.totalConferences}}
                    </div>

                    <div class="alert alert-secondary" style="margin-top: 20px">
                        Total people: {{speakerStatistics.totalPeople}}
                    </div>
                </div>

            </div>

<#--            <div id="stars">-->
<#--                <a href="" class="star"-->
<#--                      style="font-size: xx-large"-->
<#--                      ng-repeat="x in [].constructor(5) track by $index"-->
<#--                      id="star-{{$index + 1}}"-->
<#--                      ng-click="clickStar($index + 1)">★</a>-->
<#--            </div>-->

<#--            <div>-->
<#--                Average rating: {{averageRating}}-->
<#--            </div>-->

<#--            <div id="lines">-->
<#--                <ul style="list-style: none; padding-left: 0">-->
<#--                    <li ng-repeat="x in [].constructor(5) track by $index">-->
<#--                        {{5 - $index}}-->
<#--                        <div class="line"-->
<#--                             id="line-{{5 - $index}}"-->
<#--                             style="display: inline-block; width: 50%; height: 10px; background-color: #28a745; margin-top: 3px">-->
<#--                        </div>-->
<#--                        <span id="line-span-{{5 - $index}}"></span>-->
<#--                    </li>-->
<#--                </ul>-->
<#--            </div>-->

<#--            <div>-->
<#--                Total reports: {{speakerStatistics.totalReports}}-->
<#--            </div>-->

<#--            <div>-->
<#--                Total conferences: {{speakerStatistics.totalConferences}}-->
<#--            </div>-->

<#--            <div>-->
<#--                Total people: {{speakerStatistics.totalPeople}}-->
<#--            </div>-->
        </div>
    </div>
</div>