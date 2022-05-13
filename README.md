# Blueno Bills

## How to Run Our App
### IMPORTANT: For SQL connections to work, change “version” in the “pom.xml” file to:
- **M1 Chip:** 3.34.0
- **No M1 Chip:** 3.30.1
- *Not sure if these are still relevant at the moment*
### BackEnd
- In the "backend" folder with the "pom.xml" file, type `mvn package`.
- Then, type `./run --gui`. This will run our REPL and also run the web scraper componenet that loads a Chrome Web Page to scrape menus. (This might take awhile to load!)
- Then, type in a terminal window`load-database ../data/StudentData.sqlite3`. This will load our database of students.

### FrontEnd
- In the "frontend" folder, type `cd blueno-bills`.
- Then, install our dependencies and imports by typing:
  - `npm install react-icons`
  - `npm install react-hook-form`
  - `npm install react-router`
  - `npm install react-router-dom`
  - `npm install styled-components`
  - `npm install firebase`
- Then, type `npm start` to load our app.
- From there, follow the descriptions on the page and buttons.

## Section 1: Introduction
### 1.1 Project Specific Details

**Members and Roles**
- **Josh Benzon (jbenzon):** Main FrontEnd + Backend Data Visualization (including Web Sockets, Web Scraping, Authentication visualization)
- **Jillian Dominguez (jdoming8):** SQL Database + FrontEnd/BackEnd Integration (Transfer Requests and Table Visualization)
- **Shivani Mendiratta (smendir1):** Web Sockets
- **Sarah George (sgeorge8):** Web Scraping + API for webscraping data
- **Guadalupe Cantera (gcantera):** Authentication

**Estimated Total Time:** 50 Hours

### 1.2 Purpose
**What problem does your project try to solve? Specify how you gathered knowledge about the problem (e.g., user interviews, scholarly articles, the news, personal experience, etc.)**
- At Brown, many students are sometimes left with unused Meal Credits at the end of the week. Prior to the semester, each student chooses the amount of maximum Meal Credits they want to use for each week (for example, 14 or 20 Meal Credits). Every week (usually early Friday morning), each student’s Meal Credits amount gets reset to their maximum Meal Credits, regardless of the amount of leftover Meal Credits. Furthermore, our project tries to solve the problem of delegating unused Meal Credits to other students who have no Meal Credits left and need one before the Meal Credits get reset. Additionally, we can also transfer Flex Points and Bear Bucks. We gathered knowledge about our problems from both our personal experience, but also other students’ experiences.

**Who does this problem affect? How? Be specific. Is it an occupation, an age group, a nationality, a racial group? A group of your friends? You, personally? It will likely be a combination of categories. (Note: this group may align more or less directly with your intended user.)**
- This problem impacts Brown University students, especially underclassmen who have mandatory meal plans. In general, this problem can be applied to more general university and college students who are in a similar meal plan system.
- This problem also affects Brown University students off meal-plan who may sometimes wish to use a Brown dining hall and would prefer using a friend’s extra meal swipe over paying in cash. Additionally, the payment type for Bear Bucks is used throughout each grade, and Bear Bucks are most often used for paying for laundry in Brown dorms.

**Why and how does your project help solve this problem? List at least one alternative solution, whether software-based or not. Why not choose that approach? Valid reasons include your specific skill set, relative cost, greater efficiency, etc. Broadly, how will the project add value? (Examples: makes a process more efficient, accurate, and/or reliable; expands access to a good or service; brings people pleasure/fun)**
- Our project helps solve this problem of delegating unused meal swipes by finding students who are willing to give or donate their own meal swipes to students who need them the most. We can utilize a “k-nearest neighbors” strategy that sorts students based on different criteria, such as the amount of meal swipes left for each student. This process makes allocating meal swipes more efficient, as it doesn’t let unused meal swipes go to waste.
- Alternatively, one other solution to this problem is swiping for someone else’s meal. Meaning, if a friend has no meal swipes, rather than transferring it to them electronically you could physically give them your own Brown ID for them to swipe. This solution is not ideal because it requires you to be physically present when your friend is swiping, and you can’t expect to be with your friend at every meal time. Our project will allow you to just be able to send swipes electronically so you and your friend do not have to be together.

**Why choose to solve this problem over others? You are choosing to spend your limited time on this project, thereby choosing not to work on others. Why? What is your motivation for choosing this project? “General interest” is not a valid reason. Instead, share why this problem interests you. (Examples: personal experiences; the problem affects a lot of people; the problem affects a particularly vulnerable group; no one has worked on this issue before; profitability of this area)**
- Choosing the problem over others allows many university students (not just Brown students) to make sure that they are able to get food from different locations that allow meal swipes. Not many people have thought about a solution to delegate unused resources, such as Meal Swipes and Flex Points (which are non transferable between each academic year). From both personal and students’ experiences, many students are not able to get food, because they have no more meal swipes. This is a huge concern to both academic and nutritional health, especially at huge institutions.
- Meal plans are quite expensive at Brown, and those who are on it have expressed that it is cheaper to buy food from Thayer than it is to use at Brown dining halls. However, dining halls such as the Ratty, V-Dub, etc are key places for socializing and require a meal swipe for entrance, so those off of meal plan or with not enough swipes would not even be able to enter these dining halls. Therefore, transferring swipes will allow those who cannot afford to be on meal plan access to key social spaces at Brown.

### 1.3 Intended Audience and Intended Use
**Who are your intended end-users? (Obviously, if your app is public, some people outside your intended audience may use it. Try to address that concern in this section.)**
- Brown students who are on a meal plan, interested in using their meal credits and flex points more efficiently. The app should only be able to used by students who have Brown credentials.

**How will the app fit into the lives of your users? How often will they use it? Is it for personal use or professional use?**
- This will probably be used daily.
  - Example: you want to help a friend swipe into a dining hall when they’ve run out of swipes for the hour/day.
  - Example: you run out of flex points to use at the b&h cafe and a friend offers to transfer their points to you
- Personal Use

### 1.4 External Stakeholders
**Stakeholders:**
- Brown Admin
- BDS Staff
- GET app people

**For each of the groups, how might they respond to your project?**
- Brown Admin - be mad because decreasing revenue
- BDS Staff - overwhelmed from having to deal with swipe transfers etc and adjusting to the new system
- GET app people - overwhelmed to deal with transfer changes and that people are using the app

**For stakeholders who might oppose or be harmed by your project, how and why? How can you mitigate harm and conflict?**
- Brown Admin - could receive compensation for the number of students using the app maybe? don’t know where it would come from
- BDS Staff - increase wages to support increased struggles

**Are there ways you can compensate external stakeholders you interview or who otherwise assist you?**
- Let them provide input on regulations and use

### 1.5 Scope and User Stories
**What are you not planning on implementing? What are some features you’ve thought of that are outside your project scope? You can always implement these if you have time, but set them aside for now. At least you’ve written them down, and can come back to them later.
- We do not intend on implementing a live map feature where a user can locate other users who are willing to give away the types of Brown payments.
- We also do not intend on adding a feature where a user can add funds from personal bank accounts (cash, credit, debit, etc.) directly on our app.
- We also do not intend on adding complex features to students, such as getting the distance between the current user’s location and a selected student’s location.
- We also do not intend on creating profiles for each student that may list certain information, such as grade, amount of meal swipes, amount of bear bucks, etc.
- We also do not intend on implementing an “undo” feature after transferring or requesting any funds.

**What are your app's user stories? (Avoid going into detail. Instead, list things like “user can create and log into a personal account” or “user can search for and save recipes.”)**
- User can transfer/request Blueno Bills to any current student on campus
- User can sort visually based on amount of meal swipes, flex points, and bear bucks available
- User can see the menus of different dining halls based on the time of day
- User can see who’s currently active on the web application in order to request/transfer payments
- User can interact visually on a web application and have privacy policy and security measures noted

### 1.6 Definitions and Acronyms
- **Active Users:** In terms of our application, active users are student who have logged into Blueno Bills and are attempting to receive or transfer funds
- **Bear Bucks:** Like flex points that can be used to buy certain items and do laundry
- **Blueno Bills:** The name of our App. This is where current students are able to transfer funds used at Brown facilities (such as meal credits, flex points, etc) to other Brown University students
- **Flex Points:** Like meal credits, Flex points can be used in place of cash at any of Brown’s Dining Services!
- **Meal Credits:** A meal credit is how a student can pay to eat on campus. A meal credit is priced at about $9 and can be used every time you swipe into one of the Dining Halls (V-Dub, Ratty, Blue Room, Andrew's Commons, the Ivy Room, and Jo's)!
- **Menu:** Description of foods and drinks
- **Request:** Ability to receive some of another student’s payment
- **Selenium:** Is responsible for automating web browsers
- **Transfer:** Ability to give some of your payment to another student
- **Web Scraping:** A form of extracting a website’s data 8
- **Web Sockets:** A type of API that allows for a browser to connect with a server.

## Section 2: Overall Description
### 2.1 User Needs
**What are the most important tasks your users have to perform in the context of your product? (E.g., for my recipe app, maybe it’s finding an appropriate recipe based on ingredients and finding time to cook)**
- Buy food with meal credits and flex points

**What are the biggest gaps in current tools used by your users? (E.g., cookbooks don’t have sophisticated search functionality)**
- Students have no way of sharing swipes and points with other students

**What general feelings do users express about the problem you’re working on? (E.g., people express frustration at how long it takes to find an appropriate recipe)**
- They feel that it would be a more effective use of their money, as they often have extra meal swipes when the year ends.

**How often do users perform tasks in the context of your project (i.e., how often will they use the product)?**
- Multiple times a day

**In what context will users interact with your product? At work? At home? In school? Some combination?**
- On campus

**Are there secondary users? Do they have different needs? (E.g., makers of medical devices need to know the needs of doctors as well as the needs of their patients)**
- No

**Separate from user research: Can you think of any ways your app might create user needs that didn’t exist before? (E.g., the addictiveness of many social media apps creates a “need” that didn’t exist before)**
- Students might experience confusion with staff within dining halls regarding how the transfer system works

### 2.2 Assumptions and Dependencies
**What software and other technology does your project rely on?**
- React, Selenium, SQL, Intellij, VSCode, Firebase

**What non-technical dependencies does your project rely on? Are you relying on the legality of collecting data from your users?**
- We are collecting the following data: The amount of meal swipes a student has, the amount of flex points a student has, the amount of flex points a student has, whether a user is online

**Does the relevance of your app rely on a social and/or cultural context? (E.g., a Squid Game-themed app)**
- Social Context: Being a Brown Student 

**What normative assumptions are you making? Are you choosing to center a particular group over others? Why?**
- We are centering Brown Students since as students at Brown we have noticed many complications with the meal plan where often some Students have a surplus of meal swipes and not enough flex points. Students can’t transfer meal swipes so if you end the semester with extra meal swipes that money you paid for meal plan goes to waste. There are also Students who are on meal plans where the weekly meal swipes reset so for these students having friends who could transfer meal swipes when they run out of their weekly meal swipes would be helpful. 

**What assumptions are you making in claiming that your app adds value to people’s lives?**
- We are assuming that students would like to have access to more meal swipes and flex points

**Based on the dependencies enumerated above, what are the financial needs for development of your project (broadly speaking)? You don’t need exact numbers, especially for the purposes of this class, but are there services or technologies you’ll need to pay for? You shouldn’t have to pay for anything in this class, but maybe there are external APIs that have a paid tier you’d like to use eventually after the class is over.**
- Firebase
- Usage of Brown actual GET App

## Section 3: System Features and Requirements
### 3.1 Risks
#### *Stakeholder Risks*
**How might data used for decision-making within your app result in unfair outcomes for certain users or stakeholders?**
- Certain users might make less “friends” and receive more or less requests/transfers but this is not a harm that can really be mitigated.
- BDS might have a hard time adjusting to the new system

**Which features of the represented groups do your app’s data not represent?**
- Race, gender, sexuality, most identities and features honestly. We are only really collecting a username and info about meal credits/points/etc

**What limitations exist on stakeholders’ ability to manipulate the data relevant to them?**
- They can only input or edit their meal credits/points/bear bucks

**Does the app benefit one group of stakeholders more than others?**
- It benefits all Brown student users but not admin stakeholders

**Which data are publicly accessible?**
- Dining hall menus

**Is data ever harvested or used without stakeholder consent?**
- No

**Are there situations when a stakeholder cannot inspect how your app came to a decision relevant to them?**
- No, all transfers are mutually approved

**Does your app disrupt community wellbeing in any way, such as undermining trust, communication, or participation?**
- Nope! Does the opposite

**Does your app make inefficient or unnecessary uses of resources and/or energy?**
- Since we can’t use the GET info directly (Brown won’t let us), students will manually have to input and update their meal points and credits for the time-being. This would be resolved in a future sprint!

#### *External Risks*
**How does your app fit into its social, economic, and political context? Does it actively work against systems of oppression (racism, sexism, classism) How?**
- It doesn’t work against systems of oppression but it does increase food security for students regardless of identity or economic status.

**Does your app rely on services that participate in labor exploitation or create environmental harms?**
- No!

### 3.2 Data Requirements
**If you only plan on storing this datum, not using it, why?**
- We will be unable to get access to Brown’s actual data behind the information stored in the GET App, due to it being a secured server, students will be required to store their data by hand when they first download our app and from there, our app is responsible for keeping track of the different transactions.

**If you plan on using this datum, what function does it fulfill?**
- This will let the user know what their transaction history is, how many meal credits, points, etc. they have left, and who they have sent/received funds from

**What process of consent, if any, do you provide your user when collecting this data from them? If you don’t provide one, why?**
- When a user registers with our system, they will first have to create a username and password (for added security) and before they create an account, we can include a statement that explains it is their responsibility to ensure that the data they have inputted is correct so that we have an accurate dataset to execute our application successfully.

### 3.3 System Features
**What high-level modules define the functionality of your app?**
- Requesting, Transferring, viewing menus and your account, seeing other active users

**How will you separate your code into these separate modules?**
- We separated our code into its frontend and backend aspects. From the names themselves, our backend folder contained our src folder with the bulk of our code and our frontend holds the overall aesthetic code for our app. 

**How will modules communicate with each other?**
- The modules communicate with each other via spark server connections, websockets, etc. 

**How will you make modules reusable and generic when necessary?**
- If a user regularly sends funds to another specific user, we can implement a “suggestion” button that autofills given previous transaction histories.

**Are there any known bugs in your program?**
- None as of right now

### 3.4 Functional Requirements
**When during the user flow should this component be available?**
- The user enters the app and has the ability to choose whether to request points, transfer points, or view menus, and get recommendations. The users can further describe their transfer and requests after they’ve clicked on their options.

**How does this component interface with the rest of the app? Does it collect user input directly? Does it communicate with other components? External APIs? Backend database?**
- Our app will have to retrieve data from a database where students will be able to store their information on how many swipes, flex points, bear bucks, etc they have left according to the GET App

**Are there security measures to prevent all but certain components from accessing it?**
- Our project requires a user to sign in with Google in order to access the app. To implement this user authentication, we used a firebase backend and implemented a popup to be signed into google. In a future sprint, we would add a terms and conditions contact so that users would be able to sign off on having their emails displayed on our app.

**How does it process data?**
- Algorithms, data structures, etc

### 3.5 Testing Plan
**Backend Testing**
- JUnit Tests for each of our relevant classes and methods
  - Web Socket testing
  - Web scraping testing
- Creating a ClientRequestGenerator Class as well as REPL Commands to ensure that our API requests work 
  - Testing backend handlers (InsertRowHandler, DeleteHandler, UpdateHandler) using the following classes: ClientRequestGenerator.java, DeleteRowCommand.java, InsertRowCommand.java, PrintStudentsCommand.java, UpdataRowCommand.java. Here are the instructions for testing using these classes: 
  1) First you must call load-database ../data/StudentData.sqlite3
  2) Then you can do: insert-row
  3) Then you can do: update-row
  4) Then you can do: delete-row
  After each step you can call on: print-students to see how the database Students table is changing 
  NOTE: Because I tested using this way (as I was told to on ED) rather than JUnit tests, my testing coverage doesn't reflect how much testing I did. 
- System Tests to test REPL commands
  - System Tests for load-database REPL command (note: still experiencing a bug with these as referenced here: https://edstem.org/us/courses/19957/discussion/1479034?answer=3337526)

**Frontend Testing**
- Selenium Testing for front end functionality

**User Authentication Testing**
- In this project, we implemented a google user authentication system using Firebase. Because Firebase is already a secured software that keeps track of users information and other analytical information about the people who use our services, there isn’t an efficient way to test this feature. After hearing from multiple TAs about how they would go about testing this, they all suggested demonstrating a sign in from a user who isn’t already saved in the Firebase database. During the final presentation, we intend on showing our Firebase data to show that it is properly keeping track of who logs into Blueno Bills. 

**Selenium Testing**
- Via recommendations from our mentors throughout the duration of this project, we were told to test our selenium software by checking to see if our app updates daily with the food menus/ingredients from x date until the date of the final term presentation


### 3.6 External Interface Requirements
**How will your user interact with your app? Specifically, how will you make it accessible to visually, motor, cognitive, or otherwise impaired users?**
- Users will be able to download the app and use their fingers to toggle between the different options/buttons of our app. To make our app accessible to those who are visually impared, we can implement a screen reader to our program that will tell the user everything they need to know

**If applicable, how will your app communicate with external software? This will likely include your database, since you probably won’t want to host it locally, but rather on a service like Google Cloud Platform or Firebase.**
- Our app will have to retrieve data from a database where students will be able to store their information on how many swipes, flex points, bear bucks, etc they have left according to the GET App, and our App will have to be responsible for regularly updating the numbers as the user transfers or receives funds.

### 3.7 Non-Functional Requirements
**What standards of performance must your product meet?**
- It needs to work efficiently and fast so that students can access meals in a timely manner and transfer funds amongst themselves within seconds. It needs to be safe for all users and reliably transfer funds so that dining halls and BUDS staff can appropriately charge for meals.

**What standards of security must your product meet?**
- User data must be protected against leaks (number of swipes, username, anonymous requests for transfers)

**What standards of privacy must your product meet?**
- Users can send transfers publicly or privately at their discretion and their points, swipes, and bear bucks counts are private to them.

**How “flashy” and aesthetically pleasing does your UI need to be?**
- Should be clean and simple, easy to use. Figma prototype looks great

**How accessible does your UI need to be?**
- As accessible as possible since ALL students should be able to use and access this resource.
