# Project 3: *Leads*


**Project Deadline:** June 23rd, 2022 (Presentation Day)
**Code Freeze**: June 21st, 2022

_**Leads**_: Calvin, Chris, Dhruv, Levi

**Sprint Times:** Every day; Weekends TBD

<br>
<hr>

### TOC:
- Daily Meetings:
    - [6/10/22](#61022)

<hr>

### Daily Meeting Format:

> ##### _NOTE_: This format is adjustable and SHOULD be adjusted based on the needs of your team. Feel free to communicate your suggestions so that you can make this meeting as efficient as possible!
<br>

1. **What did you do yesterday?**
    - brief rundown of what you accomplished prior to Sprint to update the team on where you are in regard to the project.
    - tasks completed will be tracked through issues and milestones

2. **Did you run into any issues/problems since our last meeting?**
    - (These will typically be tackled after the meeting is over).

3. **Agenda:**
    - important changes or suggestions that  need to be discussed as a team, etc..
    - Feel free to message into your team Discord/Slack/communication-platform or DM Levi for anything you want to discuss for the day's Sprint OR mention it at the start of the meeting.

4. **What do you want to accomplish by our next sprint?**
    - _*Levi will assign Issues and create milestones to each team member according to this list._
    - Milestones, individual tasks, and reminders

<hr>

### 6/10/22

**Agenda:**
- [x] *Branching: Feature -> Dev -> Main*
    - Leads will adjust their group's Pull Request targets to their feature branch. These branches will then be merged into dev when they are completed.
    - Levi:
        - [ ] Create issues for each feature.
        - [ ] Update the Branching Guidelines specifically for the Leads.

<br>

- [x] *General Structure for each Day*
    1. Non-project plans/Azhya's plans
    2. General whole batch announcements/topics that need to be covered together
    3. Individual team breakout rooms (Daily Standup + Paired Programming)
    4. Meeting with Leads (Leads Standup + Merge Party)
    5. Meet with Azhya

<br>

- [x] *General Timeline for Leafbound*
    1. CI/CD - 6/13
    2. BE Finish Deadline - 6/15
        - Merge features into DEV on 6/15
    3. BE Testing Finish + Bugs - 6/17
        - Unit tests
        - Enpoint testing using Postman
            - installed Spring Fox, so Swagger annotations will be created automatically and we can copy that script and put it into Postman to test our endpoints. As long as it's done correctly.
    4. FE Finish (6/17)
        - FE should be happening simultaneously
        - FE: Calvin, Levi, Santi, Jorge A., Markus, Michael (?)
    5. FE Testing (6/21)
    6. Presentation prep/runthrough
        - Anyone who doesn't have a task and would like to help create presentation
        - double check to update README.md, START.md's for both repos
<br>

**Tasks**
Levi:
- [ ] Create milestones
- [ ] Create features issues
- [ ] Update branching guidelines
- [ ] Create issues for Team CaLe
    - Jorge A. and Jose will use the current assignments to create issues during Git training

Chris:
- [ ] Start.md for BE

[Click here to return to TOC](#toc)
<hr>



**Dhruv**
1. Jorge A. - FE
2. Enock -repo/service
3. Santiago - FE
4. Vidya - service
5. Patrick Y. -models
6. Markus - FE

**Chris**
1. Jorge G. - BE, Model & Service
2. Jose - FE
3. Michael - FE
4. Julian - Repository
5. Joachim - Testing



6/13/22

Progress Check:


Additional Topics for tomorrow:
- Taking on Roles, Publishers, Setting, Authors tables - Levi and Calvin
- Order Details and Orders -> Dhruv
- Discussing the number of pages/forms for FE

1. [Encryption](https://medium.com/developervisits/hiding-encrypting-database-password-in-the-application-properties-34d59fe104eb)
    - Jasypt
2. CrossOrigin should not be * in controllers (security & Sonar will fail)
3. API port will be 8080 on localhost since this is defined in the settings
4. To use or not to user DTOs (SonarCloud gets angry about this too).
    - These are defined in the service layer not the controller
    - The idea is to keep the models separate from the controllers
    - DTOs are just POJOs
    - Yes, this is seemingly duplicate code, but really easy.
5. SonarCloud extension for VSCode (helpful and teaching some new things - sort of).
6. Working on the Swagger Docs tags (description is depreciated)
7. Postman can be created [api docs link](http://localhost:8080/v2/api-docs)
    - Quick demo


Agenda tomorrow:
1. Go to GitHub and make sure you're receiving email notifications for whatever you're mentioned in.
2. Make sure you have accepted the invitation to these repositories so that 1) Jorge, Jose and myself can go ahead and assign you to issues but 2) so you can access this stuff. Everyone should have been invited.
3. If you do a pull request and you need help, this is how you should do it.
4. In terms of feature branching, Dhruv and KrolPower have the power to merge directly into their feature branches and you shoul pull down from that.
5. We will prioritize your schedule to the best of our ability. If there are other things going on, please don't hesitate to let us know. On that note, we had some miscommunication on Friday that could have been handled better on our part. Our goal is to make it so that we can get as much work done as possible during work hours, but we also have to work around non-project Revature events. So in light of what happened on Friday, I just wanted to clarify. Azhya will represent Revature and Revature's schedule, and we will represent the project schedule.