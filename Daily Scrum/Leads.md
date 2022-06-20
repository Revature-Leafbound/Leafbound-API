# Project 3: _Leads_

**Project Deadline:** June 23rd, 2022 (Presentation Day)
**Code Freeze**: June 21st, 2022

_**Leads**_: Calvin, Chris, Dhruv, Levi

**Sprint Times:** Every day; Weekends TBD

<br>
<hr>

### TOC:

- Daily Meetings:
  - [6/10/22](#61022)
  - [6/13/22](#61322)
  - [6/14/22](#meeting-61422)

<hr>

### Daily Meeting Format:

> ##### _NOTE_: This format is adjustable and SHOULD be adjusted based on the needs of your team. Feel free to communicate your suggestions so that you can make this meeting as efficient as possible!
>
> <br>

1. **What did you do yesterday?**

   - brief rundown of what you accomplished prior to Sprint to update the team on where you are in regard to the project.
   - tasks completed will be tracked through issues and milestones

2. **Did you run into any issues/problems since our last meeting?**

   - (These will typically be tackled after the meeting is over).

3. **Agenda:**

   - important changes or suggestions that need to be discussed as a team, etc..
   - Feel free to message into your team Discord/Slack/communication-platform or DM Levi for anything you want to discuss for the day's Sprint OR mention it at the start of the meeting.

4. **What do you want to accomplish by our next sprint?**
   - _\*Levi will assign Issues and create milestones to each team member according to this list._
   - Milestones, individual tasks, and reminders

<hr>

### 6/10/22

**Agenda:**

- [x] _Branching: Feature -> Dev -> Main_
  - Leads will adjust their group's Pull Request targets to their feature branch. These branches will then be merged into dev when they are completed.
  - Levi:
    - [ ] Create issues for each feature.
    - [ ] Update the Branching Guidelines specifically for the Leads.

<br>

- [x] _General Structure for each Day_
  1. Non-project plans/Azhya's plans
  2. General whole batch announcements/topics that need to be covered together
  3. Individual team breakout rooms (Daily Standup + Paired Programming)
  4. Meeting with Leads (Leads Standup + Merge Party)
  5. Meet with Azhya

<br>

- [x] _General Timeline for Leafbound_ 1. CI/CD - 6/13 2. BE Finish Deadline - 6/15 - Merge features into DEV on 6/15 3. BE Testing Finish + Bugs - 6/17 - Unit tests - Enpoint testing using Postman - installed Spring Fox, so Swagger annotations will be created automatically and we can copy that script and put it into Postman to test our endpoints. As long as it's done correctly. 4. FE Finish (6/17) - FE should be happening simultaneously - FE: Calvin, Levi, Santi, Jorge A., Markus, Michael (?) 5. FE Testing (6/21) 6. Presentation prep/runthrough - Anyone who doesn't have a task and would like to help create presentation - double check to update README.md, START.md's for both repos
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

<hr>

### 6/13/22

**Progress Check:**
Chris:

- models, repository, and service layers are done for products
- decided not to implement the author name and publisher as separate tables
  - wouldn't be difficult to connect them, but to meet basic product goals, took them off
- BE should be done by the end of today or tomorrow
- Jose updated everyone on branching; issues have been created and pull requests have been made

Dhruv:

- models, repository, and service layers have been done
- currently working on controllers
- FE - Markus is working on carts; he currently has the bare bones set up
  - wanted to start a pull request but Dhruv asked him to pause on it and test it with mock data for now
  - had a basic layout of the cart; Dhruv will check his progress again tomorrow, if there is none, Jorge will take lead on that
- FE: Jorge A and Santi are working on Checkout
  - bare bones also created so far
- Testing should be happening soon (most likely tomorrow)

Calvin:

- Model, repository, service (basic version) -> done
  - login and register for the service layer + validation still needs to be completed
  - JWT also needs to be added in
  - more of the Service needs to be done
- Controller in progress (basic version almost done)
- JWT stuff will be completed most likely by tomorrow
- FE is not started, but we will work on those in our own time

Levi:

-

Overall:

- Style Guide meeting didn't happen today. Most likely going to happen tomorrow in the morning

Agenda:

- Taking on Roles and Setting tables - Levi and Calvin
- Order Details and Orders -> Dhruv
- Discussing the number of pages/forms for FE (Site Map)
  - Navigation bar based on the state of the application
  - Login
  - Register
  - Home: View Products (page with a preset amount of products, and once that works, we can add stretch goals)
  - Cart
  - Checkout
  - Past Orders
  - Profile (basic) -> so that we can View Past Orders
- Ideas/Stretch Goals:
  - Cookies
  - Help/FAQ page
  - Contact page - link with emails, etc.
- [Encryption](https://medium.com/developervisits/hiding-encrypting-database-password-in-the-application-properties-34d59fe104eb)
  - Jasypt
  - application.properties is set up right now so that it can easily be hacked.
  - we can prevent this by using environment variables via AWS (need to attempt this before we try it)
- CrossOrigin should not be "\*" in controllers (security & Sonar will fail)
  - it should be "http://localhost:8080"
- API port will be 8080 on localhost since this is defined in the settings
  - BE 8080
  - FE 5000
- To use or not to user DTOs (SonarCloud gets angry about this too).
  - These are defined in the service layer not the controller
  - The idea is to keep the models separate from the controllers
  - DTOs are just POJOs
  - Yes, this is seemingly duplicate code, but really easy.
  - only benefit for us is getting exposure to it
  - overall consensus: Calvin will just show a quick demo for the sake of exposure
  - https://github.com/Revature-Leafbound/Leafbound-API/security/code-scanning/4
    - will try to remove the security check for it if possible
    - calvin will try to figure it out tonight, but this may mean that peeps need to put it in themselves, etc.
- SonarCloud extension for VSCode (helpful and teaching some new things - sort of) = SonarLint
  - to test stuff out even before pushing it and creating a pull request
  - JPA, if you're doing CRUD methods, you don't need to do anything in your repository layer
    1. overriding method
    2. using the isPresent check
    3. throwing an error if not present
- Working on the Swagger Docs 2.0 tags (description is depreciated)
  - use "tags" not "descriptions"
- Postman can be created [api docs link](http://localhost:8080/v2/api-docs)
  - Quick demo
- FE authorization - research it; Azhya will look up a demo to send to us
- Client Messages vs Status Codes for HTTP Responses
  - String messages or actual HTTP responses?
  - shouldn't have 500 errors as long as user stories are correct
  - User will do HTTP responses -> If User is done, Chris and Dhruv can look through the code to learn it
- Code smell
  - SonarCloud showing things that we don't want to show, esp during the presentation
  - we should have a threshold for warnings - get the code working first and then come back to fix those later
  - don't want code smells to begin with but warnings can wait, security issues should be fixed immediately
- Validation (business logic)
  - Create a Utility class for it
  - password -- 8 characters long, etc.
  - emails need to be unique
- Pathing for API: Queries vs Path : **Path**
- Branching and keeping track of features

<hr>

### Meeting 6/14/22

**Progress Check**
Calvin:

- Style Guide Meeting
  - **Logo Decision:** Patrick's design, incorporating Calvin's idea of having a logo without words
    - Patrick will finish this up; Adam is planning to finish his design and will present it tomorrow so we can vote again
  - **Color Palette** Adam's
  - Incoporating MUI
  - Roboto
- Updated User to not include Settings table - there's no security to it; it's a good user experience
- Service & JWT
- UserDTO implemented (Service and Controller layer will take care of that)
  - you will be logged in after registering; no additional checks to be logged into the site

Dhruv:

- BE: Finished Carts (Patrick just needs to push it)
- Enock will be working on Carts testing
- BE: Orders
  - Everything is done except for controllers (Jorge has been assigned)
  - once this is finished, Dhruv will assign Enock (if finished today) or himself for testing
- FE: Markus and Santi will push code
  - Markus needs to change his code from JS to Angular

Jose:

- Finished Products BE
- Looking into testing
- Client Message Utils - need to be able to send products back
  - So far, from what we've been taught, we've been returning strings
  - how do we test returning an actual item? -> Render some dummy data and you could also implement an H2 database to use in your unit tests
- FE: Michael has started looking at it again, and Jose will also jump on that as well
- Jorge G. is succeeding Jose (potentially FE testing, but BE for now)

**Agenda:**

- Welcome Jose!
- Environment variables:
  - export Leafbound_DB_URL=jdbc:postgresql://localhost:5432/postgres
  - export Leafbound_DB_User=postgres
  - export Leafbound_DB_Password=postgres
  - export Leafbound_JWT_Secret=iyvbunoimokg[20upo8yi7gyvhbjknlm[p-=o0987ytfghvbjkgjhoi
  - there should be a way for us to put this into AWS directly (into production) and not change anything in our code
- Has material UI - Santiago can do a pull request straight to dev to add MUI for everyone(?)
- Make sure everyone is good on pulling down FE repo, running `npm install`
- Products: Creating the thumbnail
  - in terms of scrolling, displaying, etc. team leads can decide together
  - one big list scroll up and down vs. ribbons
  - filter list, or automatically populate on one genre, etc.
  - main page with top sellers, etc.
- FE issues: Site Map
  - Navigation bar based on the state of the application - Levi
  - Login - Levi
  - Register - Calvin
  - Home: View Products (page with a preset amount of products, and once that works, we can add stretch goals) - Jose & Michael
  - Cart - Markus
  - Checkout - Santiago
  - Past Orders/Transactions - select all by ID for orders table - Jose
  - Profile (basic) -> so that we can View Past Orders - Calvin/Patrick Miller
- Tables:
  - User - Calvin
  - Products - Jose
  - Cart - Dhruv
  - Orders - Dhruv
  - Order Details - Calvin
  - Roles - Levi

**Tasks:**

- [Dhruv]: Can Santiago do a pull request straight to dev to add MUI for everyone
- Make sure everyone is good on pulling down FE repo, running `npm install`

<hr>

### 6/16/22

**Progress Check:**
Jose:

- waiting on how to set up the database so they can go ahead and grab information from it
- finished quasi-FE. Generates from a local array of products (Currently grabbing statically, but needs to know where to grab all that info from)

Dhruv:

- Cart table testing is finished for Controller and Service
- Orders testing is in progress
- Checkout page is complete, nav bar needs improvement, cart page is improvement (Markus is working on)
  - currently Markus doesn't have power so it's on pause

Calvin:

- BE Testing - Yugal (UserRole); Adam (User)
- Patrick: User Profile
- Calvin and Levi are on the same page
- Jacob will be doind endpoint testing

**Agenda items:**

- Endpoint testing assignments:
  - User: Jacob
  - UserRole: Calvin
  - Products: Jorge G.
  - Cart: Jorge A.
  - Orders: Vidya
  - Order Details: Chris(?)

<hr>

Staging manager: jennifer.heermance@revature.com

Dhruv:
Who will be here on the 23rd:
Markus: by today - Cart
Santi: Nav & Checkout FE:
Patrick: Available (Registration FE)
Enock & Jorge A: Order Unit Testing =>
Jorge A: Cart Endpoint Testing
Vidya: Order Endpoints

CaLe:
Yugal - your tests running
Adam - User Service tests almost done; User Controller tests
Patrick - Profile (Login) + JWT header
Jacob: today

Jose:
Joachim - product testing; angular (jasmine and karma tests (FE testing))
Julian - Checkout FE Tests
Michael -
Jorge - getting all the stuff from the APIs
Chris - Endpoint testing

FE testing:
Checkout - Julian
Login -
Registration -
Profile -
Cart -
Products -

Presentation Slides: Yugal
